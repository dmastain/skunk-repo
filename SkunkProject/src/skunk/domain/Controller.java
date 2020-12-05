package skunk.domain;

public class Controller 
{	
	private Player currentPlayer;
	private Turn currentTurn;
	private int kitty;
	private boolean roll;

	public Controller(Player player)
	{
		this.currentPlayer = player;
		this.kitty = 0;
		this.roll = false;
	}
	
	public boolean turnControl(boolean endTurn)
	{	
		if(endTurn != true)
		{		
			if (this.roll == true)
			{
				currentTurn.addRoll();
			}
			endTurn = currentTurn.isEndTurn();
			
			if (currentTurn.isSkunked() != true)
			{
				int score = currentPlayer.getScore();
				score += Turn.sumDiceRolls();
				currentPlayer.setScore(score);
			}
			else
			{
				int chips = currentPlayer.getChips();
				int lostChips = currentTurn.getLostChips();
				chips -= lostChips;
				addKitty(lostChips);
				currentPlayer.setChips(chips);
			}
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
		this.roll = false;
	}
	
	public void newTurn(Turn turn) 
	{
		this.currentTurn = turn;
		this.roll = false;
	}
	
	public void setRoll(boolean roll)
	{
		this.roll = roll;
	}
}

