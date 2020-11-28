package skunk.domain;

import java.util.List;

import edu.princeton.cs.introcs.StdOut;

import java.util.ArrayList;

public class Controller 
{	
	public static void main(String[] args)
	{	
		UserInterface ui = new UserInterface();
				
		String name = ui.promptPlayerName();
		Player player = new Player(name);
		ui.displayPlayerInfo(player);
		Turn turn = new Turn(player);
		turn.setEndTurn(ui.promptPlayerEndTurn(player));
		
		while(turn.isEndTurn() != true)
		{		
			String rollResult = turn.addRoll();
			ui.printOut(rollResult);
			if (turn.isEndTurn() != true)
			{
				turn.setEndTurn(ui.promptPlayerEndTurn(player));
			}
		}
		
		if (turn.isSkunked() != true)
		{
			int score = player.getScore();
			score += Turn.sumDiceRolls();
			player.setScore(score);
		}
		else
		{
			int chips = player.getChips();
			chips -= turn.getLostChips();
			player.setChips(chips);
		}
		
		ui.printTurn(turn);
		ui.displayPlayerInfo(player);
	}
}

