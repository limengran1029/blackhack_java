package blackhack;

public class Card {
	private Suits suit;
	private Ranks rank;
	
	
	public Card(Suits suit, Ranks rank) {
		this.setSuit(suit);
		this.setRank(rank);
	}


	public Suits getSuit() {
		return suit;
	}


	public void setSuit(Suits suit) {
		this.suit = suit;
	}


	public Ranks getRank() {
		return rank;
	}


	public void setRank(Ranks rank) {
		this.rank = rank;
	}

}
