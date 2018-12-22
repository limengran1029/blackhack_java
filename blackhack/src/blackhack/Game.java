package blackhack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	Scanner inp = new Scanner(System.in);
	Player p = new Player();
	final DBConnector db = new DBConnector();
	private ArrayList<Card> computer = new ArrayList<Card>();
	private ArrayList<Card> human = new ArrayList<Card>(); 


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
					p.setCredentials(usr, pw);				
					if (db.login(p) == 1)
					{
						System.out.println("Welcome "+ p.getUsername()+"!");
						menu = false;
					}
					else if (db.registerPlayer(p) == 2) {
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
					p.setCredentials(usr, pw);				
					if (db.registerPlayer(p) == 1) {
						System.out.println("You have successfully created your account!\n Account name: "+usr);
						break;
					}
					else if (db.registerPlayer(p) == 2) {
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

	public void gamestart() {
		boolean continu = true;
		while (continu) {			
			gamelogic();
			System.out.println("[1]Continu\n[2]Exit");
			String choice = inp.next();
			if (choice.equals("2")) {
				continu = false;
				System.out.println("Thanks for playing, se you again!");
			}
			
		}
	}
	
	private void gamelogic() {		
		System.out.println("Game start!");
		Boolean value = true;
		while (value) {
			Card cardc0,cardc,cardh;
			int sumc=0,sumh=0;
			Deck d = new Deck();
			cardc0 = d.drawCard();
			cardc = d.drawCard();
			computer.add(cardc0);
			computer.add(cardc);
			sumc = cardc0.newRank() + cardc.newRank();
			for (int i = 0; i < 2; i++) {
				cardh = d.drawCard();
				human.add(cardh);
				sumh += cardh.newRank();
			}	
			System.out.println("Computers card: [******************, "+cardc+"]");
			System.out.println("Your card     : "+human);

			do{
				System.out.println("hit or stand? (h/s) ");
				String choice = inp.next();
				if (choice.equals("h") || choice.equals("H")) {
					cardh = d.drawCard();
					human.add(cardh);
					sumh += cardh.newRank();
					System.out.println("Computers card: [******************, "+cardc+"]");
					System.out.println("Your card     : "+human);
					
					if (sumh>21) {
						System.out.println("You are loser!");
						System.out.println("Final result: ");
						System.out.println("Computer card : "+computer);
						System.out.println("Your card     : "+human);
						computer.clear();
						human.clear();
						value = false;
					}
				}
				else if (choice.equals("s") || choice.equals("S")) {
					System.out.println("Computer card : "+computer);
					
					while (sumc<17) {
						cardc = d.drawCard();
						computer.add(cardc);
						sumc += cardc.newRank();
					}

					if (sumc>21 || sumh>sumc) {
						System.out.println("You are winner!");						
					}
					else if (sumh==sumc) {
						System.out.println("It is tie!");						
					}
					else {
						System.out.println("You are loser!");
					}
					System.out.println("Final result: ");
					System.out.println("Computer card : "+computer);
					System.out.println("Your card     : "+human);
					computer.clear();
					human.clear();
					value = false;
				}
				else {
					System.out.println("Please enter the right option");
				}
				
			}while (value);
		}
		 
		
	}
	
	

}
