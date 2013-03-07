/**
 * @author amartinez
 *User.java
 * Feb 27, 2013
 */
public class Dealer extends Player
{	
	public boolean userWins;
	private int handTotal = 0, numOfCards = 0;
	private int wallet = 1000;
	private String[] hand;

	public Dealer()
	{
		hand = new String[12];
	}

	public Dealer(String name)
	{

	}

	@Override
	public int betting()
	{
		while( bet < 0 || bet > getWallet())
		{
			if(bet==0)
				break;
//			userWins=playGame();
			if(userWins)
				setWallet(getWallet() + bet);
			else
				setWallet(getWallet() - bet);
			if(getWallet() == 0)
				System.out.println(" Your out of money, better luck next time!");
			break;				
		}

		return getWallet();

	}

//	public boolean playGame()
//	{
//		DeckOfCards deck;
////		BlackjackHand dealerCards;
////		BlackjackHand userCards;
//
//		deck = new DeckOfCards();
////		dealerCards = new BlackjackHand();
////		userCards = new BlackjackHand();
//
//
//		deck.shuffleDeck();
//		dealerCards.addCard( deck.dealCard());
//		dealerCards.addCard( deck.dealCard());
//		userCards.addCard( deck.dealCard());
//		userCards.addCard( deck.dealCard());
//
//		if (dealerCards.getBlackjackValue() == 21)
//			return false;
//
//		if (userCards.getBlackjackValue() == 21)
//			return true;
//
//
//
//
//
//		return true;
//	}

	private int findCardValue(String card)
	{
		//char value = card.charAt(1);
		String value = card.substring(0,2);

		if(value.startsWith("10") || value.startsWith("J") || value.startsWith("Q") || value.startsWith("K"))
			return 10;
		if(value.startsWith("A"))
			return 11;
		else
		{
			value = value.substring(0, 1);
			int cardValue = Integer.parseInt(value);
			return cardValue;
		}
	}

	@Override
	public int getHandTotal(String[] hand) 
	{
		handTotal = 0;

		for(int i = 0; i < hand.length; i++)
		{
			if(hand[i] == null)
				break;
			else
				handTotal += findCardValue(hand[i]);
		}

		return handTotal;
	}

	public void setHand(String card)
	{
		if(numOfCards != hand.length)
		{
			hand[numOfCards] = card;
			numOfCards++;
		}
	}

	public String getHand(int i) 
	{
		return hand[i];
	}
	
	public void resetHand()
	{
		hand = new String[12];
		numOfCards = 0;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
}
