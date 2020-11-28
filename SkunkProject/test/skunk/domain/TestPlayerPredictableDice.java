package skunk.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestPlayerPredictableDice 
{
	@Test
	void test_player_PDice_Overload_Constructor()
	{
		Die die1 = new Die(1);
		Die die2 = new Die(2);
		Dice dice = new Dice(die1, die2);
		Player player = new Player("Dan", dice);
		assertEquals(1, player.getDice().getDie1LastRoll());
		assertEquals(2, player.getDice().getDie2LastRoll());
		assertEquals(3, player.getDice().getLastRoll());
	}
}
