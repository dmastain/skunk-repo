package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestPredictableDice {

	@Test
	void test_PDice_Overload_Constructor() {
		PredictableDie die1 = new PredictableDie(new int[] {1});
		PredictableDie die2 = new PredictableDie(new int[] {2});
		Dice dice = new Dice(die1, die2);
		assertEquals(1, die.getLastRoll());
		die.roll();
		assertEquals(2, die.getLastRoll());
		die.roll();
		assertEquals(3, die.getLastRoll());
	}

}
