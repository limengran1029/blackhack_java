package blackhack;

class Card {

	static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	static final int[] RANKS = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

	private String suit;
	private int rank;
	
	private boolean hidden;

	Card(String suit, int rank) {
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
	
	boolean isHidden() {
		return this.hidden;
	}
	void unHide() {
		this.hidden = false;
	}
	void hide() {
		this.hidden = true;
	}
	int getRank() {
		return rank;
	}



	public int newRank( ) {
		int newrank;
		if (rank == 14) {
			newrank = 11;
		}
		else if (10< rank && rank < 14) {
			newrank = 10;
		}
		else {
			newrank = rank;
		}
		return newrank;
	}

	void setRank(int rank) {
		this.rank = rank;
	}
	
}
