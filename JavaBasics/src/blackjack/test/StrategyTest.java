package blackjack.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.inference.OneWayAnova;
import org.junit.BeforeClass;
import org.junit.Test;

import blackjack.Blackjack;
import blackjack.player.Player;
import blackjack.player.strategy.BasicPlayerStrategy;
import blackjack.player.strategy.CardCountingStrategy;
import blackjack.player.strategy.DealerStrategy;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class StrategyTest extends LoggedTest
{
	private static Stats dealerStats;
	private static Stats benchmarkStats;
	private static Stats uutStats;
	
	static class Stats
	{
		int startingFunds;
		List<Integer> results = new ArrayList<>();
		double mean;
	}
	
	private static Stats runTest(List<Player> players, int trials, int hands)
	{
		Stats stats = new Stats();
		stats.startingFunds = players.get(0).getBalance();
		for (int repeat = 0; repeat < trials; repeat++)
		{
			Blackjack bj = new Blackjack(6, players);
			for (int i = 0; i < hands; i++)
			{
				bj.startRound();
				bj.dealHands();
				bj.play();
//				bj.showHands();
			}
			stats.results.add(players.get(0).getBalance());
		}
		stats.mean = new Mean().evaluate(convert(stats.results));
		
		return stats;
	}

	@BeforeClass
	public static void runTests()
	{
		int trials = 100;
		int hands  = 1000;
		int startingFunds = 1000000;

		List<Player> players = new ArrayList<>();
		players.add(new Player("You", startingFunds, new CardCountingStrategy()));
		uutStats = runTest(players, trials, hands);

		List<Player> benchmarkPlayers = new ArrayList<>();
		benchmarkPlayers.add(new Player("Benchmark", startingFunds, new BasicPlayerStrategy()));
		benchmarkStats = runTest(benchmarkPlayers, trials, hands);

		List<Player> dealerPlayers = new ArrayList<>();
		dealerPlayers.add(new Player("Dealer Strategy", startingFunds, new DealerStrategy()));
		dealerStats = runTest(dealerPlayers, trials, hands);
		
        List<double[]> groups= new ArrayList<>();
        groups.add(convert(uutStats.results));
	}

	@Test		
    @GradedTest(name="Meaningfully beat dealer", max_score=10)
    public void testDealer() 
    {
		System.out.println(String.format("Your average: $%.2f", uutStats.mean - uutStats.startingFunds));
		System.out.println(String.format("Dealer average: $%.2f", dealerStats.mean - dealerStats.startingFunds));
		assertTrue("You lost money compared to the dealer", uutStats.mean > dealerStats.mean);

		OneWayAnova anova = new OneWayAnova();
        List<double[]> groups= new ArrayList<>();
        groups.add(convert(uutStats.results));
        groups.add(convert(dealerStats.results));
        double p = anova.anovaPValue(groups);
		assertTrue("You did not beat the threshold (p=" + p + ")", p < .01);
    }
	
	@Test		
    @GradedTest(name="Meaningfully beat benchmark", max_score=20)
    public void testBenchmark() 
    {
		System.out.println(String.format("Your average: $%.2f", uutStats.mean - uutStats.startingFunds));
		System.out.println(String.format("Benchmark average: $%.2f", benchmarkStats.mean - benchmarkStats.startingFunds));
		assertTrue("You lost money compared to the benchmark", uutStats.mean > benchmarkStats.mean);

		OneWayAnova anova = new OneWayAnova();
        List<double[]> groups= new ArrayList<>();
        groups.add(convert(uutStats.results));
        groups.add(convert(benchmarkStats.results));
        double p = anova.anovaPValue(groups);
		assertTrue("You did not beat the threshold (p=" + p + ")", p < .2);
    }
	
	
	@Test		
    @GradedTest(name="Did you win money?", max_score=30)
    public void testWinner()
    {
		System.out.println(String.format("Your average: $%.2f", uutStats.mean - uutStats.startingFunds));
		assertTrue("You did not win money on average", (uutStats.mean - uutStats.startingFunds) > 0);
    }
	
	private static double[] convert(List<Integer> data)
	{
		double[] returnMe = new double[data.size()];
		for (int i = 0; i < returnMe.length; i++)
		{
			returnMe[i] = data.get(i);
		}
		return returnMe;
	}
	
	private static final String CODE_FILE= "src/blackjack/player/strategy/CardCountingStrategy";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}