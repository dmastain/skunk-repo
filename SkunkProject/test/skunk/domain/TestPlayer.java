package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayer 
{	
	@Test
	void test_set_player_name()
	{
		Player player = new Player("Dan");
		String name = player.getName();
		assertEquals(name, "Dan");
	}	
	
	@Test
	void test_init_player_score() 
	{
		Player player = new Player("Dan");
		int score = player.getScore();
		assertEquals(0, score);
	}
	 @Test
	void test_init_player_chips() 
	{
		Player player = new Player("Dan");
		int chips = player.getChips();
		assertEquals(50, chips);
	}
	
	@Test
	void test_player_set_score() 
	{
		Player player = new Player("Dan");
		int score = 5;
		player.setScore(score);
		assertEquals(score, player.getScore());
	}
	
	@Test
	void test_player_set_chips() 
	{
		Player player = new Player("Dan");
		int chips = 5;
		player.setScore(chips);
		assertEquals(chips, player.getScore());
	}
}
