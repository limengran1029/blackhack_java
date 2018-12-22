package blackhack;


public class BlackHack {

	public static void main(String[] args) {
		Game g = new Game();
		//g.start();		
		g.gamestart();

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

