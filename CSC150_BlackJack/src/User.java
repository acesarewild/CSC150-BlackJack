/**
 * @author amartinez
 *User.java
 * Feb 27, 2013
 */
public class User extends Player
{
	public int wallet = 100;
	public int bet;
	public boolean userWins;
	public 
	

	public int betting()
	{
		while( bet < 0 || bet > wallet)
		{
			if(bet==0)
				break;
			userWins=playGame();
			if(userWins)
				wallet = wallet + bet;
			else
				wallet = wallet - bet;
			if(wallet == 0)
				System.out.println(" Your out of money, better luck next time!");
				break;				
		}
		
		return wallet;
			
	}
	
	public boolean playGame()
	{
		
	}
}
