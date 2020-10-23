package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestPredictableDie {

	@Test
	void test_PDie_1_2_3() {
		PredictableDie die = new PredictableDie(new int[] {1,2,3});
		die.roll(1);
		assertEquals(1, die.getLastRoll());
		die.roll(2);
		assertEquals(2, die.getLastRoll());
		die.roll(2);
		assertEquals(3, die.getLastRoll());
	}
	
	@Test
	void test_PDie_1_more_than_once() {
		Die die = new Die(1);
		die.roll(1);
		assertEquals(1,die.getLastRoll());
		die.roll(1);
		assertEquals(1,die.getLastRoll());
	}
	
}
