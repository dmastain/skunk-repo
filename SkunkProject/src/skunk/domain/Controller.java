package skunk.domain;

import java.util.ArrayList;
import java.util.List;

public class Controller 
{	
	private static int numPlayers;
	private static List<Player> playerList = new ArrayList<Player>();
	private static boolean endGame = false;
	private static int kitty = 0;
	
	public static int getKitty() 
	{
		return kitty;
	}

	public static void setKitty(int kitty) 
	{
		Controller.kitty = kitty;
	}

	public static boolean isEndGame()
	{
		return endGame;
	}

	public static void setEndGame(boolean endGame)
	{
		Controller.endGame = endGame;
	}

	public static int getNumPlayers()
	{
		return numPlayers;
	}

	public static void setNumPlayers(int numPlayers) 
	{
		Controller.numPlayers = numPlayers;
	}

	public static List<Player> getPlayerList()
	{
		return playerList;
	}

	public static void setPlayerList(List<Player> playerList) 
	{
		Controller.playerList = playerList;
	}
	
	private static void turnControl(UserInterface ui, Player player)
	{
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
			score += turn.sumDiceRolls();
			player.setScore(score);
		}
		else
		{
			int chips = player.getChips();
			int kitty = getKitty();
			int lostChips = turn.getLostChips();
			chips -= lostChips;
			kitty += lostChips;
			player.setChips(chips);
			setKitty(kitty);
		}
		
		ui.printBreak();
		ui.printTurn(turn);
		ui.displayPlayerInfo(player);
	}
	
	private static void findWinner(UserInterface ui)
	{
		int maxScore = 0;
		int winnerIndex = 0;
		ui.printBreak();
		
		for (int pNum = 0; pNum < getNumPlayers(); pNum++) 
		{
			Player player = playerList.get(pNum) ;
			int pScore = player.getScore(); 
			if (pScore > maxScore)
			{
				maxScore = pScore;
				winnerIndex = pNum;
			}
		}
		
		for (int pNum2 = 0; pNum2 < getNumPlayers(); pNum2++) 
		{
			if (pNum2 == winnerIndex)
			{
				continue;
			}
			else
			{
				Player player = playerList.get(pNum2) ;
				int pChips = player.getChips();
				int currentKitty = getKitty();
				int removeChips;
				
				if (player.getScore() == 0)
				{
					removeChips = 10;
				}
				else
				{
					removeChips = 5;
				}
				pChips -= removeChips;
				currentKitty += removeChips;
				player.setChips(pChips);
				setKitty(currentKitty);
			}
		}
		
		Player winningPlayer = playerList.get(winnerIndex);
		int winningChips = winningPlayer.getChips();
		
		winningChips += getKitty();
		winningPlayer.setChips(winningChips);
		
		ui.printBreak();
		ui.printWinner(winningPlayer, kitty);
		ui.printOut("Final Scoreboard:");
		ui.displayScoreBoard(getPlayerList());
		ui.displayKitty(getKitty());
		ui.printBreak();
	}
	
	public static void main(String[] args)
	{	
		UserInterface ui = new UserInterface();
				
		setNumPlayers(ui.promptNumPlayers());
		
		List<Player> localPlayerList =  new ArrayList<Player>();
		
		for (int i = 0; i < getNumPlayers(); i++) 
		{
			String Name = ui.promptPlayerName();
			Player player = new Player(Name);
			localPlayerList.add(player);
		}
		
		setPlayerList(localPlayerList);
		
		while(!isEndGame())
		{
			for (int pNum = 0; pNum < getNumPlayers(); pNum++) 
			{
				Player player = playerList.get(pNum) ;
						
				ui.printBreak();
				ui.displayScoreBoard(getPlayerList());
				ui.displayKitty(getKitty());
				ui.printBreak();
				
				turnControl(ui, player);
				
				if (player.getScore() >= 100)
				{	
					ui.printBreak();
					ui.finalTurn();
					
					for(int finalNum = pNum + 1; finalNum <= getNumPlayers(); finalNum++)
					{
						if (finalNum == getNumPlayers())
						{
							finalNum = 0;
						}
						if (finalNum == pNum)
						{
							break;
						}
						
						player = playerList.get(finalNum) ;
					
						ui.printBreak();
						ui.displayScoreBoard(getPlayerList());
						ui.displayKitty(getKitty());
						ui.printBreak();
						
						turnControl(ui, player);
					}
					
					setEndGame(true);
					break;
				}
			}
		}
		
		findWinner(ui);
	}
}

