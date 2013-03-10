
/**
 * @author Blake Bishop
 * CSC150 - A
 * 
 * 
 */
public class Card 
{
	public static final String[] face = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	public static final String[] suit = {"Hearts", "Spades", "Clubs", "Diamonds"};
	private String faceValue;
	private String suitValue;
	//Parameters 
		public Card(int x, int y)
		{
			faceValue = face[x];
			suitValue = suit[y];
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return faceValue + suitValue;
		}
}
