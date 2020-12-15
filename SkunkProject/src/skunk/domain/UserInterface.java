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
	
	public void displayKitty(int kitty)
	{
		StdOut.println("Kitty: " + kitty);
	}
	
	public void printOut(String str)
	{
		StdOut.println(str);
	}
	
	public void printTurn(Turn turn)
	{
		Player player = turn.getPlayer();
		String resultStr = "Turn Result: ";
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
		
		switch(turn.getLostChips())
		{
		case 0:
			resultStr += turn.lastRoll() + " points earned!";
			break;
		case 1:
			resultStr += "Single Skunk...";
			break;
		case 2:
			resultStr += "Skunk Duce...";
			break;
		case 4:
			resultStr += "Double Skunk...";
			break;
		}
		
		StdOut.println(resultStr);
		StdOut.println(rollStr);
		StdOut.println(chipStr);
	}
	
	public boolean promptPlayerRollAgain(Player player)
	{
		StdOut.print("Will " + player.getName() + " roll again? (Y/N):");
	
		boolean endTurn = !promptYesNo();
		
		return endTurn;
	}
	
	public boolean promptPlayerEndTurn(Player player)
	{		
		StdOut.print("End " + player.getName() + "'s turn(Y/N):");
		
		return promptYesNo();
	}

	private boolean promptYesNo() 
	{
		boolean endTurn;
		
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
			endTurn = promptYesNo();
		}
		return endTurn;
	}
}
