import java.util.Arrays;


public class DeckOfCards {
	private Card[] deck;
	private int counter;
	private final int TOTAL_CARDS = 52;
	private String deckImage;
	
	public DeckOfCards()
	{
		deck = new Card[TOTAL_CARDS];
		int count = 0;
		for (int j = 0; j < Card.suit.length; j++)
		{
			for (int i = 0; i < Card.face.length; i++)
			{
				deck[count] = new Card(i, j);
				count++;
			}
		}
	}
	//---------------------------------------------------------------
	//  Creates a shuffle object and runs shuffleArray on deck object
	//---------------------------------------------------------------
	public void shuffleDeck()
	{
		Shuffle shuffle = new Shuffle();
		shuffle.shuffleArray(deck);
	}
	public void multipleCards(int x)
	{
		for (int i = 0; i < x; i++)
		{
			dealCard();
			System.out.println(deckImage);
		}
	}
	//---------------------------------------------------------------
	//  Takes in the amount cards you want dealt
	//---------------------------------------------------------------
	public String dealCard()
	{
			deckImage = deck[counter] + ".png";
			deck[counter] = null;
			counter++;

			return deckImage;
	}
	//---------------------------------------------------------------
	//  Prints out the full deck
	//---------------------------------------------------------------
	public String dealDeck()
	{		
		return Arrays.toString(deck);
	}
	//---------------------------------------------------------------
	//  Takes total amount of cards, subtracts the counter and
	//  prints out to the console
	//---------------------------------------------------------------
	public void numberLeft()
	{
		int numberLeft = TOTAL_CARDS - counter;
		System.out.println("You have " + numberLeft + " cards left.");
	}
}
