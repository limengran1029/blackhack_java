package blackhack;

import java.util.Scanner;

public class Game {
	Scanner inp = new Scanner(System.in);
	Player player = new Player();
	Player dealer = new Player();
	final DBConnector db = new DBConnector();

	public void start() {
		System.out.println("Welcome to Blackhack!\n[1]Login\n[2]Register\n[3]Exit");
		menuOptions(inp.next());
	}
	
	private void menuOptions(String option) {

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
		}
		else
		{
			System.out.println("Please enter correct menu choice!");
		}
		
	}
	
	private boolean bet() {
		System.out.println("How much would you like to bet?");
		int betamount = inp.nextInt();
		if (db.getPlayerCredit(player) >= betamount) {
			db.updateCredits(player, db.getPlayerCredit(player)-betamount);
			return true;
		}
		else {
			return false;
		}
	}

	public void gameStart() {
		while (true) {
			int credits = db.getPlayerCredit(player);
			System.out.println("You have " + credits + " credits.");
			while (bet()) {
				gameLogic();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e){} 
			}
			System.out.println("You dont have enough credits\n[1]Place new bet\n[2]Exit");
			String choice = inp.next();
			if (choice.equals("2")) {
				System.out.println("Thanks for playing, se you again!");
				break;
			}
		}
	}
	
	private void gameLogic() {		

		Deck d = new Deck();

		System.out.println("\nGame start!");
		boolean game = true;
		boolean hitStand = true;

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e){} 

		do {

			// Draw 2 cards for players
			for (int i = 0; i < 2; i++) {
				dealer.addCardToHand(d.drawCard());
				player.addCardToHand(d.drawCard());
			}

			//Print first hands
			System.out.println("Dealer: " + dealer.getHand().get(0) + " Total: " + dealer.getDealerPoints());
			System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());

			//Check if first hands are 21
			if (player.getPoints() == 21) {
				System.out.println("Congratulations you won");
				game = false;
			}
			else {
				while (hitStand){

					System.out.println("Hit or stand? (h/s) ");
					String choice = inp.next();

					if (choice.toLowerCase().equals("h")) {

						player.addCardToHand(d.drawCard());
						System.out.println("Dealer: " + dealer.getHand().get(0) + " Total: " + dealer.getDealerPoints());
						System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());

						if (player.getPoints() > 21) {
							System.out.println("You bust");
							System.out.println("Your hand: " + player.getHandText() + " Total: " + player.getPoints());

							dealer.getHand().clear();
							player.getHand().clear();
							game = false;
						}
					}
					else if (choice.toLowerCase().equals("s")) {
						while (dealer.getPoints() < 17) {
							dealer.addCardToHand(d.drawCard());
						}

						if (dealer.getPoints() > 21 || player.getPoints() > dealer.getPoints()) {
							System.out.println("You won!");						
							db.updateCredits(player, 20);
							printResult(player, dealer);
							game = false;
						}

						else if (player.getPoints() == dealer.getPoints()) {
							System.out.println("It is tie!");						
							printResult(player, dealer);
							game = false;
						}
						else {
							System.out.println("You lose!");
							printResult(player, dealer);
							game = false;
						}

						printResult(player, dealer);

						dealer.getHand().clear();

						player.getHand().clear();
					}
					else {
						System.out.println("Please enter the right option");
					}
				}
			}
		} while (game);
	}		 

	private void printResult(Player p, Player d) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){} 
		System.out.println("\nFinal result: ");
		System.out.println("Computer's point: "+d.getPoints()+"\nComputer card : "+d.getHand());
		System.out.println("Your point      : "+p.getPoints()+"\nYour card     : "+p.getHand());
	}
}
