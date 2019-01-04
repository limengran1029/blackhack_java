package blackhack;

import java.util.Scanner;

public class BlackHack {

	public static void main(String[] args) {
		
		final DBConnector db = new DBConnector();
		Player player = new Player();
		Scanner inp = new Scanner(System.in);
		
		System.out.println("Welcome to Blackhack!\n[1]Login\n[2]Register\n[3]Exit");
		Boolean menu = true;
		String option = inp.next();

		while(menu)
			if (option.equals("1")) {
				System.out.println("Enter username: ");
				String usr = inp.next();
				System.out.println("Enter password: ");
				String pw = inp.next();
				player.setCredentials(usr, pw);		
				
				if (db.login(player)) {
					System.out.println("Welcome "+ player.getUsername()+"!");
					menu = false;
					Game game = new Game(player, inp, db);
					while (game.run)
						game.gameStart();
				}
				else {
					System.out.println("Communications link failure");
				}
			}
			else if (option.equals("2")) {
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
			else if (option.equals("3")) {
				System.out.println("You're exiting the program..\nGoodbye and please come again!");
				menu = false;
			}
			else {
				System.out.println("Please enter correct menu choice!");
				}				
			}
}