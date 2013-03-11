
public class GamePlay
{
	private boolean userTurn;
	private DeckOfCards deck;
	private User player;
	private Dealer dealer;
	private final int BLACKJACK = 21;

	public GamePlay()
	{
		player = new User();
		dealer = new Dealer();
		deck = new DeckOfCards();
		deck.shuffleDeck();
		userTurn = true;
	}

	public DeckOfCards getDeck() 
	{
		return deck;
	}

	public void setDeck(DeckOfCards deck) 
	{
		this.deck = deck;
	}

	public User getPlayer() 
	{
		return player;
	}

	public void setPlayer(User player) 
	{
		this.player = player;
	}

	public Dealer getDealer() 
	{
		return dealer;
	}

	public void setDealer(Dealer dealer) 
	{
		this.dealer = dealer;
	}

	public boolean isUserTurn() 
	{
		return userTurn;
	}
	
	public void endTurn() 
	{
		userTurn = false;
	}

	public boolean determinePush(int playerTotal, int dealerTotal)
	{
		if((BLACKJACK-playerTotal) == (BLACKJACK-dealerTotal))
			return true;
		else
			return false;
	}
	
	public boolean determineWinner(int playerTotal, int dealerTotal)
	{
		if((BLACKJACK-playerTotal) < (BLACKJACK-dealerTotal))
			return true;
		if(dealerTotal > BLACKJACK)
			return true;
		else
			return false;
	}
	
	public boolean isBlackjack(int handTotal)
	{
		if(handTotal == BLACKJACK)
			return true;
		else
			return false;
	}
	
	public boolean isBust(int handTotal)
	{
		if(handTotal > BLACKJACK)
			return true;
		else
			return false;
	}

}
