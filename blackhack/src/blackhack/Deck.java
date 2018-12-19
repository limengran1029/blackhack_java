package blackhack;

import java.util.ArrayDeque;

public class Deck {
	private ArrayDeque<Card> deck = new ArrayDeque<Card>();

	public Deck() {
		fillDeck();
	}
	
	private void fillDeck() {
		deck.add(new Card());
		deck.add(new Card());
	}
	
	public Card drawCardFromDeck() {
		return deck.pop();
	}

}
