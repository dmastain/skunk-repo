package skunk.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestTurn 
{
	@Test
	void test_init_end_turn()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		assertEquals(turn.isEndTurn(), false);
	}
	
	@Test
	void test_init_player_turn()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		assertEquals(turn.getPlayer(), player);
	}
	
	@Test
	void test_set_end_turn()
	{
		Player player = new Player("Dan");
		Turn turn = new Turn(player);
		turn.setEndTurn(true);
		assertEquals(turn.isEndTurn(), true);
	}
}
