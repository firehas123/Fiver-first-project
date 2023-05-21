package blackjack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import blackjack.deck.Card;
import blackjack.deck.Deck;
import blackjack.player.Player;
import blackjack.player.strategy.DealerStrategy;
import blackjack.rules.Play;
import blackjack.rules.Rules;

public class Blackjack
{
	private Deck deck;
	private List<Player> players;
	Player dealer = new Player("Dealer", Integer.MAX_VALUE, new DealerStrategy());
	private Hand dealerHand;
	private Map<Player, List<Hand>> hands;
	private int currentCut;
	private int deckCount;
	
	public Blackjack(int numberOfDecks, List<Player> players)
	{
		this.players = players;
		hands = new TreeMap<>();
		this.deckCount = numberOfDecks;
		shuffleAndCut();
	}

	private void shuffleAndCut()
	{
		deck = new Deck(deckCount); // Yes we throw away the old cards, but this is a fancy casino.  We always use new cards!
		deck.shuffle();
		currentCut = new Random().nextInt(Rules.MAXIMUM_CUT_RANGE - Rules.MINIMUM_CUT_RANGE) + Rules.MINIMUM_CUT_RANGE;
		for (Player p : players)
		{
			p.noteShuffle(deckCount);
		}
	}
	
	public void startRound()
	{
		if (deck.getCardsRemaining() < currentCut)
		{
			shuffleAndCut();
		}
		
		hands.clear();
		for (Player p : players)
		{
			List<Hand> playerHands = new ArrayList<>(); 
			playerHands.add(p.startHand());
			hands.put(p, playerHands);
		}
	}
	
	public void dealHands()
	{
		dealerHand = new Hand(dealer, 1);
		for (int i = 0; i < 2; i++)
		{
			for (Player p : players)
			{
				for (Hand h : hands.get(p))
				{
					h.addCard(getPublicCard());
				}
			}
			if (i == 0)
			{
				dealerHand.addCard(getPublicCard());
			} else
			{
				dealerHand.addCard(deck.draw()); // Only the first card is shown on dealing
			}
		}
		
		boolean dealerNatural = dealerHand.getTotal() == Rules.LIMIT;
		for (List<Hand> playerHands : hands.values())
		{
			for (Iterator<Hand> i = playerHands.iterator(); i.hasNext(); )
			{
				Hand hand = i.next();
				if (hand.getTotal() == Rules.LIMIT) // Pay out naturals
				{
					if (dealerNatural)
					{
						hand.payout(1);
					} else
					{
						hand.payout(Rules.NATURAL_PAYOUT, false);
					}
				}
				if (dealerNatural)
				{
					i.remove();
				}
			}
		}
	}

	public void play()
	{
		Card dealerShowing = dealerHand.getCards().get(0);
		// Have the players play their hands
		for (Player p : players)
		{
			List<Hand> playerHands = hands.get(p);
			for (ListIterator<Hand> i = playerHands.listIterator(); i.hasNext(); )
			{
				Hand hand = i.next();
				Hand splitHand = playHand(p, hand, dealerShowing);
				if (splitHand != null) 
				{
					i.add(splitHand);
					splitHand.addCard(getPublicCard());
				}
				if (hand.getTotal() > Rules.LIMIT)
				{
					i.remove();
				}
			}
		}
		
		// Play the dealer's hand
		showCard(dealerHand.getCards().get(1));
		playHand(dealer, dealerHand, dealerShowing);
		
		// Settle the bets
		int dealerTotal = dealerHand.getTotal();
		if (dealerTotal > Rules.LIMIT)
		{
			for (Player p : players)
			{
				for (Hand hand : hands.get(p))
				{
					if (hand.isDoubledown()) {showCard(hand.getCards().get(2));}
					hand.payout(2);
				}
			}
		} else
		{
			for (Player p : players)
			{
				for (Hand hand : hands.get(p))
				{
					if (hand.isDoubledown()) {showCard(hand.getCards().get(2));}
					if (hand.getTotal() > dealerTotal)
					{
						hand.payout(2);
					}
				}
			}
		}
	}

	/**
	 * Plays a hand with the user until they stay, double down, or split
	 * @param p the player
	 * @param hand the hand
	 * @param dealerShowing the card showing from the dealer
	 * @return a new hand if they split
	 */
	private Hand playHand(Player p, Hand hand, Card dealerShowing)
	{
		while (true)
		{
			Play play = p.play(hand, dealerShowing);
			switch (play.getChoice())
			{
				case Play.STAY: return null;
				case Play.HIT:
					hand.addCard(getPublicCard());
					if (hand.getTotal() > Rules.LIMIT)
					{
						return null;
					}
					break;
				case Play.DOUBLE_DOWN:
					hand.doubleDown(deck.draw());
					return null;
				case Play.SPLIT:
					Hand newHand = new Hand(p, hand.getWager()); // May want to create the new hand in the play?
					newHand.addCard(hand.removeCard(hand.getCards().remove(1)));
					hand.addCard(getPublicCard());
					newHand.addCard(getPublicCard());
					return newHand;
			}
		}
	}
	
	public void showHands()
	{
		System.out.println("..........................................");
		for (Player p : players)
		{
			for (Hand hand : hands.get(p))
			{
				System.out.println(p.getName() + " ($" + p.getBalance() + "): " + hand.getTotal());
				for (Card c: hand.getCards())
				{
					System.out.println(c);
				}
				System.out.println();
			}
		}
		System.out.println("Dealer: " + dealerHand.getTotal());
		for (Card c: dealerHand.getCards())
		{
			System.out.println(c);
		}
		System.out.println("..........................................");
	}
	
	private Card getPublicCard()
	{
		assert(deck.getCardsRemaining() > 0);
		Card card = deck.draw();
		showCard(card);
		return card;
	}
	
	private void showCard(Card card)
	{
		for (Player p : players)
		{
			p.seeCard(card);
		}
	}
}