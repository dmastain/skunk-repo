package skunk.domain;

public class Die
{
	private int lastRoll;

	public Die()
	{
		this.roll();
	}
	
	public Die(int i) 
	{
		this.lastRoll = i;
	}

	public int getLastRoll() // getter method
	{
		return this.lastRoll;
	}

	public void roll() // note how this changes Die's state, but doesn't return anything
	{
		this.lastRoll = (int) (Math.random() * 6 + 1);
	}
	
	public void roll(int i) 
	{
		this.lastRoll = i;
	}

	@Override
	public String toString() // this OVERRIDES the default Object.toString()
	{
		return "Die: " + this.getLastRoll();
	}

}
