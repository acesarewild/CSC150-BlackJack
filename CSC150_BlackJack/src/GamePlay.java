
public class GamePlay 
{
	public boolean userWins;
	
	public GamePlay()
	{
		User player1 = new User();
		Dealer dealer1 = new Dealer();
	}
	
	public boolean playGame()
	{
		DeckOfCards deck;
		BlackjackHand dealerCards, userCards;
		
		 deck = new DeckOfCards();
         dealerCards = new BlackjackHand();
         userCards = new BlackjackHand();
		
		
		  deck.shuffleDeck();
          dealerCards.addCard( deck.dealCard() );
          dealerCards.addCard( deck.dealCard() );
          userCards.addCard( deck.dealCard() );
          userCards.addCard( deck.dealCard() );
		
		
		
		
		return true;
	}
}
