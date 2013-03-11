import java.util.Arrays;


public class DeckOfCards 
{
	private Card[] deck;
	private String[] deckImg;
	private int counter;
	private final int TOTAL_CARDS = 52;
	private String card;

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
		deckImg = createDeckImg();
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
			System.out.println(card);
		}
	}
	
	//---------------------------------------------------------------
	//  Takes in the amount cards you want dealt
	//---------------------------------------------------------------
	public String dealCard()
	{
		card = deck[counter].toString();
		deck[counter] = null;
		counter++;

		return card;
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

	//----------------------------------------------------------------
	// creates the array with img paths
	//----------------------------------------------------------------
	private String[] createDeckImg()
	{
		deckImg = new String[TOTAL_CARDS+1];
		
		int count = 0;
		for (int j = 0; j < Card.suit.length; j++)
		{
			for (int i = 0; i < Card.face.length; i++)
			{
				deckImg[count] = "classic-cards/" + Card.face[i] + Card.suit[j] + ".png";
				count++;
			}
		}
		deckImg[deckImg.length-1] = "classic-cards/BackVertical.png";

		return deckImg;
	}
	
	//---------------------------------------------------------------
	//  Prints out the full deck image paths
	//---------------------------------------------------------------
	public String dealDeckImg()
	{		
		return Arrays.toString(deckImg);
	}
	
	//----------------------------------------------------------------
	// Finds the img path that corresponds with the card
	//----------------------------------------------------------------
	public String findCardImg(String card)
	{
		boolean cardFound = false;
		int index = 0;
		
		while(!cardFound)
		{
			cardFound = (deckImg[index].indexOf(card) >= 0);
			
			if(!cardFound)
				index++;
		}
		
		return deckImg[index];
	}
}

