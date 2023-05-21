package blackjack.rules;

import blackjack.Hand;

public class Play
{
	public static final int STAY        = 0;
	public static final int HIT         = 1;
	public static final int DOUBLE_DOWN = 2;
	public static final int SPLIT       = 3;
	
	private int choice;

	public Play(Hand hand, int choice)
	{
		this.choice = choice;
		
		if (choice == DOUBLE_DOWN || choice == SPLIT)
		{
			if (hand.getCards().size() > 2) throw new RuntimeException("Can only make this action with 2 cards");
			if (hand.getPlayer().getBalance() < hand.getWager()) throw new RuntimeException("Not enough funds");
			if (choice == DOUBLE_DOWN && (hand.getTotal() < 9 || hand.getTotal() > 11))
			{
				throw new RuntimeException("Can only double down on a total of 9-11");
			}
			if (choice == SPLIT && ! hand.getCards().get(0).getRank().equals(hand.getCards().get(1).getRank()))
			{
				throw new RuntimeException("Can only split when the card's ranks match");
			}
		}
	}

	public int getChoice()
	{
		return choice;
	}
}