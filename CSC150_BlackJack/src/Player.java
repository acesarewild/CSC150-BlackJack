/**
 * @author amartinez
 *
 */
public abstract class Player
{
	public double wallet;
	protected String name;
	protected abstract int getHandTotal(String[] hand);
}
