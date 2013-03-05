/**
 * @author amartinez
 *User.java
 * Feb 27, 2013
 */
public class User extends Player
{	
	public boolean userWins;

	public User()
	{

	}

	public User(String name)
	{

	}

	@Override
	public int betting()
	{
		while( bet < 0 || bet > wallet)
		{
			if(bet==0)
				break;
			userWins=playGame();
			if(userWins)
				wallet = wallet + bet;
			else
				wallet = wallet - bet;
			if(wallet == 0)
				System.out.println(" Your out of money, better luck next time!");
			break;				
		}

		return wallet;

	}

	public boolean playGame()
	{
		DeckOfCards deck;
		BlackjackHand dealerCards;
		BlackjackHand userCards;

		deck = new DeckOfCards();
		dealerCards = new BlackjackHand();
		userCards = new BlackjackHand();


		deck.shuffleDeck();
		dealerCards.addCard( deck.dealCard());
		dealerCards.addCard( deck.dealCard());
		userCards.addCard( deck.dealCard());
		userCards.addCard( deck.dealCard());

		if (dealerCards.getBlackjackValue() == 21)
			return false;

		if (userCards.getBlackjackValue() == 21)
			return true;

		



		return true;
	}
}
