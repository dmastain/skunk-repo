package skunk.domain;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestDice {
	
	@Test
	public void test_roll_100_dice_in_bounds() {
		int roll;
		int i;
		int pass = 0;
		Dice dice = new Dice();
		for (i = 0; i < 100; i++) {
			dice.roll();
			roll = dice.getLastRoll();
			if (roll >= 2 && roll <= 12) {
				pass++;
			}
		}
		assertEquals(i, pass);
	}
	
	@Test
	public void test_roll_1000_dice_fairness() {
		int roll;
		int i;
		float average = 0;
		Dice dice = new Dice();
		for (i = 1; i <= 1000; i++) {
			dice.roll();
			roll = dice.getLastRoll();
			average += roll;
		}
		average = average / i;
		assertEquals(7.0, average, .2);
	}
}
