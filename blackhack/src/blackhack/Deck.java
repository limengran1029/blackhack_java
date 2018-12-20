package blackhack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayDeque<Card> deck;

	public Deck() {
		ArrayList<Card> tDeck = new ArrayList<Card>();

		for (int i = 1; i < 5; i++) {
			for (String suit: new Card().getSuit()) 
			{
				for (int rank: new Card().getRank()) 
				{
					tDeck.add(new Card(suit, rank));
				}
			}
		}
		Collections.shuffle(tDeck);
		deck = new ArrayDeque<Card>(tDeck);
	}
	
	public ArrayDeque<Card> getDeck() {
		return deck;
	}
	
	public static void main(String args[]) {
		Deck d = new Deck();
	}
}