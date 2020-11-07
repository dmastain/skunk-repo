package skunk.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayer {

	@Test
	void test_set_player_name() {
		Player player = new Player("Dan");
		String name = player.getName();
		assertEquals(name, "Dan");
	}

}
