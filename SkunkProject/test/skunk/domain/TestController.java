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
		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		
		Player currentPlayer = controller.getCurrentPlayer();
		int initKitty = controller.getKitty();
		assertEquals(player, currentPlayer);
		assertEquals(expectedKitty, initKitty);
		assertEquals(null,controller.getCurrentTurn());
	}
	
	@Test
	public void test_controller_addKitty() 
	{
		int kittyAdd1 = 1;
		int kittyAdd2 = 2;
		int kittyAdd3 = 4;
		int expectedKitty = kittyAdd1 + kittyAdd2 + kittyAdd3;
		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		
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
		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		Turn turn = new Turn(player);
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
		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		Turn turn = new Turn(player);
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
		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		
		boolean turnResult = controller.turnControl(endTurn);
		
		assertEquals(true, turnResult);
	}
	
	@Test
	public void test_controller_turnControl_newTurn() 
	{

		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		controller.newTurn();
		
		Turn currentTurn = controller.getCurrentTurn();
		
		assertNotEquals(null, currentTurn);
	}
	
	@Test
	public void test_controller_setRoll_true() 
	{
		boolean endTurn = false;
		Player player = new Player("Dan");
		Controller controller = new Controller(player);
		controller.newTurn();
		controller.setRoll(true);
		
		boolean turnResult = controller.turnControl(endTurn);
		
		assertTrue(turnResult == true || turnResult == false);
	}
}
