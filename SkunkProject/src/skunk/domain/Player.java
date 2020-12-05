package skunk.domain;

public class Player 
{
	private String name;
	private int score;
	private int chips;
	private Dice dice;
	
	public Player(String name)
	{
		this.name = name;
		this.score = 0;
		this.chips = 50;
		this.dice = new Dice();
	}
	
	public Player(String name, Dice dice)
	{
		this.name = name;
		this.score = 0;
		this.chips = 50;
		this.dice = dice;
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

	public Dice getDice()
	{
		return dice;
	}

}
