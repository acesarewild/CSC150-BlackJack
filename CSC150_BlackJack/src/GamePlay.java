
public class GamePlay 
{
	public boolean userWins;
	
	public boolean playGame()
	{
		DeckOfCards deck;
		BlackjackHand dealerCards;
		BlackjackHand userCards;
		
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
