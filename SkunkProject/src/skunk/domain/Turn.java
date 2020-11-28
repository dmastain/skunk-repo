package skunk.domain;

import java.util.*;

public class Turn
{
	static List<Integer> rolls;
	int lostChips;
	Player player;
	boolean endTurn;
	
	public Turn(Player player)
	{
		this.lostChips = 0;
		this.player = player;
		rolls = new ArrayList<Integer>();
		endTurn = false;
	}
	
	public void addRoll()
	{
		Dice dice = player.getDice();
		int roll = dice.getLastRoll();
		rolls.add(roll);
	}
	
	public void addRoll(Dice dice)
	{
		int roll = dice.getLastRoll();
		rolls.add(roll);
	}
	
	public List<Integer> getRolls() {
		return rolls;
	}

	public int getLostChips() {
		return lostChips;
	}

	public boolean isEndTurn() {
		return endTurn;
	}
}