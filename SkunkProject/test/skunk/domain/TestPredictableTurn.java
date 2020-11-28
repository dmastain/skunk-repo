package skunk.domain;

import org.junit.jupiter.api.Test;

public class TestPredictableTurn 
{
	@Test
	void test_init_PTurn()
	{
		int pRolls[] = {3,9};
		Turn turn = new Turn();
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		turn.addRoll(dice);
		die1.roll(5);
		die2.roll(4);
		dice.roll(die1,die2);
		turn.addRoll(dice);
		assertEquals(pRolls, turn.getRolls);
	}
}
