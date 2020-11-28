package skunk.domain;
import java.util.List;

import edu.princeton.cs.introcs.*;

public class UserInterface
{
	public UserInterface() 
	{
		StdOut.print("Welcome to Skunk!\n");
	}
	
	
	public String promptPlayerName()
	{	
		StdOut.print("Enter player name:");
		
		String name = StdIn.readString();
		
		return name;
	}
	
	public void displayPlayerInfo(Player player)
	{
		StdOut.println("Name: " + player.getName() + " | Score: " + player.getScore() + " | Chips:" + player.getChips());
	}
	
	public void printOut(String str)
	{
		StdOut.println(str);
	}
	
	public void printTurn(Turn turn)
	{
		Player player = turn.getPlayer();
		String chipStr = player.getName() + "'s Lost Chips: " + turn.lostChips;
		String rollStr = player.getName() + "'s Dice Rolls: ";
		List<Integer> diceRolls = turn.getDiceRolls();
		List<Integer> die1Rolls = turn.getDie1Rolls();
		List<Integer> die2Rolls = turn.getDie2Rolls();
		
		for(int i=0; i < diceRolls.size(); i++)
		{
			if (i > 0)
			{
				rollStr += ", ";
			}
			rollStr += "{" + diceRolls.get(i) + "}:" + "[" + die1Rolls.get(i) + "," + die2Rolls.get(i) + "]";
		}
		
		StdOut.println(rollStr);
		StdOut.println(chipStr);
	}
	
	public boolean promptPlayerEndTurn(Player player)
	{
		boolean endTurn;
		
		StdOut.print("End " + player.getName() + "'s turn(Y/N):");
		
		String end = StdIn.readString();

		if (end.equalsIgnoreCase("Y") || end.equalsIgnoreCase("Yes")) 
		{
			endTurn = true;
		}
		else if(end.equalsIgnoreCase("N") || end.equalsIgnoreCase("No"))
		{
			endTurn = false;
		}
		else
		{
			StdOut.println("Invalid response");
			endTurn = promptPlayerEndTurn(player);
		}
		return endTurn;
	}
}
