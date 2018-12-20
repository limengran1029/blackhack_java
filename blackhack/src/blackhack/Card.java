package blackhack;

public class Card {
<<<<<<< HEAD
=======
	private String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	private int[] ranks = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	private String suit;
	private int rank;

	public Card() {
	}

	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;

	}

	public String[] getSuit() {
		return suits;
	}

	public void setSuit(String[] suit) {
		this.suits = suit;
	}

	public int[] getRank() {
		return ranks;
	}

	public void setRank(int[] rank) {
		this.ranks = rank;
	}
	
	public String toString() {
		return "Card >" + suit + " | " + rank;
	}
	
>>>>>>> refs/remotes/origin/card_logic
}
