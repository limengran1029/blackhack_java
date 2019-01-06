package blackhack;

import java.util.ArrayList;
import java.util.Scanner;

class Game {
	private Player player;
	private DBConnector db;
	private Scanner inp;
	private Player dealer = new Player();
	private Deck d = new Deck();
	
	Game(Player p, Scanner inp, DBConnector db){
		this.player = p;
		this.inp = inp;
		this.db = db;
		this.dealer.setUsername("Dealer");
	}

	private boolean bet() {
		System.out.println("How much would you like to bet?");
		int betam = 0;

		while (true) {
			try {
				String betamount = inp.next();
				betam = Integer.parseInt(betamount);
				break;
			} catch (Exception e) {
				System.out.println("Invalid input!");
			}
		}

		if (db.getPlayerCredit(player) >= betam) {
			db.updateCredits(player, db.getPlayerCredit(player)-betam);
			player.setBet(betam);
			return true;
		}

		else {
			System.out.println("Insufficient credits, please add some before playing!");
			return false;
		}			
	}
	
	private boolean addCredits() {
		System.out.println("You have: " +db.getPlayerCredit(player)+ " credits.");
		System.out.println("How much would you like to add?");
		String credits = inp.next();
		
		while (true) {
			try {
				int creds = Integer.parseInt(credits);
				db.updateCredits(player, db.getPlayerCredit(player)+creds);
				System.out.println("You have successfully added " + creds + " to your account.");
				System.out.println("Your new balance is: " + db.getPlayerCredit(player));	
				return true;
			} catch (Exception e) {
				System.out.println("Invalid input!");
			}
		}

	}
	

	void gameStart() {
		gameStart: while (true) {
			System.out.println("[1] Play BlackJack\n[2] Add credits\n[3] Read rules\n[4] Exit");
			switch (inp.next()) {
			case "1":
				System.out.println("Your balance is: " + db.getPlayerCredit(player));
				boolean betAccept = bet();
				if (betAccept) {
					break gameStart;
				}
				break;
			case "2":
				addCredits();
				break;
			case "3":
				bjRules();
				break;
			case "4":
				System.out.println("Exited");
				System.exit(0);
				break;
			default:
				System.out.println("Choose a correct alternative");
				break;
			}
		}
	gameLogic();
	}
	
	private void gameLogic() {		

		System.out.println("\nGame start!\n");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e){} 
		
		firstDeal();
		if (player.getPoints() == 21) {
			System.out.println("Congratulations you won");
			db.updateCredits(player, db.getPlayerCredit(player) + player.getBet() * 2);
		}
		else deal();

