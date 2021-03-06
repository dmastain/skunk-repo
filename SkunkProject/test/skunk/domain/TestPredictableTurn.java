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
		List<Integer> pRollsDie1 = new ArrayList<Integer>();
		List<Integer> pRollsDie2 = new ArrayList<Integer>();
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		pRollsDie1.add(1);
		pRollsDie2.add(2);
		pRolls.add(3);
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		pRollsDie1.add(5);
		pRollsDie2.add(4);
		pRolls.add(9);
		die1.roll(5);
		die2.roll(4);
		dice.roll(die1,die2);
		turn.addRoll(dice);
		assertArrayEquals(turn.getDiceRolls().toArray(),pRolls.toArray());
		assertArrayEquals(turn.getDie1Rolls().toArray(),pRollsDie1.toArray());
		assertArrayEquals(turn.getDie2Rolls().toArray(),pRollsDie2.toArray());
	}
	
	@Test
	void test_PTurn_sum_rolls()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);

		Die die1 = new Die(2);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);

		die1.roll(5);
		die2.roll(4);
		dice.roll(die1,die2);
		turn.addRoll(dice);
		
		int sum = turn.sumDiceRolls();
		assertEquals(sum, 2+2+5+4);
	}
	
	@Test
	void test_PTurn_last_roll()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);

		Die die1 = new Die(2);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);

		die1.roll(5);
		die2.roll(4);
		dice.roll(die1,die2);
		turn.addRoll(dice);
		
		int predictLastRoll = 9;
		int lastRoll = turn.lastRoll();
		assertEquals(lastRoll, predictLastRoll);
	}
	
	@Test
	void test_PTurn_skunk_end_turn()
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
	void test_end_PTurn_single_skunk_die1()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(5);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(true, turn.isSkunked());
		assertEquals(true, turn.isEndTurn());
	}
	
	@Test
	void test_end_PTurn_single_skunk_die2()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(5);
		Die die2 = new Die(1);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(true, turn.isSkunked());
		assertEquals(true, turn.isEndTurn());
	}
	
	@Test
	void test_end_PTurn_double_skunk()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(1);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(true, turn.isSkunked());
		assertEquals(true, turn.isEndTurn());
	}
	
	@Test
	void test_end_PTurn_duce_skunk_die1()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(2);
		Die die2 = new Die(1);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(true, turn.isSkunked());
		assertEquals(true, turn.isEndTurn());
	}
	
	@Test
	void test_end_PTurn_duce_skunk_die2()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(true, turn.isSkunked());
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
	
	@Test
	void test_end_PTurn_2_player_single_skunk_remove_chips()
	{
		Player player1 = new Player("Dan");
		Turn turn1 = new Turn(player1);
		Die die1 = new Die(1);
		Die die2 = new Die(5);
		Dice dice1 = new Dice(die1, die2);
		turn1.addRoll(dice1);
		assertEquals(1, turn1.getLostChips());
		Player player2 = new Player("Zoe");
		Turn turn2 = new Turn(player2);
		Die die3 = new Die(5);
		Die die4 = new Die(1);
		Dice dice2 = new Dice(die3, die4);
		turn2.addRoll(dice2);
		assertEquals(1, turn2.getLostChips());
	}
	
	@Test
	void test_end_PTurn_skunk_duce_remove_chips()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(2, turn.getLostChips());
	}
	
	@Test
	void test_end_PTurn_double_skunk_remove_chips()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		Die die1 = new Die(1);
		Die die2 = new Die(1);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		assertEquals(4, turn.getLostChips());
	}
	
}
