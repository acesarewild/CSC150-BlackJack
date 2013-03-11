/**
 * @author amartinez
 *User.java
 * Feb 27, 2013
 */
public class Dealer extends Player
{	
	public boolean userWins;
	private int handTotal = 0, numOfCards = 0;
	private double wallet = 500;
	private String[] hand;

	public Dealer()
	{
		hand = new String[12];
	}

	public Dealer(String name)
	{

	}

	private int findCardValue(String card)
	{
		String value = card.substring(0,2);

		if(value.startsWith("10") || value.startsWith("J") || value.startsWith("Q") || value.startsWith("K"))
			return 10;
		if(value.startsWith("A"))
			return 11;
		else
		{
			value = value.substring(0, 1);
			int cardValue = Integer.parseInt(value);
			return cardValue;
		}
	}

	@Override
	public int getHandTotal(String[] hand) 
	{
		handTotal = 0;

		for(int i = 0; i < hand.length; i++)
		{
			if(hand[i] == null)
				break;
			else
				handTotal += findCardValue(hand[i]);
		}
		
		softBust();
		return handTotal;
	}

	public void setHand(String card)
	{
		if(numOfCards != hand.length)
		{
			hand[numOfCards] = card;
			numOfCards++;
		}
	}

	public String getHand(int i) 
	{
		return hand[i];
	}

	public void resetHand()
	{
		hand = new String[12];
		numOfCards = 0;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	
	public void addWallet(double w)
	{
		this.wallet += w;
	}

	public boolean isSoftSeventeen()
	{
		if(this.getHandTotal(hand) >= 17)
		{
			for(int i = 0; i < hand.length; i++)
			{
				if(hand[i] == null)
					break;
				if(hand[i].startsWith("Ace"))
					return true;
			}
		}
		
		return false;
	}
	
	private void softBust()
	{
		if(handTotal > 21)
		{
			for(int i = 0; i < hand.length; i++)
			{
				if(hand[i] == null)
					break;
				if(hand[i].startsWith("Ace"))
					handTotal -= 10;
			}
		}
	}

	public String[] getHand() 
	{
		return hand;
	}
}
