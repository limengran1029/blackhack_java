package blackhack;

import java.util.ArrayDeque;

public class Deck {
	private int NUM_CARDS = 52;
	private ArrayDeque<Card> deck = new ArrayDeque<Card>();

	public Deck() {
		for (int i = 1; i < NUM_CARDS - 1; i++)
		{
			deck.add(new Card());
		}
	}
	
	public Card drawCardFromDeck() {
		return deck.pop();
	}

}
