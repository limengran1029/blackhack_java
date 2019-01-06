package blackhack;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testUsername() {
		Player p = new Player();
		p.setUsername("somebody");
		assertEquals(p.getUsername(), "somebody");
	}

	@Test
	public void testPassword() {
		Player p = new Player();
		p.setPassword("123456");
		assertEquals(p.getPassword(), "123456");
		
	}

}
