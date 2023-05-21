package blackjack.rules;

import java.util.Map;

import blackjack.deck.Card;
import blackjack.deck.Rank;

import static blackjack.deck.Rank.*;
import static java.util.Map.entry;

public class Rules
{
	public static final int CARDS = 52;
	public static final int LIMIT = 21;
	public static final double NATURAL_PAYOUT = 1.5;
	public static final int MIN_BET = 2;
	public static final int MINIMUM_CUT_RANGE = 60;
	public static final int MAXIMUM_CUT_RANGE = 75;
	private static final Map<Rank, Integer> VALUES = Map.ofEntries(
			entry(ACE, 11), entry(TWO, 2), entry(THREE, 3), entry(FOUR, 4), entry(FIVE, 5),
			entry(SIX, 6), entry(SEVEN, 7), entry(EIGHT, 8), entry(NINE,  9), entry(TEN, 10), 
			entry(JACK, 10), entry(QUEEN, 10), entry(KING, 10));
	
	/**
	 * Get the Blackjack value for the given card
	 * @param card - some card
	 * @return the numeric value betwe 1 and 11
	 */
	public static int value(Card card)
	{
		return VALUES.get(card.getRank());
	}
}