package blackjack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.deck.Rank;
import blackjack.deck.Suit;
import blackjack.player.Player;
import blackjack.player.strategy.BasicPlayerStrategy;
import blackjack.player.strategy.Strategy;
import blackjack.rules.Play;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class BasicStrategyTest extends LoggedTest
{
	@Test		
    @GradedTest(name="Test Basic makePlay()", max_score=10)
    public void testPlay() 
    {
		int funds = 1000;
		int bet = 10;
		Player playa = new Player("Dr. Lowedown", funds, null);
		Strategy uut = new BasicPlayerStrategy();

		Hand hand = new Hand(playa, bet);
		hand.addCard(new Card(Suit.HEARTS, Rank.TWO));
		hand.addCard(new Card(Suit.DIAMONDS, Rank.SIX));

		checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.HIT);

		Card temp = new Card(Suit.DIAMONDS, Rank.FOUR);
		hand.addCard(temp);
		checkPlay(uut, hand, Rank.FOUR, Rank.SIX, Play.STAY);
		checkPlay(uut, hand, Rank.TWO, Rank.THREE, Play.HIT);
		checkPlay(uut, hand, Rank.SEVEN, Rank.ACE, Play.HIT);

		hand.removeCard(temp);
		hand.addCard(new Card(Suit.DIAMONDS, Rank.FIVE));
		checkPlay(uut, hand, Rank.TWO, Rank.THREE, Play.STAY);
		checkPlay(uut, hand, Rank.SEVEN, Rank.ACE, Play.HIT);

		hand.addCard(new Card(Suit.DIAMONDS, Rank.FOUR));
		checkPlay(uut, hand, Rank.SEVEN, Rank.ACE, Play.STAY);
    }

	
	@Test		
    @GradedTest(name="Test Double Down makePlay()", max_score=10)
    public void testDoubleDown() 
    {
		int funds = 1000;
		int bet = 10;
		Player playa = new Player("Dr. Lowedown", funds, null);
		Strategy uut = new BasicPlayerStrategy();

		Hand hand = new Hand(playa, bet);
		hand.addCard(new Card(Suit.DIAMONDS, Rank.SIX));
		Card temp = new Card(Suit.HEARTS, Rank.FIVE);
		hand.addCard(temp);
		checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.DOUBLE_DOWN);

		hand.removeCard(temp);
		temp = new Card(Suit.DIAMONDS, Rank.FOUR);
		hand.addCard(temp);
		checkPlay(uut, hand, Rank.TWO, Rank.NINE, Play.DOUBLE_DOWN);
		checkPlay(uut, hand, Rank.TEN, Rank.ACE, Play.HIT);

		hand.removeCard(temp);
		temp = new Card(Suit.DIAMONDS, Rank.THREE);
		hand.addCard(temp);
		checkPlay(uut, hand, Rank.TWO, Rank.SIX, Play.DOUBLE_DOWN);
		checkPlay(uut, hand, Rank.SEVEN, Rank.ACE, Play.HIT);

		hand.addCard(new Card(Suit.HEARTS, Rank.TWO));
		Play play = uut.makePlay(hand, new Card(Suit.CLUBS, Rank.THREE));
		assertNotEquals("Can only double down on two cards", Play.DOUBLE_DOWN, play.getChoice());
    }
	
	@Test		
    @GradedTest(name="Test Split makePlay()", max_score=10)
    public void testSplit() 
    {
		int funds = 1000;
		int bet = 10;
		Player playa = new Player("Dr. Lowedown", funds, null);
		Strategy uut = new BasicPlayerStrategy();

		Hand hand = new Hand(playa, bet);
		setupDoubles(hand, Rank.ACE);
		checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.SPLIT);

		setupDoubles(hand, Rank.EIGHT);
		checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.SPLIT);

		setupDoubles(hand, Rank.TWO);
		checkPlay(uut, hand, Rank.TWO, Rank.SEVEN, Play.SPLIT);
		checkPlay(uut, hand, Rank.EIGHT, Rank.ACE, Play.HIT);

		setupDoubles(hand, Rank.THREE);
		checkPlay(uut, hand, Rank.TWO, Rank.SEVEN, Play.SPLIT);
		checkPlay(uut, hand, Rank.EIGHT, Rank.ACE, Play.HIT);
		
		setupDoubles(hand, Rank.SEVEN);
		checkPlay(uut, hand, Rank.TWO, Rank.SEVEN, Play.SPLIT);
		checkPlay(uut, hand, Rank.EIGHT, Rank.ACE, Play.HIT);

		setupDoubles(hand, Rank.SIX);
		checkPlay(uut, hand, Rank.TWO, Rank.SIX, Play.SPLIT);
		checkPlay(uut, hand, Rank.SEVEN, Rank.ACE, Play.HIT);

		setupDoubles(hand, Rank.FOUR);
		checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.HIT);
		checkPlay(uut, hand, Rank.SEVEN, Rank.ACE, Play.HIT);

		setupDoubles(hand, Rank.FIVE);
		checkPlay(uut, hand, Rank.TWO, Rank.NINE, Play.DOUBLE_DOWN);
		checkPlay(uut, hand, Rank.TEN, Rank.ACE, Play.HIT);

		setupDoubles(hand, Rank.NINE);
		checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.STAY);

		for (Rank r : Rank.values())
		{
			if (r.ordinal() < Rank.NINE.ordinal() || r == Rank.ACE) continue;
			setupDoubles(hand, r);
			checkPlay(uut, hand, Rank.TWO, Rank.ACE, Play.STAY);
		}
		
    }

	private void setupDoubles(Hand hand, Rank rank)
	{
		hand.getCards().clear();
		hand.addCard(new Card(Suit.DIAMONDS, rank));
		hand.addCard(new Card(Suit.SPADES,   rank));
	}
	
	
	private void checkPlay(Strategy uut, Hand hand, 
						   Rank bottom, Rank top, int expected)
	{
		for (Rank r : Rank.values())
		{
			if (r.ordinal() >= bottom.ordinal() && r.ordinal() <= top.ordinal())
			{
				Play play = uut.makePlay(hand, new Card(Suit.CLUBS, r));
				assertEquals("Play did not match expected for " + r, expected, play.getChoice());
			}
		}
	}
	
	private static final String CODE_FILE= "src/blackjack/player/strategy/BasicPlayerStrategy";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}