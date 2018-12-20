package blackhack;

import java.util.Scanner;

public class Game {
	Scanner inp = new Scanner(System.in);
	Player p = new Player();
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
					p.setCredentials(usr, pw);				
					if (db.login(p) == 1)
					{
						System.out.println("Welcome "+ p.getUsername()+"!");
						menu = false;
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

	public void gametime() {
		System.out.println("HÄR KOMMER SPELET ATT VARA");
		
	}
	
	

}
