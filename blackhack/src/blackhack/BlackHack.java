package blackhack;

import java.util.Scanner;

public class BlackHack {

	public static void main(String[] args) {

		System.out.println("V�lkommen till BlackJack\n [1] Logga in\n [2] Registrera\n [3] Avsluta");

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
				while(true) {
					// Kod f�r inloggning
					System.out.println("Skriv in ett anv�ndarnamn:");
					username = inp.next();
					// Kolla om anv�ndarnamn redan �r taget
					if (accepted == 1)
					{
						System.out.println("Skriv in ett l�senord");
						password = inp.next();
						// kolla om l�senord st�mmer med anv�ndare
						if (fullclearence == 1)
						{
							System.out.println("V�lkommen + h�r skriver vi in namn taget fr�n anv�ndare.");
							break;
						}
						else
						{
							System.out.println("Fel l�senord!");
							break;
						}
					}
					else
					{
						System.out.println("Fel anv�ndarnamn!");
					}
				}
				
				
				
			}
			else if (choice.equals("2"))
			{
				// kod f�r registrering
			}
			else if (choice.equals("3"))
			{
				System.out.println("Du valde att avsluta programmet...");
				break;
			}
			else
			{
				System.out.println("V�nligen v�lj ett korrect alternativ");
			}
		}
			
			
			
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
