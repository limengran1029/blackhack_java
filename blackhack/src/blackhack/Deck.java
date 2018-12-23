package blackhack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayDeque<Card> deck;

	public Deck() {
		ArrayList<Card> tDeck = new ArrayList<Card>();

		// 4 represents num of decks in the whole deck stack
		for (int i = 1; i <= 4; i++) {
			for (String suit: Card.SUITS)
			{
				for (int rank: Card.RANKS)
				{
					tDeck.add(new Card(suit, rank));
				}
			}
		}
		Collections.shuffle(tDeck);
		deck = new ArrayDeque<Card>(tDeck);
		tDeck = null; //remove from memory
	}
	
	public Card drawCard() {
		return deck.pop();
	}
	
	public static void main(String args[]) {
		Deck d = new Deck();
		System.out.println(d.drawCard());
	}
}