package blackhack;

public class Card {

	public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	public static final int[] RANKS = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

	private String suit;
	private int rank;

	public Card() {
	}

	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;

	}
	
	public String toString() {
		return "Card > " + rank + " of " + suit;
	}
	
}
