package blackhack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;

	public Deck() {

		this.deck = new ArrayList<Card>();
		for (int i = 0; i < 4; i++) {
			for (String suit: Card.SUITS) {
				for (int rank: Card.RANKS) {
					this.deck.add(new Card(suit, rank));
				}
			}
		}
		
		Collections.shuffle(deck);
	}

	Card drawCard() {
		return deck.remove(0);
	}
	
	ArrayList<Card> getDeck() {
		return this.deck;
	}
}
