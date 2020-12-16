package skunk.domain;

public class SkunkApp 
{
	public static void main(String[] args)
	{	
		UserInterface ui = new UserInterface();
		
		int numPlayers = ui.promptNumPlayers();
		Controller controller = new Controller(numPlayers);

		for(int i=0; i < numPlayers; i++)
		{
			String name = ui.promptPlayerName();
			controller.addPlayer(name);
		}
		
		while(!controller.isEndGame())
		{
			for (int pNum = 0; pNum < controller.getNumPlayers(); pNum++) 
			{
				controller.setCurrentPlayer(pNum);
				Player player = controller.getCurrentPlayer();
				
				ui.displayPlayerInfo(player);
				ui.displayKitty(controller.getKitty());

				boolean endTurn;
				boolean first = true;
				
				controller.newTurn();
				
				while(true)
				{
					if (first)
					{
						first = false;
						endTurn = ui.promptPlayerEndTurn(controller.getCurrentPlayer());
					}
					else
					{
						endTurn = ui.promptPlayerRollAgain(controller.getCurrentPlayer());	
					}
					
					endTurn = controller.turnControl(endTurn);
					ui.printTurn(controller.getCurrentTurn());
					
					if (player.getScore() >= 100)
					{	
						
						ui.printBreak();
						ui.displayScoreBoard(controller.getPlayerList());
						ui.displayKitty(controller.getKitty());
						ui.printBreak();
						ui.finalTurn();
						ui.printBreak();
						
						for(int finalNum = pNum + 1; finalNum <= controller.getNumPlayers(); finalNum++)
						{
							if (finalNum == controller.getNumPlayers())
							{
								finalNum = 0;
							}
							if (finalNum == pNum)
							{
								break;
							}
							
							controller.setCurrentPlayer(finalNum);
							player = controller.getCurrentPlayer();
							
							boolean lastFirst = true;
							
							controller.newTurn();
							
							while (true)
							{
								if (lastFirst)
								{
									lastFirst = false;
									endTurn = ui.promptPlayerEndTurn(controller.getCurrentPlayer());
								}
								else
								{
									endTurn = ui.promptPlayerRollAgain(controller.getCurrentPlayer());	
								}
								
								endTurn = controller.turnControl(endTurn);
								ui.printTurn(controller.getCurrentTurn());
								
								if (endTurn == true)
								{
									break;
								}
							}
							ui.printBreak();
							ui.displayScoreBoard(controller.getPlayerList());
							ui.displayKitty(controller.getKitty());
							ui.printBreak();
						}
						
						controller.setEndGame(true);
						endTurn = true;
						break;
					}
					
					if (endTurn == true)
					{
						break;
					}
				}
				
				if (controller.isEndGame() == true)
				{
					break;
				}
				
				ui.printBreak();
				ui.displayScoreBoard(controller.getPlayerList());
				ui.displayKitty(controller.getKitty());
				ui.printBreak();
			}
		}
		
		Player winner = controller.findWinner();
		
		ui.printWinner(winner, controller.getKitty());
		
		ui.printBreak();
		ui.displayScoreBoard(controller.getPlayerList());
		ui.displayKitty(controller.getKitty());
		ui.printBreak();
	}
}
