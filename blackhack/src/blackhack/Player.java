package blackhack;

import java.util.ArrayList;

public class Player {
	private String username;
	private String password;
	private int credits;

	private ArrayList<Card> hand = new ArrayList<Card>();

	public int getPoints(){
		int sum = 0;
		int numberofA = 0;

		for(int i = 0; i < this.hand.size(); i++){
			Card c = this.hand.get(i);
			if(c.newRank() >= 2 && c.newRank() <= 10){
				sum += c.newRank();
			}
			else if(c.newRank() == 11){
				numberofA ++;
			}
		}

		if(numberofA != 0){
			if(sum + (numberofA*11) > 21){
				sum += numberofA;
			}
			else{
				sum += 11;
			}
		}
		
		return sum;

	}

	public void setCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public void addCard(Card c) {
		this.hand.add(c);
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
}