package blackhack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {

	@Test
	void testDeck() {
		Deck d = new Deck();

		// we test for 4 card decks, total of 208 card
		assertEquals(d.getDeck().size(), 208);
	}

}
