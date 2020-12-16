package skunk.domain;

import java.util.ArrayList;
import java.util.List;

public class Controller 
{	
	private static int numPlayers;
	private static List<Player> playerList;
	private static boolean endGame = false;
	private Player currentPlayer;
	private Turn currentTurn;
	private int kitty;

	public Controller(int numPlayers)
	{
		this.numPlayers = numPlayers;
		this.playerList = new ArrayList<Player>(this.numPlayers);
		this.kitty = 0;
	}
	
	public boolean turnControl(boolean endTurn)
	{	
		if(endTurn != true)
		{		
			currentTurn.addRoll();
			
			endTurn = currentTurn.isEndTurn();
		}
			
		if (currentTurn.isSkunked() != true && endTurn == true)
		{
			int score = currentPlayer.getScore();
			score += Turn.sumDiceRolls();
			currentPlayer.setScore(score);
		}
		else if (currentTurn.isSkunked() == true)
		{
			int chips = currentPlayer.getChips();
			int lostChips = currentTurn.getLostChips();
			chips -= lostChips;
			addKitty(lostChips);
			currentPlayer.setChips(chips);
		}
		
		return endTurn;
	}

	public int getKitty()
	{
		return kitty;
	}

	public void addKitty(int chips) 
	{
		this.kitty += chips;
	}
	
	public void setCurrentPlayer(int pNum)
	{
		currentPlayer = playerList.get(pNum); 
	}

	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	public Turn getCurrentTurn() 
	{
		return this.currentTurn;
	}
	
	public void addPlayer(String name)
	{
		Player player = new Player(name);
		playerList.add(player);
		if (currentPlayer == null)
		{
			this.currentPlayer = player;
		}
	}

	public boolean isEndGame() 
	{
		return endGame;
	}

	public void setEndGame(boolean endGame) 
	{
		Controller.endGame = endGame;
	}

	public void newTurn() 
	{
		this.currentTurn = new Turn(getCurrentPlayer());
	}
	
	public void newTurn(Turn turn) 
	{
		this.currentTurn = turn;
	}
	public int getNumPlayers()
	{
		return numPlayers;
	}
	
	public List<Player> getPlayerList() 
	{
		return playerList;
	}
	
	public Player findWinner()
	{
		int maxScore = 0;
		int winnerIndex = 0;
		
		for (int pNum = 0; pNum < numPlayers; pNum++) 
		{
			Player player = playerList.get(pNum) ;
			int pScore = player.getScore(); 
			if (pScore > maxScore)
			{
				maxScore = pScore;
				winnerIndex = pNum;
			}
		}
		
		for (int pNum2 = 0; pNum2 < numPlayers; pNum2++) 
		{
			if (pNum2 == winnerIndex)
			{
				continue;
			}
			else
			{
				Player player = playerList.get(pNum2) ;
				int pChips = player.getChips();
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
				this.kitty += removeChips;
				player.setChips(pChips);
			}
		}
		
		Player winningPlayer = playerList.get(winnerIndex);
		int winningChips = winningPlayer.getChips();
		
		winningChips += this.kitty;
		winningPlayer.setChips(winningChips);
		
		return winningPlayer;
	}
}

