package blackhack;

import java.util.Scanner;

public class BlackHack {

	public static void main(String[] args) {

		System.out.println("Welcome to Blackhack!\n[1]Login\n[2]Register\n[3]Exit");

		Scanner inp = new Scanner(System.in);
		String choice = inp.next();
		String username;
		String password;
		int fullclearence = 0; // S�tts till 1 eller 0 returneras fr�n metod i databaser.
		int accepted = 0; // S�tts till 1 eller 0 returneras fr�n en metod i databaser.
		
		
		while (true) 
		{
			if (choice.equals("1"))
			{
				while(true) 
				{
					// Kod f�r inloggning
					System.out.println("Enterusername: ");
					username = inp.next();
					// Kolla om anv�ndarnamn redan �r taget
					if (accepted == 1)
					{
						System.out.println("Enter password: ");
						password = inp.next();
						// kolla om l�senord st�mmer med anv�ndare
						if (fullclearence == 1)
						{
							System.out.println("Welcome! " + username + " Have fun Blackhacking!");
							break;
						}
						else
						{
							System.out.println("Wrong password!");
							break;
						}
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
					
					if(accepted==1)
					{
				
					System.out.println("Enter desired Password: ");
					password = inp.next();
					
					if(fullclearence==1)
					{
						System.out.println(username + " your account has been created, have fun playing!");
						break;
					}
					else
						System.out.println("no password entered");
						break;
				
					}
				else
					System.out.println("Username exists already!");
					break;
				}	
				
				
				
				// kod f�r registrering
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
