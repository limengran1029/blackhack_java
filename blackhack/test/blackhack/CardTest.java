package blackhack;

import static org.junit.Assert.*;

import org.junit.Test;
public class CardTest {

	@Test
	public void testToString() {
        Card card1 = new Card("Clubs", 5);
		assertEquals(card1.toString(), "5 of Clubs");
        Card card2 = new Card("Diamonds", 12);
		assertEquals(card2.toString(), "K of Diamonds");
	}
	
	@Test
	public void testHide() {
        Card card1 = new Card("Clubs", 5);
        card1.hide();
		assertTrue(card1.isHidden());
        card1.unHide();
		assertFalse(card1.isHidden());
	}
	
	@Test
	public void testgetRank() {
        Card card1 = new Card("Clubs", 14);
		assertEquals(card1.getValue(), 11);
        Card card2 = new Card("Clubs", 12);
		assertEquals(card2.getValue(), 10);
	}

}
