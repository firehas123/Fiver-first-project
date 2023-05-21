package blackjack.player;

import blackjack.Hand;
import blackjack.deck.Card;
import blackjack.player.strategy.Strategy;
import blackjack.rules.Play;

public class Player implements Comparable<Player>
{
	private String name;
	private int balance;
	private Strategy strategy;

	public Player(String name, int initialBalance, Strategy startingStrategy)
	{
		this.name = name;
		this.strategy = startingStrategy;
		balance = initialBalance;
	}

	public String getName()
	{
		return name;
	}
	
	public Hand startHand()
	{
		return new Hand(this, strategy.getBid());
	}

	public Play play(Hand hand, Card dealerCard)
	{
		return strategy.makePlay(hand, dealerCard);
	}
	
	public void placeBet(int amount)
	{
		balance -= amount;
	}

	public void seeCard(Card card)
	{
		strategy.clockCard(card);
	}
	
	public void win(int amount)
	{
		balance += amount;
	}
	
	public int getBalance()
	{
		return balance;
	}

	@Override
	public int compareTo(Player o)
	{
		return this.name.compareTo(o.name);
	}

	public void noteShuffle(int numberOfDecks)
	{
		strategy.shuffleTime(numberOfDecks);
	}
}