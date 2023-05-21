package blackjack.player.strategy;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.rules.Play;

public interface Strategy
{
	/**
	 * Return how much you want to bid on the next hand
	 * @return the bid amount (must be at least the minimum bid from the Rules class)
	 */
	public int getBid();
	
	/**
	 * Make a decision on how to play the current hand
	 * @param hand - the current hand
	 * @param dealerCard - the showing dealer card
	 * @return the next Play to make
	 */
	public Play makePlay(Hand hand, Card dealerCard);
	
	/**
	 * Your strategy can respond to each card shown at the table 
	 * @param card - the next card shown
	 */
	public void clockCard(Card card);
	
	/**
	 * Your strategy is informed each time the cards are shuffled
	 */
	public void shuffleTime(int numberOfDecks);

	/**
	 * Select whether to buy insurance when the dealer shows an ace
	 * @return true to buy insurance
	 */
	public boolean buyInsurance();
}