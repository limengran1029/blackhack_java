package blackhack;

public class Card {

	public static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	public static final int[] RANKS = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

	private String suit;
	private int rank;
	
	private boolean hidden;

	public Card(String suit, int rank) {
		this.suit = suit;
		this.setRank(rank);
	}
	
	@Override
	public String toString() {
		String option;
		switch (getRank()) {
		case 11:
			option = "J";
			break;
		case 12:
			option = "K";
			break;
		case 13:
			option = "Q";
			break;
		case 14:
			option = "A";
			break;
		default:
			option = String.valueOf(getRank());
			break;
		}

		if (hidden) return "***HIDDEN**";
		else		return option +" of "+ suit;
	}
	
	public boolean isHidden() {
		return this.hidden;
	}
	public void unHide() {
		this.hidden = false;
	}
	public void hide() {
		this.hidden = true;
	}

	public int getRank() {
		if (this.rank == 14) {
			rank = 11;
		}
		else if (10< this.rank && this.rank < 14) {
			rank = 10;
		}
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
