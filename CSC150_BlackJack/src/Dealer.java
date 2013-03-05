
public class Dealer extends Player
{
	private int handTotal = 0;
	private String[] hand;

	public Dealer()
	{
		this.name = "Dealer";
	}
	
	@Override
	public int betting() 
	{
		return 0;
	}
	
	private int findCardValue(String card)
	{
		//char value = card.charAt(1);
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
		
		return handTotal;
	}

}
