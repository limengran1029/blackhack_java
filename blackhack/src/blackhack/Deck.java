package blackhack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		for (Suits s: Suits.values()) 
		{
			for (Ranks r: Ranks.values()) 
			{
				Card c = new Card(s,r);
				deck.add(c);
			}
		}
		shuffleDeck();
	}
	
	public void printCards() {
		for (Card c: deck)
		{
			System.out.println(c.getSuit());
			System.out.println(c.getRank());
		}

	}
	
	private void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	/* Testing area
	public static void main(String args[]) {
		Deck d = new Deck();
		d.printCards();
	}
	*/
}
