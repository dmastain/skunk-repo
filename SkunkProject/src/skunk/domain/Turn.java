package skunk.domain;

import java.util.*;

public class Turn
{
	static List<Integer> diceRolls;
	static List<Integer> die1Rolls;
	static List<Integer> die2Rolls;
	int lostChips;
	Player player;
	boolean endTurn;
	boolean skunked;

	public Turn(Player player)
	{
		this.lostChips = 0;
		this.player = player;
		diceRolls = new ArrayList<Integer>();
		die1Rolls = new ArrayList<Integer>();
		die2Rolls = new ArrayList<Integer>();
		endTurn = false;
		skunked = false;
	}
	
	public String addRoll()
	{
		Dice dice = player.getDice();
		dice.roll();
		return addRoll(dice);
	}
	
	public String addRoll(Dice dice)
	{
		int roll = dice.getLastRoll();
		die1Rolls.add(dice.getDie1LastRoll());
		die2Rolls.add(dice.getDie2LastRoll());
		diceRolls.add(roll);
		String rollResult = dice.toString();
		
		if (dice.getDie1LastRoll() == 1 || dice.getDie2LastRoll() == 1)
		{
			this.endTurn = true;
			
			if (dice.getDie1LastRoll() == 1 && dice.getDie2LastRoll() == 1)
			{
				this.lostChips = 4;
				this.skunked = true;
				rollResult = rollResult.concat("\nDouble Skunk!");
			}
			else if ((dice.getDie1LastRoll() == 1 && dice.getDie2LastRoll() == 2) || (dice.getDie1LastRoll() == 2 && dice.getDie2LastRoll() == 1))
			{
				this.lostChips = 2;
				this.skunked = true;
				rollResult = rollResult.concat("\nSkunk Duce!");
			} 
			else
			{
				this.lostChips = 1;
				this.skunked = true;
				rollResult = rollResult.concat("\nSingle Skunk!");
			}
		}
		else
		{
			rollResult = rollResult + "\nTurn total: " + sumDiceRolls();			
		}
		return rollResult;
	}
	
	public int sumDiceRolls()
	{
	    int sum = 0;
	    for (int i: diceRolls) 
	    {
	        sum += i;
	    }
	    return sum;
	}
	
	public void setEndTurn(boolean endTurn)
	{
		this.endTurn = endTurn;
	}

	public List<Integer> getDie1Rolls() {
		return die1Rolls;
	}

	public List<Integer> getDie2Rolls() {
		return die2Rolls;
	}

	public List<Integer> getDiceRolls()
	{
		return diceRolls;
	}

	public int getLostChips() 
	{
		return lostChips;
	}

	public boolean isEndTurn() 
	{
		return endTurn;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public boolean isSkunked()
	{
		return skunked;
	}
}
