package blackjack.player.strategy;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.rules.Play;
import blackjack.rules.Rules;

/**
 * @author <Your Name Here>
 */
public class CardCountingStrategy implements Strategy
{

	public CardCountingStrategy()
	{
	}

	@Override
	public int getBid()
	{
		return Rules.MIN_BET;
	}

	@Override
	public Play makePlay(Hand hand, Card dealerCard)
	{
		return new Play(hand, Play.STAY);
	}

	@Override
	public void clockCard(Card card)
	{
	}

	@Override
	public boolean buyInsurance()
	{
		return false; //getTrueCount() >= bidThreshold;
	}
	
	@Override
	public void shuffleTime(int numberOfDecks)
	{
	}	
}