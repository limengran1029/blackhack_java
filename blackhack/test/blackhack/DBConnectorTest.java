package blackhack;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConnectorTest {

	@Test
	public void testLogin() {
		DBConnector connect = new DBConnector();
		Player p1 = new Player();
		p1.setCredentials("Li", "123");;
		assertTrue(connect.login(p1));
		Player p2 = new Player();
		p2.setCredentials("Li", "321");;
		assertFalse(connect.login(p2));
	}

}
