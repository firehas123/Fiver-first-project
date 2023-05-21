package blackjack.player.strategy;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.rules.Play;
import blackjack.rules.Rules;

public class DealerStrategy implements Strategy
{
	private static final int MAX_DEALER = 17;
	private int bet;
	
	public DealerStrategy() {this(Rules.MIN_BET);}
	public DealerStrategy(int bet)
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
		if (hand.getTotal() < MAX_DEALER)
		{
			return new Play(hand, Play.HIT);
		} else
		{
			return new Play(hand, Play.STAY);
		}
	}

	@Override
	public void clockCard(Card card)
	{
		// Dealer doesn't care
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