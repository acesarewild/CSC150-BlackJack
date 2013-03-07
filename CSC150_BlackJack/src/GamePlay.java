
public class GamePlay
{
	public boolean userTurn;
	private DeckOfCards deck;
	private User player;
	private Dealer dealer;

	public GamePlay()
	{
		player = new User();
		dealer = new Dealer();
		deck = new DeckOfCards();
		deck.shuffleDeck();
	}

	public DeckOfCards getDeck() {
		return deck;
	}

	public void setDeck(DeckOfCards deck) {
		this.deck = deck;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public boolean isUserTurn() {
		return userTurn;
	}
}