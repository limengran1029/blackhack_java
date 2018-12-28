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
					if (db.login(player) == 1)
					{
						System.out.println("Welcome "+ player.getUsername()+"!");
						menu = false;
					}
					else if (db.registerPlayer(player) == 2) {
						System.out.println("Communications link failure");
					}
					else
					{
						System.out.println("Wrong username!");
					}
				}
			else if (option.equals("2"))
			{
					System.out.println("Enter desired Username: ");
					String usr = inp.next();
					System.out.println("Enter desired Password: ");
					String pw = inp.next();
					player.setCredentials(usr, pw);				
					if (db.registerPlayer(player) == 1) {
						System.out.println("You have successfully created your account!\n Account name: "+usr);
						break;
					}
					else if (db.registerPlayer(player) == 2) {
						System.out.println("Communications link failure");
					}
					else {
						System.out.println("Username already taken!");
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

	public void gameStart() {

		while (true) {			
			gameLogic();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e){} 

			System.out.println("\n[1]Continu\n[2]Exit");
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

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e){} 

		Boolean value = true;

		while (value) {


			for (int i = 0; i < 2; i++) {

				dealer.addCard(d.drawCard());
				player.addCard(d.drawCard());
			}
			
			System.out.println("Computers card: [******************, "+dealer.getHand().get(1)+"]");
			//System.out.println("Your card     : "+player.getHand()+" | points: " +player.getPoints());
			player.printHandandPoints();

			while (true){

				System.out.println("hit or stand? (h/s) ");
				String choice = inp.next();

				if (choice.equals("h") || choice.equals("H")) {

					player.addCard(d.drawCard());
					System.out.println("Computers card: [******************, "+dealer.getHand().get(1)+"]");
					//System.out.println("Your card     : "+player.getHand());
					player.printHandandPoints();
					
					if (player.getPoints() > 21) {
						System.out.println("You are loser!");
						printResult(player, dealer);
						dealer.getHand().clear();
						player.getHand().clear();
						value = false;
					}
				}
				else if (choice.equals("s") || choice.equals("S")) {
					while (dealer.getPoints() < 17) 
					{
						dealer.addCard(d.drawCard());
					}

					if (dealer.getPoints() > 21 || player.getPoints() > dealer.getPoints()) 
					{
						System.out.println("You are winner!");						
					}
					else if (player.getPoints() == dealer.getPoints()) 
					{
						System.out.println("It is tie!");						
					}
					else 
					{
						System.out.println("You are loser!");
					}

					printResult(player, dealer);

					dealer.getHand().clear();

					player.getHand().clear();

					break;
				}
				else {
					System.out.println("Please enter the right option");
				}
				
			}
		}		 
		
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
