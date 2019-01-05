package blackhack;

import java.util.Scanner;

public class BlackHack {
	private final DBConnector db = new DBConnector();
	private Player player = new Player();
	private Scanner inp = new Scanner(System.in);
	
	BlackHack(){
		System.out.println("Welcome to Blackhack!\n[1]Login\n[2]Register\n[3]Exit");
		String option = inp.next();
		
		while(true) {
			if (option.equals("1")) {
				login();
				break;
			}
			else if (option.equals("2")) {
				register();
			}
			else if (option.equals("3")) {
				System.out.println("You're exiting the program..\nGoodbye and please come again!");
				break;
			}
			else {
				System.out.println("Please enter correct menu choice!");
				}
		}
	}
	
	private void login() {
		System.out.println("Enter username: ");
		String usr = inp.next();
		System.out.println("Enter password: ");
		String pw = inp.next();
		player.setCredentials(usr, pw);		
		if (db.login(player)) {
			System.out.println("Welcome "+ player.getUsername()+"!");
			Game game = new Game(player, inp, db);
			game.gameStart();
		}
		else {
			System.out.println("Incorrect username!");
		}
	}
	
	private void register() {
		System.out.println("Enter desired Username: ");
		String usr = inp.next();
		System.out.println("Enter desired Password: ");
		String pw = inp.next();
		player.setCredentials(usr, pw);				
		if (db.registerPlayer(player)) {
			System.out.println("You have successfully created your account!\n Account name: "+usr);
			login();
		}
		
	}
	
	public static void main(String[] args) {
		new BlackHack();
	}

}
