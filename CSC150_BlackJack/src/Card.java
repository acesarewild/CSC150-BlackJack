
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
	public static final int[] value = {1,2,3,4,5,6,7,8,9,10,11};
	private final int INDEX_OF_TEN = 9;
	private String faceValue;
	private String suitValue;
	private int cardValue;
	//Parameters 
		public Card(int x, int y)
		{
			faceValue = face[x];
			suitValue = suit[y];
			if(faceValue.equals("Jack") || faceValue.equals("Queen") || faceValue.equals("King"))
				cardValue = value[INDEX_OF_TEN];
			if(faceValue.equals("Ace"))
				cardValue = value[value.length-1];
			if(x <= 9 && x > 0)
				cardValue = value[x];
		}
		public int getCardValue() {
			return cardValue;
		}
		public void setCardValue(int cardValue) {
			this.cardValue = cardValue;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return faceValue + suitValue;
		}
}
