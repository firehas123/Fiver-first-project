package blackjack.deck;

import java.util.List;

/**
 * A model for a deck of standard playing cards
 * @author <Your Name Here>
 */
public class Deck
{
	private List<Card> cards;

	/**
	 * Creates a single deck of cards where each Card is a unique Suit and Rank
	 * based on the Enum types in this class
	 */
	public Deck(){this(1);}
	
	/**
	 * Creates more than one deck of cards as described above
	 * @param number the number of decks to include (must be positive or it throws a Runtime Exception)
	 */
	public Deck(int number)
	{
	}
	
	/**
	 * Randomize the remaining cards in the deck
	 */
	public void shuffle()
	{
	}

	/**
	 * Remove one card from the deck and return it to the caller
	 * @return the removed card
	 */
	public Card draw()
	{
		return null;
	}

	/**
	 * The number of cards in the deck
	 * @return and integer
	 */
	public int getCardsRemaining()
	{
		return 0;
	}

	@Override
	public String toString()
	{
		return cards.toString();
	}
}