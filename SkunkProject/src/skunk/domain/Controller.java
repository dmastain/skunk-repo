package skunk.domain;

public class Controller 
{	
	private Player currentPlayer;
	private Turn currentTurn;
	private int kitty;

	public Controller(Player player)
	{
		this.currentPlayer = player;
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

	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	public Turn getCurrentTurn() 
	{
		return this.currentTurn;
	}

	public void newTurn() 
	{
		this.currentTurn = new Turn(getCurrentPlayer());
	}
	
	public void newTurn(Turn turn) 
	{
		this.currentTurn = turn;
	}
	
}

