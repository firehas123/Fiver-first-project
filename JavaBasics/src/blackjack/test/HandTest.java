package blackjack.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.deck.Rank;
import blackjack.deck.Suit;
import blackjack.player.Player;
import gradescope.grader.GradedTest;
import ledger.LoggedTest;

/**
 * @author Tony
 */
public class HandTest extends LoggedTest
{
	@Test		
    @GradedTest(name="Test constructor, doubleDown() (betting side), and payout()", max_score=10)
    public void testCreation() 
    {
		int funds = 1000;
		int bet = 2;
		Player playa = new Player("Dr. Lowedown", funds, null);
		Hand uut = new Hand(playa, bet);
		funds = funds - bet;
		assertEquals("Did not take the wager from the player's account", funds, playa.getBalance());
		assertEquals("Did not save the wager", bet, uut.getWager());

		uut.doubleDown(new Card(Suit.CLUBS, Rank.TWO));
		funds = funds - bet;
		assertEquals("Did not incrase the wager", bet * 2, uut.getWager());
		assertEquals("Did not take the wager from the player's account", funds, playa.getBalance());
		
		uut.payout(1, false); // Not the final payout
		funds = funds + bet * 2; // Bet, double down, then pay out 1.5 times   
		assertEquals("Did not add the payout to the player's account", funds, playa.getBalance());
		assertNotEquals("Zeroed out the wager on a non-final payout", 0, uut.getWager());

		uut.payout(1.5); // Make sure it rounds up!
		funds = funds + bet * 3; // Bet, double down, then pay out 1.5 times   
		assertEquals("Did not add the payout to the player's account", funds, playa.getBalance());
		assertEquals("Did not zero out the wager", 0, uut.getWager());
		
		try
		{
			new Hand(playa, 0);
			fail("Should not create a hand of zero or negative initial bet");
		} catch (RuntimeException e)
		{
			// Yes.. exactly
		}

		try
		{
			Hand dd = new Hand(playa, 100);
			dd.doubleDown(new Card(Suit.CLUBS, Rank.THREE));
			dd.doubleDown(new Card(Suit.CLUBS, Rank.FOUR));
			fail("Should not allow multiple double downs");
		} catch (RuntimeException e)
		{
			// Yes.. exactly
		}
		
    }

	
	@Test		
    @GradedTest(name="Test addCard, removeCard, getCards, and getTotal", max_score=10)
    public void testCardManagement() 
    {
		int funds = 1000;
		int bet = 10;
		Player playa = new Player("Dr. Lowedown", funds, null);
		Hand uut   = new Hand(playa, bet);
		Card ace   = new Card(Suit.CLUBS, Rank.ACE);
		Card two   = new Card(Suit.HEARTS, Rank.TWO);
		Card nine  = new Card(Suit.SPADES, Rank.NINE);
		Card ten   = new Card(Suit.DIAMONDS, Rank.TEN);
		Card jack  = new Card(Suit.SPADES, Rank.JACK);
		Card queen = new Card(Suit.DIAMONDS, Rank.QUEEN);
		Card king  = new Card(Suit.CLUBS, Rank.KING);
		
		assertNotNull("Cards were null", uut.getCards());
		assertEquals("Size of hand did not match expected", 0, uut.getCards().size());
		
		uut.addCard(two);
		assertEquals("Size of hand did not match expected", 1, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 2, uut.getTotal());
		
		uut.addCard(ten);
		assertEquals("Size of hand did not match expected", 2, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 12, uut.getTotal());
		
		uut.addCard(nine);
		assertEquals("Size of hand did not match expected", 3, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 21, uut.getTotal());

		uut.removeCard(nine);
		assertEquals("Size of hand did not match expected", 2, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 12, uut.getTotal());

		uut.addCard(ace);
		assertEquals("Size of hand did not match expected", 3, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 13, uut.getTotal());

		uut.removeCard(two);
		assertEquals("Size of hand did not match expected", 2, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 21, uut.getTotal());		

		uut.removeCard(ten);
		assertEquals("Size of hand did not match expected", 1, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 11, uut.getTotal());		

		uut.addCard(king);
		assertEquals("Size of hand did not match expected", 2, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 21, uut.getTotal());		

		uut.removeCard(ace);
		assertEquals("Size of hand did not match expected", 1, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 10, uut.getTotal());		

		uut.addCard(queen);
		assertEquals("Size of hand did not match expected", 2, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 20, uut.getTotal());		

		uut.addCard(jack);
		assertEquals("Size of hand did not match expected", 3, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 30, uut.getTotal());	

		uut.removeCard(jack);
		uut.addCard(ace);
		assertEquals("Size of hand did not match expected", 3, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 21, uut.getTotal());	
		uut.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
		assertEquals("Size of hand did not match expected", 4, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 22, uut.getTotal());	
		uut.removeCard(queen);
		System.out.println(uut.getCards());
		assertEquals("Size of hand did not match expected", 3, uut.getCards().size());
		assertEquals("Total did not match expected for the hand " + uut.getCards(), 12, uut.getTotal());	
    }	
	
	private static final String CODE_FILE= "src/blackjack/Hand";
	@BeforeClass
	public static void grabCode()
	{
		LoggedTest.grabCode(CODE_FILE);
	}
}