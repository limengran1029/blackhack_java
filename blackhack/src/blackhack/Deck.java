package blackhack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
<<<<<<< HEAD
	private int NUM_CARDS = 52;
	private ArrayDeque<Card> deck = new ArrayDeque<Card>();
=======
	private ArrayDeque<Card> deck;
>>>>>>> refs/remotes/origin/card_logic

	public Deck() {
<<<<<<< HEAD
		for (int i = 1; i < NUM_CARDS - 1; i++)
		{
			deck.add(new Card());
		}
	}
	
	public Card drawCardFromDeck() {
		return deck.pop();
	}
=======
		ArrayList<Card> tDeck = new ArrayList<Card>();
>>>>>>> refs/remotes/origin/card_logic

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