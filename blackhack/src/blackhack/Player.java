package blackhack;

import java.util.ArrayList;


class Player {
	private String username;
	private String password;
	private int credits;
	private int bet;
	private ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();

	private ArrayList<Card> hand = new ArrayList<Card>();
	
	int getPoints() {
		int sum = 0;
		int numberofA = 0;
		
		for(int i = 0; i < this.hand.size(); i++) {
			Card c = this.hand.get(i);
			if(c.getValue() >= 2 && c.getValue() <= 10) {
				sum += c.getValue();
			}
			else if(c.getValue() == 11){
				numberofA ++;
			}
		}
 
		if(numberofA != 0) {
			if(sum + (numberofA*11) > 21) {
				sum += numberofA;
			}
			else {
				 sum += 11;
			}
		}
		return sum;
	}

	int getDealerPoints() {
		return this.hand.get(1).getValue();
	}

	Card getFirstCard() {
		return hand.get(0);

	}
	void setCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	String getUsername() {
		return username;
	}

	void setUsername(String username) {
		this.username = username;
	}

	String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}
	
	ArrayList<Card> getHand() {
		return this.hand;
	}

	String getHandText() {

		StringBuilder builder = new StringBuilder();

		for (Card c: this.hand) {
			builder.append(c + ", ");
		}
		String handText = builder.toString();
		return handText;
	}
	
	void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	void addCardToHand(Card c) {
		this.hand.add(c);
	}

	void addCardToCurrentHand(Card c) {
		this.hand.add(c);
	}

	int getCredits() {
		return credits;
	}

	void setCredits(int credits) {
		this.credits = credits;
	}

	int getBet() {
		return bet;
	}

	void setBet(int bet) {
		this.bet = bet;
	}

	ArrayList<ArrayList<Card>> getHands() {
		return hands;
	}

	void addHandtoHands(ArrayList<Card> arrayList) {
		this.hands.add(arrayList);
	}
}