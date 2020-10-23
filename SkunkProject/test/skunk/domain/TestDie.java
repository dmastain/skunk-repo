package skunk.domain;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestDie {

	@Test
	public void test_roll_100_die_in_bounds() {
		int roll;
		int i;
		int pass = 0;
		Die die = new Die();
		for (i = 0; i < 100; i++) {
			die.roll();
			roll = die.getLastRoll();
			if (roll >= 1 && roll <= 6) {
				pass++;
			}
		}
		assertEquals(i, pass);
	}
	
	@Test
	public void test_roll_1000_die_fairness() {
		int roll;
		int i;
		float average = 0;
		Die die = new Die();
		for (i = 1; i <= 1000; i++) {
			die.roll();
			roll = die.getLastRoll();
			average += roll;
		}
		average = average / i;
		assertEquals(3.5, average, .2);
	}
	
}
