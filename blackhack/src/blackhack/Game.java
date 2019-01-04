package blackhack;

import java.util.Scanner;

public class Game {
	Scanner inp = new Scanner(System.in);
	Player player = new Player();
	Player dealer = new Player();
	Deck d = new Deck();
	Player[] players;

	final DBConnector db = new DBConnector();

	public void start() {
		System.out.println("Welcome to Blackhack!\n[1]Login\n[2]Register\n[3]Exit");
		menuOptions(inp.next());
	}
	
	private void menuOptions(String option) {
		
		dealer.setUsername("Dealer");

		Boolean menu = true;

		while(menu)
			if (option.equals("1"))
			{
					System.out.println("Enter username: ");
					String usr = inp.next();
					System.out.println("Enter password: ");
					String pw = inp.next();

					player.setCredentials(usr, pw);				

					if (db.login(player))
					{
						System.out.println("Welcome "+ player.getUsername()+"!");
						menu = false;
						gameStart();
					}
					else
					{
						System.out.println("Communications link failure");
					}
				}

			else if (option.equals("2"))
			{
					System.out.println("Enter desired Username: ");
					String usr = inp.next();
					System.out.println("Enter desired Password: ");
					String pw = inp.next();
					player.setCredentials(usr, pw);				
					if (db.registerPlayer(player)) {
						System.out.println("You have successfully created your account!\n Account name: "+usr);
						break;
					}
					else {
						System.out.println("See error code");
					}
				
		}
		else if (option.equals("3"))
		{
			System.out.println("You're exiting the program..\nGoodbye and please come again!");
			menu = false;
		}
		else
		{
			System.out.println("Please enter correct menu choice!");
		}
		
	}
	
	private void bet() {
		System.out.println("Your balance is: " + db.getPlayerCredit(player));
		System.out.println("How much would you like to bet?");
		String betamount = inp.next();
		try {
			int betam = Integer.parseInt(betamount);
			if (db.getPlayerCredit(player) >= betam) {
				db.updateCredits(player, db.getPlayerCredit(player)-betam);
				player.setBet(betam);
				gameLogic();
			}
			else {
				System.out.println("Insufficient credits, please add some before playing!");
				gameStart();
			}			
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}
	}
	
	private void addCredits() {
		System.out.println("You have: " +db.getPlayerCredit(player)+ " credits.");
		System.out.println("How much would you like to add?");
		String credits = inp.next();
		try {
			int creds = Integer.parseInt(credits);
			db.updateCredits(player, db.getPlayerCredit(player)+creds);
			System.out.println("You have successfully added " + creds + " to your account.");
			System.out.println("Your new balance is: " + db.getPlayerCredit(player));			
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}

	}
	

	public void gameStart() {
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("[1] Play BlackJack\n[2] Add credits\n[3] Read rules\n[4] Exit");
			switch (inp.next()) {
			case "1":
				bet();
				break;
			case "2":
				addCredits();
				break;
			case "3":
				bjRules();
				break;
			case "4":
				System.out.println("You have quit.");
				isRunning = false;
				break;
			default:
				System.out.println("Choose a correct alternative");
				break;
			}
		}
	}
	
	private void gameLogic() {		


		System.out.println("\nGame start!\n");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e){} 
		
		firstDeal(d);
		if (player.getPoints() == 21) System.out.println("Congratulations you won");
		else hitStandDoubleSplit();

		// Round is over
		System.out.println("See you next time!\n");
		gameStart();
	}

	private void firstDeal(Deck d) {
		// Clear hands
		player.getHand().clear();
		dealer.getHand().clear();
		
		// Deal two cards
		for (int i = 0; i < 2; i++) {
			dealer.addCardToHand(d.drawCard());
			player.addCardToHand(d.drawCard());
		}
		// hides dealers first card
		dealer.getFirstCard().hide();
		System.out.println("Dealer: " + dealer.getHandText() + " Total: " + dealer.getDealerPoints());
		System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());	
	}
	
	private void hitStandDoubleSplit() {
		//boolean doubleRound = false;
		while (true) {
			System.out.println("\n[1] Hit | "
							 + "[2] Stand | "
							 + "[3] Double | "
							 + "[4] Split\n");

			String choice = inp.next();
			
			switch (choice) {
			case "1":
				hit(player);
				break;
			case "2":
				while(dealer.getPoints() < 17)
					hit(dealer);
				break;
			case "3":
				playDouble();
				break;
			case "4":
				break;
			default:
				System.out.println("Not a valid option");
				break;
			}

			// If there is a winner/loser break loop return to menu
			boolean won = checkWinLose();
			if(won)
				break;
		}
		
	}
	
	private void hit(Player p) {

		String choice = null;
		Card c = d.drawCard();
		p.addCardToHand(c);
		System.out.println(p.getUsername() + "Drew: " + c);

		if (p == player && p.getPoints() < 22) {
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

		while(dealer.getPoints() < 17)
			hit(dealer);
	}
	
	private boolean checkWinLose() {

		dealer.getFirstCard().unHide();

		System.out.println("Dealer: " + dealer.getHandText() + " Total: " + dealer.getPoints());
		System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());	

		if (player.getPoints() > 21) {
			System.out.println("You bust!");
			return true;
		}
		else if (dealer.getPoints() > 21) {
			System.out.println("Dealer busts");
			System.out.println("You win!!!!");
			db.updateCredits(player, player.getCredits() + (player.getBet() * 2));
			return true;
		}
		else if (player.getPoints() == dealer.getPoints()) {
			System.out.println("It's a tie");
			db.updateCredits(player, db.getPlayerCredit(player) + (player.getBet()));
			return true;
		}
		else if (player.getPoints() > dealer.getPoints()) {
			db.updateCredits(player, db.getPlayerCredit(player) + (player.getBet() * 2));
			System.out.println("You won!");
			return true;
		}
		else if(dealer.getPoints() > player.getPoints()) {
			System.out.println("You lost!");
			return true;
		}
		else {
			return false;
		}
	}

	/*
	private void printResult(Player p, Player d) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){} 
		System.out.println("\nFinal results: ");
		System.out.println("Dealer score: "+d.getPoints());
		System.out.println("Your score: "+p.getPoints());
	}
	*/
	
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
