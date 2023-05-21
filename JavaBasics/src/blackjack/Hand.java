package blackjack;

import java.util.List;

import blackjack.deck.Card;
import blackjack.player.Player;

/**
 * A single hand of Blackjack 
 * @author <Your Name Here>
 */
public class Hand
{
	private Player player;
	private List<Card> cards;
	private int wager;
	private boolean doubledown;
	
	/**
	 * A hand starts with a player offering a wager
	 * @param player the player making the bet
	 * @param initialWager a positive value (or throws a RuntimeException)
	 */
	public Hand(Player player, int initialWager)
	{
		placeBet(initialWager); // Not required, but a reminder this is available...
	}
	
	/**
	 * Takes the bet amount from the player's balance and applies
	 * it to this hand.  If the player's balance is less than the 
	 * bet amount this throws a RuntimeException
	 * @param amount the amount of the bet
	 */
	private void placeBet(int amount)
	{
	}

	/**
	 * Settles the hand by playing out the provided amount to the player.
	 * The amount paid to the player should be an integer value, so round
	 * any fractional amount up to the next dollar amount.  After paying out
	 * the hand, the wager goes to zero.
	 * @param amount the payout amount
	 */
	public void payout(double multiplier) {payout(multiplier, true);}
	public void payout(double multiplier, boolean finalPayout)
	{
	}
	
	/**
	 * Adds a card to the hand
	 * @param card the card to add
	 */
	public void addCard(Card card)
	{
	}

	/**
	 * Removes a card from the hand
	 * @param card the card to remove
	 * @return the removed card (for passthrough functions)
	 */
	public Card removeCard(Card card)
	{
		return card;
	}

	/**
	 * Get the cards the hand
	 * @return a list of hand cards (please don't modify them)
	 */
	public List<Card> getCards()
	{
		return cards;
	}
	
	/**
	 * Gets the Blackjack point total for the hand (see the Rules class)
	 *  - Numbered cards are worth their face value 
	 *  - Face cards are worth 10
	 *  - An ace is worth 11, or 1 if the total would exceed 21 
	 * @return the computed total
	 */
	public int getTotal()
	{
		int total = 0;
		return total;
	}

	public void doubleDown(Card card)
	{
	}
	
	public boolean isDoubledown()
	{
		return doubledown;
	}

	public int getWager()
	{
		return wager;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}