package blackhack;

import java.util.Scanner;

public class BlackHack {

	public static void main(String[] args) {

		System.out.println("Välkommen till BlackJack\n [1] Logga in\n [2] Registrera\n [3] Avsluta");

		Scanner inp = new Scanner(System.in);
		String choice = inp.next();
		String username;
		String password;
		
		while (true) 
		{
			if (choice.equals("1"))
			{
				while(true) {
					// Kod för inloggning
					System.out.println("Skriv in ett användarnamn:");
					username = inp.next();
					// Kolla om användarnamn redan är taget
					int accepted = 0; // Sätts till 1 eller 0 returneras från en metod i databaser.
					if (accepted == 1)
					{
						System.out.println("Skriv in ett lösenord");
						password = inp.next();
					}
					else
				}
				
				
				
			}
			else if (choice.equals("2"))
			{
				// kod för registrering
			}
			else if (choice.equals("3"))
			{
				System.out.println("Du valde att avsluta programmet...");
				break;
			}
			else
			{
				System.out.println("Vänligen välj ett korrect alternativ");
			}
		}
			
			
			
	}
			

		
		
		// Huvudmeny för programmet, som ligger i en loop
		// Inoggning & registrering för att kunna spela.
		// Detta skall kollas mot en databas
		
		// Ytterligare en loop när man har loggat in.
		// Här ska spelaren få en ny meny för att kunna välja:
		// Spela, kolla crediter, avsluta spel. (kanske lite regler om man vill...)
		
		// crediter ska kollas från en databas och skriver ut på skärmen.
		// En undermeny för spela bör finnas, där det frågas hur mycket man vill satsa innan man får kort.

	}
