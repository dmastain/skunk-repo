package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestPredictableDice {

	@Test
	void test_PDice_Overload_Constructor() {
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		assertEquals(1, die1.getLastRoll());
		assertEquals(2, die2.getLastRoll());
		assertEquals(3, dice.getLastRoll());
	}

}
