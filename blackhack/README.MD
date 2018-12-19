# DB structure table user
- id
- namn
- pass
- credit

# Funktioner
- Registrerings funktion som är kopplad till database
- Credits

# Classes
- BlackHack main klass
- - Här lever menyn och sånt som printas
- DB klass
- - DB klassen ska ta emot ett player objekt för att spara ned information till databas
- Deck klass
- - Deck klassen innehåller alla kort
- Card klassen
- - Hämtar kort från deck klassen.
- Player klass

# MENYER

1. Login
2. Register 

Username:
Password:

# Game logic
split?

==Rounds==
Hello name you have 2234 cred.
1. Add credits
2. Play
3. Exit

	==Play==
	Credit: 100
	How much to bet?

	1. take another
	2. bet
	3. double


2-9
10
J = 10
D = 10
K = 10
A = 1 | 11

('Black Jack Rules:\n'
              'In black Jack the goal is to hit 21 or get as close to as possible\n'
              'The game start by giving you two cards and the dealer shows one.\n'
              'You then have the option to get another card (hit) or stand.\n'
              'If you chose to hit:\n'
              'You get another card and the option to hit again or stand.\n'
              'You may hit as many times as you like, but if you exceed 21 your auto lose.\n'
              'If you chose to stand:\n'
              'The dealer shows her next card, if the dealer has 17 or higher she must stand.\n'
              'If the dealer shows 16 or lower she must draw cards until she gets greater than 17 or busts.\n'
              'Do not spend money you cannot afford losing.\n')
