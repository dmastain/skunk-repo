package skunk.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestController 
{
	@Test
	public void test_controller_init() 
	{
		int expectedKitty = 0;
		Controller controller = new Controller(1);
		
		int initKitty = controller.getKitty();

		assertEquals(expectedKitty, initKitty);
		assertEquals(null, controller.getCurrentTurn());
		assertEquals(null, controller.getCurrentPlayer());
		assertEquals(1, controller.getNumPlayers());
	}
	
	@Test
	public void test_controller_set_endGame() 
	{
		Controller controller = new Controller(1);
		
		controller.setEndGame(true);

		assertEquals(true, controller.isEndGame());
	}
	
	@Test
	public void test_controller_addKitty() 
	{
		int kittyAdd1 = 1;
		int kittyAdd2 = 2;
		int kittyAdd3 = 4;
		int expectedKitty = kittyAdd1 + kittyAdd2 + kittyAdd3;
		Controller controller = new Controller(1);
		
		controller.addKitty(kittyAdd1);
		controller.addKitty(kittyAdd2);
		controller.addKitty(kittyAdd3);
		
		int actualKitty = controller.getKitty();
		assertEquals(expectedKitty, actualKitty);
	}
	
	@Test
	public void test_controller_turnControl_skunk() 
	{
		boolean endTurn = false;
		Controller controller = new Controller(1);
		controller.addPlayer("Dan");
		controller.setCurrentPlayer(0);
		Turn turn = new Turn(controller.getCurrentPlayer());
		Die die1 = new Die(1);
		Die die2 = new Die(5);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		
		controller.newTurn(turn);
		boolean turnResult = controller.turnControl(endTurn);
		
		assertEquals(true, turnResult);
	}
	
	@Test
	public void test_controller_turnControl_no_skunk() 
	{
		boolean endTurn = false;
		Controller controller = new Controller(1);
		controller.addPlayer("Dan");
		controller.setCurrentPlayer(0);
		Turn turn = new Turn(controller.getCurrentPlayer());
		Die die1 = new Die(2);
		Die die2 = new Die(5);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		
		controller.newTurn(turn);
		boolean turnResult = controller.turnControl(endTurn);
		
		assertEquals(false, turnResult);
	}
	
	@Test
	public void test_controller_turnControl_endTurn_true() 
	{
		boolean endTurn = true;
		
		Controller controller = new Controller(1);
		controller.addPlayer("Dan");
		controller.setCurrentPlayer(0);
		controller.newTurn();
		
		boolean turnResult = controller.turnControl(endTurn);
		
		assertEquals(true, turnResult);
	}
	
	@Test
	public void test_controller_turnControl_newTurn() 
	{	
		Controller controller = new Controller(1);
		controller.addPlayer("Dan");
		controller.setCurrentPlayer(0);
		controller.newTurn();
		
		Turn currentTurn = controller.getCurrentTurn();
		
		assertNotEquals(null, currentTurn);
	}
	
	@Test
	public void test_controller_findWinner()
	{
		Controller controller = new Controller(4);
		controller.addPlayer("Dan");
		controller.addPlayer("Zoe");
		controller.addPlayer("Liz");
		controller.addPlayer("Fred");
		
		controller.setCurrentPlayer(0);
		Player player = controller.getCurrentPlayer();
		
		player.setScore(115);
		
		controller.setCurrentPlayer(1);
		player = controller.getCurrentPlayer();
		
		player.setScore(45);
		
		controller.setCurrentPlayer(2);
		player = controller.getCurrentPlayer();
		
		player.setScore(68);
		
		controller.setCurrentPlayer(3);
		player = controller.getCurrentPlayer();
		
		player.setScore(0);
		
		controller.setCurrentPlayer(0);
		
		Player winner = controller.findWinner();
		
		assertEquals(winner, controller.getCurrentPlayer());
	}
}
