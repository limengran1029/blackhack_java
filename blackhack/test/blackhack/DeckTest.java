package blackhack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void testDeck() {
		Deck d = new Deck();
		assertEquals(d.getDeck().size(), 208);
	}

}
