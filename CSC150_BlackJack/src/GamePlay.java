
public class GamePlay 
{
	public boolean userWins;
	public DeckOfCards deck;
	public User player;
	public Dealer dealer;
	
	public GamePlay()
	{
		player = new User();
		dealer = new Dealer();
		deck = new DeckOfCards();
		deck.shuffleDeck();
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
