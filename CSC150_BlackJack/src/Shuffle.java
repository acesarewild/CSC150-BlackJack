import java.util.Random;

/**********************************************************
 * 
 * @author Blake Bishop
 * CSC150 - A
 * 
 *
 *********************************************************/

public class Shuffle 
{
	private Random generator;
	
	public Shuffle()
	{
		generator = new Random();
	}
	//-----------------------------------------------------
	//  @param array
	//  NOTE: This method alters this argument
	//-----------------------------------------------------
	public void shuffleArray(Card[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			//Randomly choose index j from the remainder of the array
			int randomNumber = generator.nextInt(array.length-i); //Fischer Yates shuffle
			int j = i + randomNumber;
			
			//Swap array[i] with array[j] (i being the current position in the array, j being the random number)
			Card temp = array[i];
			array[i] = array [j];
			array[j] = temp;
		}	
	}
}
