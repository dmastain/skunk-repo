package skunk.domain;

public class Player 
{
	private String name;
	private int score;
	private int chips;
	
	public Player(String name)
	{
		this.name = name;
		this.score = 0;
		this.chips = 50;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
	}
	
	public int getChips()
	{
		return chips;
	}
	
	public void setChips(int chips)
	{
		this.chips = chips;
	}

	public String getName() 
	{
		return name;
	}

}
