package blackjack.player.strategy;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.rules.Play;
import blackjack.rules.Rules;

/**
 * @author <Your Name Here>
 */
public class BasicPlayerStrategy implements Strategy
{
	private int bet;
	
	public BasicPlayerStrategy() {this(Rules.MIN_BET);}
	public BasicPlayerStrategy(int bet)
	{
		this.bet = bet;
	}

	@Override
	public int getBid()
	{
		return bet;
	}
	
	@Override
	public Play makePlay(Hand hand, Card dealerCard)
	{
		return new Play(hand, Play.STAY);
	}

	@Override
	public void clockCard(Card card)
	{
		// Nothing for this one
	}

	@Override
	public boolean buyInsurance()
	{
		return false;
	}
	
	@Override
	public void shuffleTime(int numberOfDecks)
	{
		// Nothing to do
	}	
}