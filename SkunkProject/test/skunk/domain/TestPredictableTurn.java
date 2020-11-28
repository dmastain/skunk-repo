package skunk.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TestPredictableTurn 
{
	@Test
	void test_PTurn_add_rolls()
	{
		List<Integer> pRolls = new ArrayList<Integer>();
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		pRolls.add(3);
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		pRolls.add(9);
		die1.roll(5);
		die2.roll(4);
		dice.roll(die1,die2);
		turn.addRoll(dice);
		assertArrayEquals(turn.getRolls().toArray(),pRolls.toArray());
	}
	
	@Test
	void test_end_PTurn_skunk()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(5);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(true, turn.isEndTurn());
	}
	
	@Test
	void test_end_PTurn_single_skunk_remove_chips()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(5);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(1, turn.getLostChips());
	}
}
