package skunk.domain;

public class SkunkApp 
{
	public static void main(String[] args)
	{	
		UserInterface ui = new UserInterface();
				
		String name = ui.promptPlayerName();
		Player player = new Player(name);
		
		Controller controller = new Controller(player);
		
		ui.displayPlayerInfo(controller.getCurrentPlayer());
		ui.displayKitty(controller.getKitty());
		
		boolean endTurn = ui.promptPlayerEndTurn(player);
		
		controller.newTurn();
		
		while(endTurn == false)
		{
			endTurn = controller.turnControl(endTurn);
			ui.printTurn(controller.getCurrentTurn());

			if (endTurn == false)
			{
				endTurn = ui.promptPlayerEndTurn(controller.getCurrentPlayer());
			}
			else
			{
				break;
			}
			
		}
		
		ui.displayPlayerInfo(player);
		ui.displayKitty(controller.getKitty());
	}
}
