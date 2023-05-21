package blackjack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import blackjack.deck.Card;
import blackjack.deck.Deck;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class DeckTest extends LoggedTest
{
	private static final int CARDS_IN_DECK = 52;
	private static final int SHUFFLE_THRESHOLD        = 4;	
	private static final int SHUFFLE_REPEATS          = 10;	
	private static final int SHUFFLE_REPEAT_THRESHOLD = 3;

	@Test		
    @GradedTest(name="Test constructor", max_score=5)
    public void testCreation() 
    {
		Deck uut = new Deck();
		assertEquals("Did not contain the number of expected cards", CARDS_IN_DECK, uut.getCardsRemaining());
		
		Set<Card> cards = new HashSet<>();
		for (int i = 0; i < CARDS_IN_DECK; i++)
		{
			cards.add(uut.draw());
			assertEquals("Did not contain the number of expected cards", CARDS_IN_DECK - i - 1, uut.getCardsRemaining());
			assertEquals("Did not contain unique cards", i + 1, cards.size());
		}
		
		uut = new Deck(5);
		assertEquals("Did not contain the number of expected cards", CARDS_IN_DECK * 5, uut.getCardsRemaining());
		
		try
		{
			uut = new Deck(0);
			fail("Deck should only allow positive numbers");
		} catch(RuntimeException e)
		{
			// No worries, it was supposed to do this!
		}
		
    }

	
	@Test		
    @GradedTest(name="Test shuffle()", max_score=5)
    public void testShuffle() 
    {
		testCreation();
		int factoryCountTotal = 0;
		int shuffleCountTotal = 0;

		Deck factoryNew = new Deck();
		for (int i = 0; i < SHUFFLE_REPEATS; i++)
		{
			Deck uut        = new Deck();
			Deck uut2       = new Deck();
			uut.shuffle();
			uut2.shuffle();
			int factoryCount = 0;
			int shuffleCount = 0;
			while(factoryNew.getCardsRemaining() > 0)
			{
				Card factoryNewCard = factoryNew.draw();
				Card uutCard = uut.draw();
				Card uut2Card = uut2.draw();
				if (factoryNewCard.equals(uutCard)) factoryCount++;
				if (uutCard.equals(uut2Card)) shuffleCount++;
			}
			if (factoryCount > SHUFFLE_THRESHOLD) factoryCountTotal++;
			if (shuffleCount > SHUFFLE_THRESHOLD) shuffleCountTotal++;
		}
		assertTrue("Shuffle did not randomize a factory deck enough ("+factoryCountTotal+")", factoryCountTotal < SHUFFLE_REPEAT_THRESHOLD);
		assertTrue("Shuffle does not randomize enough on multiple shuffles("+shuffleCountTotal+")", shuffleCountTotal < SHUFFLE_THRESHOLD);
    }
	
	private static final String CODE_FILE= "src/blackjack/deck/Deck";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}