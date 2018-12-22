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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e){} 
			System.out.println("\n[1]Continu\n[2]Exit");
			String choice = inp.next();
			if (choice.equals("2")) {
				continu = false;
				System.out.println("Thanks for playing, se you again!");
			}
			
		}
	}
	
	private void gamelogic() {		
		System.out.println("\nGame start!");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e){} 
		Boolean value = true;
		while (value) {
			Card cardc, cardh;
			Deck d = new Deck();
			for (int i = 0; i < 2; i++) {
				cardc = d.drawCard();			
				computer.add(cardc);
				cardh = d.drawCard();
				human.add(cardh);
			}
			
			System.out.println("Computers card: [******************, "+computer.get(1)+"]");
			System.out.println("Your card     : "+human);

			while (value){
				System.out.println("hit or stand? (h/s) ");
				String choice = inp.next();
				if (choice.equals("h") || choice.equals("H")) {
					cardh = d.drawCard();
					human.add(cardh);					
					System.out.println("Computers card: [******************, "+computer.get(1)+"]");
					System.out.println("Your card     : "+human);
					
					if (point(human) > 21) {
						System.out.println("You are loser!");
						printresult();
						computer.clear();
						human.clear();
						value = false;
					}
				}
				else if (choice.equals("s") || choice.equals("S")) {
					while (point(computer) < 17) {
						cardc = d.drawCard();
						computer.add(cardc);
					}

					if (point(computer) > 21 || point(human) > point(computer)) {
						System.out.println("You are winner!");						
					}
					else if (point(human) == point(computer)) {
						System.out.println("It is tie!");						
					}
					else {
						System.out.println("You are loser!");
					}
					printresult();
					computer.clear();
					human.clear();
					value = false;
				}
				else {
					System.out.println("Please enter the right option");
				}
				
			}
		}		 
		
	}
	
	
	
	private int point(ArrayList<Card> player){
		int sum = 0;
		int numberofA = 0;

		for(int i = 0; i < player.size(); i++){
			Card c = player.get(i);
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

	private void printresult() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e){} 
		System.out.println("\nFinal result: ");
		System.out.println("Computer's point: "+point(computer)+"\nComputer card : "+computer);
		System.out.println("Your point      : "+point(human)+"\nYour card     : "+human);
	}
}
