package ui;

import model.Card;
import model.Dealer;
import model.Deck;
import model.Player;
import org.json.JSONException;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *
 * Gameplay for blackjack
 *
 */
public class Blackjack {
    private Scanner scanner;
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private Reader reader;
    private Writer writer;
    static final String ROUTE_TO_DATA = "./data/players.json";
    static final int MIN_BET_SIZE = 1;
    static final int SMALLEST_PLAYABLE_DECK_SIZE = 10;

    // EFFECTS: sets up and start game
    public Blackjack() {
        scanner = new Scanner(System.in);   // Credits to https://www.w3schools.com/java/java_user_input.asp for scanners info
        dealer = new Dealer();
        deck = new Deck();

        setup();
    }

    // MODIFIES: player
    // EFFECTS: sets up player name
    private void setup() {
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        reader = new Reader(ROUTE_TO_DATA);

        try {
            int bankroll = reader.read(name);
            player = new Player(name, bankroll);
        } catch (IOException e) {
            player = new Player(name, 0);
        } catch (JSONException e) {
            player = new Player(name, 0);
        }


        System.out.println("Hi, " + player.getName() + "! Welcome to the blackjack table");  // Output user input

        playOrLearn();
    }


    // REQUIRES: user input must be an integer
    // EFFECTS: player can choose to learn or play BlackJack
    public void playOrLearn() {
        System.out.println("Press 1 to sit and play. Press 2 to learn the rules.");
        int choice  = scanner.nextInt();

        if (choice == 1) {
            preGame();
        } else if (choice == 2) {
            learnBlackJack();
        } else {
            System.out.println("Sorry, that wasn't a valid choice.");
            playOrLearn();
        }

    }

    // EFFECTS: gives player a link with the rules. Also tells them about the house rules
    private void learnBlackJack() {
        String link = "https://bicyclecards.com/how-to-play/blackjack/";    // Credits to Bicycle Cards for this tutorial
        System.out.println("Here's a helpful link to learn the game: " + link);
        System.out.println("Please note that we have a few house rules: ");
        System.out.println("Our tables do not allow for splitting, doubling down, or getting insurance.");

    }

    // EFFECTS: takes the player to go and add money into their bankroll
    private void preGame() {
        if (player.getBankroll() == 0) {
            System.out.println("Your bankroll is currently empty. Let's add money into it.");
            addFunds();
        } else {
            System.out.println("Let's head to the table and play!");
            play();
        }

    }

    // EFFECTS: deposits the given amount of money into bankroll
    //          tells them how much money is in their bankroll
    private void addFunds() {
        System.out.println("How much would you like to add into your bankroll?");
        int amount  = scanner.nextInt();

        if (amount <= 0) {
            System.out.println("The amount you add must be a positive integer");
            addFunds();
        }
        player.addToBankroll(amount);


        System.out.println("Just added $" + amount + " into your bankroll.");
        System.out.println("Your current bankroll is: $" + player.getBankroll());
        System.out.println("Let's head back to the table's now and play!");
        play();
    }

    // EFFECTS: user plays blackjack
    //          deck gets set up
    private void play() {
        deck.addDeck();
        while (player.getBankroll() >= MIN_BET_SIZE) {
            System.out.println("Your current bankroll is $" + player.getBankroll());
            System.out.println("How much would you to bet? Minimum is $" + MIN_BET_SIZE);
            int amount  = scanner.nextInt();
            player.setCurrBetSize(amount);
            if (amount < MIN_BET_SIZE) {
                System.out.println("Sorry, your bet is too small.");
                System.out.println("Remember: the minimum bet size is: $" + MIN_BET_SIZE);
                play();
            } else if (amount > player.getBankroll()) {
                System.out.println("You don't have that type of money. Bet smaller!");
                play();
            } else {
                deal();
            }
        }
        System.out.println("It looks like you've run out of money. Let's top-up your bankroll");
        addFunds();
    }

    // EFFECTS: empties the hands of the dealer and player.
    //          new cards are dealt to the player and dealer.
    private void deal() {
        player.setHand(new ArrayList<Card>());
        dealer.setHand(new ArrayList<Card>());


        if (deck.getDeck().size() < SMALLEST_PLAYABLE_DECK_SIZE) {
            System.out.println("Sorry for the delay. We're adding cards to the shoe.");
            deck.addDeck();
        }

        player.addCard(deck.getRandCard());
        player.addCard(deck.getRandCard());
        dealer.addCard(deck.getRandCard());
        dealer.addCard(deck.getRandCard());

        System.out.println("Your cards: ");
        System.out.println(player.getHand().get(0).getName() + " of " + player.getHand().get(0).getSuit());
        System.out.println(player.getHand().get(1).getName() + " of " + player.getHand().get(1).getSuit());
        System.out.println("The dealer's up card: ");
        System.out.println(dealer.getHand().get(1).getName() + " of " + dealer.getHand().get(1).getSuit());

        playerMove();
    }

    // EFFECTS: player gets a choice of hitting or standing
    private void playerMove() {
        player.updateHandValue();

        if (player.isBust()) {
            playerBust();
        } else {
            System.out.println("Press 'h' to hit, or 's' to stand.");
            String hitOrStand  = scanner.next();
            if (hitOrStand.equals("h")) {
                hit();
            } else if (hitOrStand.equals("s")) {
                stand();
            } else {
                System.out.println("Sorry, that is an invalid entry. Let's try again.");
                playerMove();
            }
        }
    }

    // MODIFIES: Player
    // EFFECTS: adds card to player's hand
    private void hit() {
        Card newCard = deck.getRandCard();
        player.addCard(newCard);
        System.out.println("Your hand:");
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println(player.getHand().get(i).getName() + " of " + player.getHand().get(i).getSuit());
        }
        playerMove();
    }


    // REQUIRES: player hand total > 21
    // EFFECTS: tells player that they lost the hand.
    private void playerBust() {
        System.out.println("You bust! Better luck next time!!");
        playerLose();
    }

    // EFFECTS: now is dealer's turn to draw additional cards
    private void stand() {
        System.out.println("Okay, it's now the dealer's turn to draw!");
        System.out.println("The dealer's hidden card:");
        System.out.println(dealer.getHand().get(0).getName() + " of " + dealer.getHand().get(0).getSuit());

        dealerTurn();
    }

    // MODIFIES: dealer
    // EFFECTS: simulates cards being dealt to dealer
    private void dealerTurn() {
        while (dealer.shouldHit()) {
            Card nextCard = deck.getRandCard();
            dealer.addCard(nextCard);
            System.out.println("The dealer drew a " + nextCard.getName() + " of " + nextCard.getSuit());
        }
        result();
    }

    // MODIFIES: player
    // EFFECTS: compares hand results from dealer and player
    private void result()  {
        if (dealer.isBust()) {
            playerWin();
        } else if (dealer.getHandValue() > player.getHandValue()) {
            playerLose();
        } else if (player.getHandValue() > dealer.getHandValue()) {
            playerWin();
        } else {
            push();
        }
        try {
            writer = new Writer(ROUTE_TO_DATA);
            writer.open();
            writer.write(player);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: simulates blackjack "push"; new hand
    private void push() {
        System.out.println("Tie!");
    }

    // MODIFIES: player
    // EFFECTS: subtracts the player's bet size from their bankroll
    private void playerLose() {
        player.subFromBankroll(player.getCurrBetSize());
    }


    // MODIFIES: player
    // EFFECTS: gives player proper payout and starts another round
    private void playerWin() {
        System.out.println("Nice win!");
        player.addToBankroll(player.getCurrBetSize());
    }

}
