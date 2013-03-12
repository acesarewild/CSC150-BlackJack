
/**
 * @author aquang
 *GamePlay.java
 * Mar 11, 2013
 */
public class GamePlay
{
	private boolean userTurn;
	private DeckOfCards deck;
	private User player;
	private Dealer dealer;
	private final int BLACKJACK = 21;

	//constructor of GamePlay
	public GamePlay()
	{
		player = new User();
		dealer = new Dealer();
		deck = new DeckOfCards();
		deck.shuffleDeck();
		userTurn = true;
	}
	
	//getter for deck
	public DeckOfCards getDeck() 
	{
		return deck;
	}
	
	//setter for deck
	public void setDeck(DeckOfCards deck) 
	{
		this.deck = deck;
	}

	//getter for player
	public User getPlayer() 
	{
		return player;
	}

	//setter for player
	public void setPlayer(User player) 
	{
		this.player = player;
	}

	//getter for dealer
	public Dealer getDealer() 
	{
		return dealer;
	}

	//setter for dealer
	public void setDealer(Dealer dealer) 
	{
		this.dealer = dealer;
	}

	//getter for userTurn
	public boolean isUserTurn() 
	{
		return userTurn;
	}
	
	//ends the users turn
	public void endTurn() 
	{
		userTurn = false;
	}

	//determine if there is a push/tie
	public boolean determinePush(int playerTotal, int dealerTotal)
	{
		if((BLACKJACK-playerTotal) == (BLACKJACK-dealerTotal))
			return true;
		else
			return false;
	}
	
	//checks if the player won
	public boolean isPlayerWinner(int playerTotal, int dealerTotal)
	{
		if((BLACKJACK-playerTotal) < (BLACKJACK-dealerTotal))
			return true;
		if(isBust(dealerTotal) && !isBust(playerTotal))
			return true;
		else
			return false;
	}
	
	//checks if the dealer won
	public boolean isDealerWinner(int playerTotal, int dealerTotal)
	{
		if((BLACKJACK-playerTotal) > (BLACKJACK-dealerTotal))
			return true;
		if(isBust(playerTotal) && !isBust(dealerTotal))
			return true;
		else
			return false;
	}
	
	//checks if someone has blackjacked
	public boolean isBlackjack(int handTotal)
	{
		if(handTotal == BLACKJACK)
			return true;
		else
			return false;
	}
	
	//checks if someone busted
	public boolean isBust(int handTotal)
	{
		if(handTotal > BLACKJACK)
			return true;
		else
			return false;
	}
}
