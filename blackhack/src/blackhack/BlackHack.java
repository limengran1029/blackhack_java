package blackhack;

import java.util.ArrayDeque;

public class BlackHack {

	public static void main(String[] args) {
		ArrayDeque<Deck> decks = new ArrayDeque<Deck>();
		decks.add(new Deck());
		decks.add(new Deck());
		decks.add(new Deck());
		
		System.out.println(decks.element().drawCardFromDeck().toString());

		

	}
}