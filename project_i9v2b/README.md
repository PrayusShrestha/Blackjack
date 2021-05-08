# Blackjack

An implementation of the popular card game

## Who should play?
Anyone who wants to have a good time (and not risk real money!) is welcome to play. Anyone who wants to learn the rules is welcome to open up the application too.

## Why this?
I've always enjoyed playing card games. I started off playing Uno and Go Fish, and now have moved into playing and learning  poker and blackjack. Between those 2, blackjack made more sense for this project as in it, individual players go up against the house, whereas poker would require a multiplayer aspect as users would be playing each other.

## User Stories:
- As a user, I want to the virtual casino to know my name 
- As a user, I want to be able to learn the rules of Blackjack 
- As a user, I want to buy-in for an amount of my choosing
- As a user, I want know what cards I have 
- As a user, I want the game to add cards to the deck/shoe 
- As a user, I want to see my dealer's up-card
- As a user, I want to be able to control my bet size every hand 
- As a user, I want to be able to 'hit' and add a card to my hand 
- As a user, I want to be able to 'stand'
- As a user, I want to be paid when I have the better hand 
- As a user, I want my bankroll to update whenever I play 
- As a user, I want to play with my previously saved bankroll 
- As a user, I want to see the cards I have on another panel 
- As a user, I want to be able to load my previous bankroll and play with it
- As a user, I want to add cards to my hand and on its GUI
- As a user, I want to see the dealer's cards on a GUI
- As a user, I want to hear sounds when clicking certain buttons

## Phase 4: Task 2
Appropriately used the *Map* interface

## Phase 4: Task 3
Some possible refactor should be considered as follows:
- Since the Dealer and Player class share many of the same methods and fields, it would be better to have an abstract class to capture that behaviour and then have Dealer and Player extend the abstract class
- The Dealer and Player class have started to lose cohesion. This can be remedied by refactoring to get a separate Hand class, then have both Dealer and Class have a field of type Hand


## Credits
- Bicycle Cards for the how-to guide (https://bicyclecards.com/how-to-play/blackjack/).
- w3schools for the Java reference (https://www.w3schools.com/java/default.asp).
