package blackhack;

import java.util.Scanner;

public class BlackHack {

	public static void main(String[] args) {
		Player p = new Player();
		final DBConnector db = new DBConnector();
		System.out.println("Welcome to Blackhack!\n[1]Login\n[2]Register\n[3]Exit");

		Scanner inp = new Scanner(System.in);
		String choice = inp.next();
		String username;
		String password;

		while (true) 
		{
			if (choice.equals("1"))
			{
				while(true) 
				{
					System.out.println("Enter username: ");
					username = inp.next();
					System.out.println("Enter password: ");
					password = inp.next();
					p.setCredentials(username, password);
					
					if (db.login(p) == 1)
					{
						System.out.println("Welcome "+ p.getUsername()+"!");
						break;
					}
					else
					{
						System.out.println("Wrong username!");
						break;
					}
				}
				
				
				
			}
			else if (choice.equals("2"))
			{
				while(true) 
				{
					System.out.println("Enter desired Username: ");
					username = inp.next();
					System.out.println("Enter desired Password: ");
					password = inp.next();
					p.setCredentials(username, password);
					
					if (db.registerPlayer(p) == 1) {
						System.out.println("You have successfully created your account!\n Account name: "+username);
					}
					else {
						System.out.println("Username already taken!");
					}
				
				}	
				
			}
			else if (choice.equals("3"))
			{
				System.out.println("You're exiting the program..\nGoodbye and please come again!");
				break;
			}
			else
			{
				System.out.println("Please enter correct menu choice!");
			}
		}
			
			
			inp.close();
	}
			

		
		
		// Huvudmeny f�r programmet, som ligger i en loop
		// Inoggning & registrering f�r att kunna spela.
		// Detta skall kollas mot en databas
		
		// Ytterligare en loop n�r man har loggat in.
		// H�r ska spelaren f� en ny meny f�r att kunna v�lja:
		// Spela, kolla crediter, avsluta spel. (kanske lite regler om man vill...)
		
		// crediter ska kollas fr�n en databas och skriver ut p� sk�rmen.
		// En undermeny f�r spela b�r finnas, d�r det fr�gas hur mycket man vill satsa innan man f�r kort.

	}