		// Round is over
		System.out.println("See you next time!\n");
		gameStart();
	}

	private void firstDeal() {
		// Clear hands
		player.getHand().clear();
		dealer.getHand().clear();
		player.getHands().clear();
		
		// Deal two cards
		for (int i = 0; i < 2; i++) {
			dealer.addCardToHand(d.drawCard());
			player.addCardToHand(d.drawCard());
		}
		// hides dealers first card
		dealer.getFirstCard().hide();
		
		// insert first hand into handlist
		player.addHandtoHands(player.getHand());

		System.out.println("Dealer: " + dealer.getHandText() + " Total: " + dealer.getDealerPoints());
		System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());	
	}
	
	private void deal() {
		boolean enableSplit = false;
			
		while (true) {

			System.out.print("\n[1] Hit | "
							 + "[2] Stand | "
							 + "[3] Double");
			if(player.getHand().get(0).getRank() ==  player.getHand().get(1).getRank()) {
				System.out.println(" | [4] Split");
				enableSplit = true;
			}

			String choice = inp.next();

			if (choice.equals("1")) {
				hit(player);
			}
			else if(choice.equals("2")) {
				while(dealer.getPoints() < 17)
					hit(dealer);
			}
			else if(choice.equals("3")) {
				playDouble();
			}
			else if(enableSplit && choice.equals("4")) {
				playSplit(player);
				hit(player);
			}
			else {
				System.out.println("Not a valid option");
			}

			checkWinLose();

			if(player.getHands().size() == 0) {
				break;
			}
			else {
				player.setHand(player.getHands().get(0));
				//player.setHand(player.getHands().remove(0));

				System.out.println("Dealer: " + dealer.getHandText() + " Total: " + dealer.getDealerPoints());
				System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());	
			}
		}

		dealer.getFirstCard().unHide();
		printHands(player, dealer);
		System.out.println("Good bye!! :>");
	}
	
	private void hit(Player p) {
		String choice = null;
		Card c = d.drawCard();
		p.addCardToHand(c);
		System.out.println("\n" + p.getUsername() + " Drew: " + c + "\n");

		if (p == player && p.getPoints() < 21) {
			System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());	
			while(true) {
				System.out.println("\n[1] Hit | "
									+ "[2] Stand");
				choice = inp.next();
				if (choice.equals("1") || choice.equals("2"))
					break;
			}
			
			if(choice.equals("1")) {
				hit(player);
			}
			else if (choice.equals("2")) {
				while(dealer.getPoints() < 17)
					hit(dealer);
			}
		}
	}

	
	private void playDouble() {
		
		// remove double cash, and double bet. transfers happens in checkwin
		db.updateCredits(player, db.getPlayerCredit(player) - player.getBet());
		player.setBet(player.getBet() * 2);

		System.out.println("You choose to double, you get one card.");

		player.addCardToHand(d.drawCard());

		if(player.getPoints() < 22) {
			while(dealer.getPoints() < 17)
				hit(dealer);
		}
	}
	private void playSplit(Player p) {
		//temp array for splitting cards
		ArrayList<Card> tempHand = new ArrayList<Card>();

		// remove first card in first hand
		Card tempCard = p.getHand().remove(0);
		tempHand.add(tempCard);
		// add that hand with first card to hands
		p.addHandtoHands(tempHand);
		// add first hand to the list
		p.addHandtoHands(p.getHand());
		/*
		for(ArrayList<Card> hand : p.getHands()) {

			p.setHand(hand);

			hit(player);
			
			//checkWinLose();
		}
		// split game done
		// clear hands
		 */

	}
		

	private void checkWinLose(){
		//dealer.getFirstCard().unHide();

		//printHands(player, dealer);
		//boolean finnished = false;

		if (player.getPoints() > 21) {
			System.out.println("\nYou bust!");
		}
		else if (dealer.getPoints() > 21) {
			System.out.println("\nDealer busts");
			System.out.println("\nYou win!!!!");
			db.updateCredits(player, db.getPlayerCredit(player) + (player.getBet() * 2));
		}
		else if (player.getPoints() == dealer.getPoints()) {
			System.out.println("\nIt's a tie");
			db.updateCredits(player, db.getPlayerCredit(player) + (player.getBet()));
		}
		else if (player.getPoints() > dealer.getPoints()) {
			db.updateCredits(player, db.getPlayerCredit(player) + (player.getBet() * 2));
			System.out.println("\nYou won!");
		}
		else if(dealer.getPoints() > player.getPoints()) {
			System.out.println("\nYou lost!");
		}

		player.getHands().remove(0);
	}
	
	private void printHands(Player p, Player d) {
		System.out.println("Dealer: " + d.getHandText() + " Total: " + d.getPoints());
		System.out.println("Your hand: " + p.getHandText() + " Total: " + p.getPoints());	
	}

	private void bjRules() {
		System.out.println("Black Jack Rules:\n"+
				"In black Jack the goal is to hit 21 or get as close to as possible\n"+
				"The game start by giving you two cards and the dealer shows one.\n"+
				"You then have the option to get another card (hit) or stand.\n"+
				"If you chose to hit:\n"+
				"You get another card and the option to hit again or stand.\n"+
				"You may hit as many times as you like, but if you exceed 21 your auto lose.\n"+
				"If you chose to stand:\n"+
				"The dealer shows her next card, if the dealer has 17 or higher she must stand.\n"+
				"If the dealer shows 16 or lower she must draw cards until she gets greater than 17 or busts.\n"+
				"Do not spend money you cannot afford losing.\n");
	}	
}