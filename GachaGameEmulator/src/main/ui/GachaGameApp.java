package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*
All code to do with JsonWriter and JsonReader is modelled off of the code found at the link
here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/


// The functionality of the app resides within this class, an instance of this class must be created to run the
// game
public class GachaGameApp {

    private static final String JSON_STORE = "./data/listOfCardCollections.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private final Scanner sc = new Scanner(System.in);
    private Summoning summoner;
    private ListOfCardCollections myCardCollections;
    private CardCollection cardCollection1 = new CardCollection();
    private CardCollection cardCollection2 = new CardCollection();
    private CardCollection cardCollection3 = new CardCollection();
    int summonPreferenceUnchanged = 1;
    int keepSummoning = 1;
    int currentCollectionNumber;
    CardCollection currentCardCollection;

    //EFFECTS: starts the functionality of the GachaGameApp
    public GachaGameApp() throws FileNotFoundException {
        myCardCollections = new ListOfCardCollections("Gacha Supreme");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menuForStartOfGame();
    }

    // EFFECTS: presents a menu of options with corresponding relevant commands
    public void menuForStartOfGame() throws FileNotFoundException {
        System.out.println("How would you like to proceed?");
        System.out.println("1: Start a new game");
        System.out.println("2: Load a previous game save");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                startGame();
                break;
            case 2:
                loadPrompt();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: Gives the intro dialogue of the game, and sets up and adds the 3 card colections to the list of card
    // collections, and starts the summoning
    public void startGame() {
        System.out.println("Welcome to the greatest gacha game of all time, where you get to summon for your favorite\n"
                + "characters from popular franchises such as Naruto, Dragon Ball, and Marvel! You also have three \n"
                + "collections at your disposal, name them now. For yes or no questions, always press\n"
                + "0 for no and 1 for yes.  \n"
                + "When you come across questions such as Wanna keep summoning with these preferences? "
                + "If \nnot, wanna change then summon?,if your answer to the first one agrees to the precondition \n"
                + "in the second, then answer both. So for that question, if you do not want to summon with current\n"
                + "preferences, then you would say \n"
                + "0 \n0 \nif you also don't want to change preferences then keep summoning. Else if you want to "
                + "change preferences then keep summoning, then \n0 \n1 \nand keep going. \n Same goes for questions"
                + " like \"Is there a card you would like to remove duplicates of? 1-yes 0-no. If so, which one?\""
                + " if your answer to the first part is yes, then you type \n1 \n[name of card]\nand keep going.");
        System.out.println("The card categories are: Naruto, Marvel, and Dragon Ball. The rarities are \n"
                + "LR: Legendary rare (3) \nUR: Ultra rare (8) \nSSR: Super super rare(13) \nSR: Super Rare(26) "
                + "\nR: Rare(9) in order of decreasing rarity. If you're unlucky, LRs could possibly take a while to "
                + "get ;) Have fun! (You don't HAVE to collect all cards, it's just a game, you can stop summoning"
                + " at any time)\n");
        myCardCollections.addCollection(cardCollection1);
        myCardCollections.addCollection(cardCollection2);
        myCardCollections.addCollection(cardCollection3);
        setCurrentCollection(0);
        summonMain();
    }

    //EFFECTS: Just shows options for a suer to choose from, calls functions accordingly
    public void gameMainMenu() {
        System.out.println("\t1 - Summon for units");
        System.out.println("\t2 - View a collection");
        System.out.println("\t3 - Order a collection");
        System.out.println("\t4 - Find number of duplicates of a card");
        System.out.println("\t5 - Remove duplicates of a card");
        System.out.println("\t6 - Remove all duplicates of all cards in collection and show # of unique cards left");
        System.out.println("\t7 - Save game");
        System.out.println("\t8 - Quit game");
        int choice = sc.nextInt();
        sc.nextLine();
        executeChoice(choice);
    }

    //MODIFIES: methods called by this method may modify this
    //EFFECTS: executes choice input from main menu
    public void executeChoice(int choice) {
        if (choice == 1) {
            setCurrentCollection(1);
            summonMain();
        } else if (choice == 2) {
            viewCollections();
        } else if (choice == 3) {
            orderingCommands();
        } else if (choice == 4) {
            findCard();
        } else if (choice == 5) {
            removeDuplicatesOfACardFromCollection();
        } else if (choice == 6) {
            currentCardCollection.removeAllDuplicateCardsInCollection();
            printCardsToCollectStatus();
            gameMainMenu();
        } else if (choice == 7) {
            savePrompt(false);
        } else if (choice == 8) {
            savePrompt(true);
        } else {
            System.out.println("try again");
            gameMainMenu();
        }
    }

    //EFFECTS: prints message indicating status of completion of collection, congratulatory message if complete,
    //else number of cards left to collect
    public void printCardsToCollectStatus() {
        int size = currentCardCollection.getCards().size();
        int diff = 59 - size;
        if (diff > 0) {
            System.out.println("You have " + diff + " cards left to collect for this collection (collection "
                    + getCurrentCollectionNumber() + "), keep at it!!");
        } else {
            System.out.println("Congratulations!! Your collection is complete! You can now change collections "
                    + "or keep summoning here on this one for no reason :)");
        }
    }

    //EFFECTS: prompts option to load previous save file, and loads it according to user response
    public void loadPrompt() throws FileNotFoundException {

        try {
            myCardCollections = jsonReader.read();
            System.out.println("Loaded " + myCardCollections.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
//              System.out.println("Unable to read from file: " + JSON_STORE);
            throw new FileNotFoundException();
        }

        cardCollection1 = myCardCollections.getCardCollections().get(0);
        cardCollection2 = myCardCollections.getCardCollections().get(1);
        cardCollection3 = myCardCollections.getCardCollections().get(2);

        setCurrentCollection(0);
        gameMainMenu();

    }

    // EFFECTS: just an extra method for setting current collection for summoning
    public void setCurrentCollection(int variant) {
        if (variant == 0) {
            System.out.println("Which collection would you like to continue operating on?");
        } else {
            System.out.println("Which collection would you like to summon on?");
        }

        currentCollectionNumber = sc.nextInt();
        sc.nextLine();
        switch (currentCollectionNumber) {
            case 1:
                currentCardCollection = cardCollection1;
                break;
            case 2:
                currentCardCollection = cardCollection2;
                break;
            case 3:
                currentCardCollection = cardCollection3;
                break;
            default:
                System.out.println("Invalid collection number, try again (1, 2, or 3)");
                setCurrentCollection(0);
                break;
        }
    }

    //EFFECTS: Prompts user for choice to save game on file, saves game if positive response
    public void savePrompt(boolean quit) {
        System.out.println("Would you like to save your current collections on file?");
        int c = sc.nextInt();
        myCardCollections.getCardCollections().clear();
        myCardCollections.getCardCollections().add(cardCollection1);
        myCardCollections.getCardCollections().add(cardCollection2);
        myCardCollections.getCardCollections().add(cardCollection3);
        sc.nextLine();
        if (c == 1) {
            try {
                jsonWriter.open();
                jsonWriter.write(myCardCollections);
                jsonWriter.close();
                System.out.println("Saved " + myCardCollections.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
        if (quit) {
            System.out.println("Hope you had fun!");
            System.exit(0);
        }
        gameMainMenu();
    }

    //MODIFIES: this
    //EFFECTS: summon for characters according to user preference of which categories of characters they wish to summon
    // for. Once done summoning, calls function responsible for functionality related to the actual collections.
    public void summonMain() {
        while (keepSummoning == 1) {
            System.out.println("1 for true or 0 for false for each of Naruto, marvel, dragon ball in that order :)");
            int naruto = sc.nextInt();
            int marvel = sc.nextInt();
            int dragonBall = sc.nextInt();
            summonPreferenceUnchanged = 1;
            while (summonPreferenceUnchanged == 1) {
                summoner = new Summoning(intToBoolean(naruto), intToBoolean(marvel), intToBoolean(dragonBall));
                List<Card> summonResults = summoner.summon();
                for (Card card : summonResults) {
                    if (card.getRarity().equals("LR")) {
                        System.out.println("!!LEGENDARY RARE CARD INCOMING!!");
                    }
                    System.out.println(card.getRarity() + " " + card.getName() + " " + card.getCategory() + "\n");
//                    myCardCollections.getCardCollection(currentCollection).insertCards(card);
                    currentCardCollection.insertCards(card);
                }
                System.out.println("Wanna keep summoning with these preferences? If not, wanna change then summon?");
                summonPreferenceUnchanged = sc.nextInt();
            }
            keepSummoning = sc.nextInt();
        }
        keepSummoning = 1;
        gameMainMenu();
    }

    //EFFECTS: displays collection desired by user to be displayed. Calls function to find a card in current collection
    public void viewCollections() {
        System.out.println("Which collection do you want to view?");
        int choice = sc.nextInt();
        sc.nextLine();

        //used ifs instead of switch to save lines of code used for the method
        if (choice == 1) {
            printCardsInCollection(cardCollection1);
            currentCardCollection = cardCollection1;
            currentCollectionNumber = 1;
        } else if (choice == 2) {
            printCardsInCollection(cardCollection2);
            currentCardCollection = cardCollection2;
            currentCollectionNumber = 2;
        } else if (choice == 3) {
            printCardsInCollection(cardCollection3);
            currentCardCollection = cardCollection3;
            currentCollectionNumber = 3;
        } else {
            System.out.println("Enter a valid collection number (1, 2, or 3)");
            viewCollections();
        }
        gameMainMenu();
    }

    //EFFECTS: prints cards in the collection
    public void printCardsInCollection(CardCollection cardCollection) {
        for (Card card : cardCollection.getCards()) {
            System.out.println(card.getRarity() + " " + card.getName());
        }
    }

    //EFFECTS: Displays number of copies of input card found within current card collection if card related with input
    //card name is found, else indicates its absence. Calls function for removing duplicates of a card in case user
    // decides on removing duplicates of the cards he just found, or any other card.
    public void findCard() {
        System.out.println("Which card are you looking for? (Enter its name)");
        String name = sc.nextLine();
        boolean found = false;
        int count = 0;
        for (Card card : currentCardCollection.getCards()) {
            if (card.getName().equals(name)) {
                count++;
                found = true;
            }
        }
        if (count > 0) {
            System.out.println("Found " + count + " of " + name + "!");
        }
        if (!found) {
            System.out.println("Couldn't find " + name);
        }

        gameMainMenu();
    }


    //EFFECTS: Gives user choice for ordering collection according to their preference, rarity or category, or not order
    public void orderingCommands() {

        System.out.println("Order by rarity or category? Enter 0 for rarity or 1 for category");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 0:
                orderCollectionByRarity();
                break;
            case 1:
                System.out.println("List the categories Naruto, Marvel, Dragon Ball in your desired order.");
                String first = sc.nextLine();
                String second = sc.nextLine();
                String third = sc.nextLine();
                orderCollectionByCategory(first, second, third);
                break;
        }

    }

    //MODIFIES: this
    //EFFECTS: Orders current collection by rarity
    public void orderCollectionByRarity() {
        OrderGivenCollection orderGivenCollection;
        if (getCurrentCollectionNumber() == 1) {
            orderGivenCollection = new OrderGivenCollection(cardCollection1);
            cardCollection1 = orderGivenCollection.getCardCollectionOrderedByRarity();
            currentCardCollection = cardCollection1;
        } else if (getCurrentCollectionNumber() == 2) {
            orderGivenCollection = new OrderGivenCollection(cardCollection2);
            cardCollection2 = orderGivenCollection.getCardCollectionOrderedByRarity();
            currentCardCollection = cardCollection2;
        } else {
            orderGivenCollection = new OrderGivenCollection(cardCollection3);
            cardCollection3 = orderGivenCollection.getCardCollectionOrderedByRarity();
            currentCardCollection = cardCollection3;
        }
        printCardsInCollection(currentCardCollection);
        gameMainMenu();
    }

    //MODIFIES: this
    //EFFECTS: Orders current collection by category, as decided by the user
    public void orderCollectionByCategory(String first, String second, String third) {
        OrderGivenCollection orderGivenCollection;
        if (getCurrentCollectionNumber() == 1) {
            orderGivenCollection = new OrderGivenCollection(cardCollection1);
            cardCollection1 = orderGivenCollection.getCardCollectionOrderedByCategory(first, second, third);
            currentCardCollection = cardCollection1;
        } else if (getCurrentCollectionNumber() == 2) {
            orderGivenCollection = new OrderGivenCollection(cardCollection2);
            cardCollection2 = orderGivenCollection.getCardCollectionOrderedByCategory(first, second, third);
            currentCardCollection = cardCollection2;
        } else {
            orderGivenCollection = new OrderGivenCollection(cardCollection3);
            cardCollection3 = orderGivenCollection.getCardCollectionOrderedByCategory(first, second, third);
            currentCardCollection = cardCollection3;
        }
        printCardsInCollection(currentCardCollection);
        gameMainMenu();

    }

    //Identity number: id number of cardCollection1 is 1, cardCollection2 is 2, cardCollection3 is 3
    //EFFECTS: gets identity number of current card collection.
    public int getCurrentCollectionNumber() {
        return currentCollectionNumber;
    }

    //MODIFIES: this
    //EFFECTS: Removes duplicates of input card from collection. Allows user to then view the collection.
    public void removeDuplicatesOfACardFromCollection() {
        System.out.println("Which card you would like to remove duplicates of? (Enter its name)");

        String removeChoice = sc.nextLine();
        currentCardCollection.removeDuplicatesOfOneCard(currentCardCollection.getCard(removeChoice));

        gameMainMenu();
    }

//    public void checkIfCollectionComplete() {
//        CardCollection completion = new CardCollection();
//        for (Card card : currentCardCollection.getCards()) {
//            completion.insertCards(card);
//        }
//        completion.removeAllDuplicateCardsInCollection();
//        if (completion.getCards().size() == 59) {
//            System.out.println("Congratulations! Your collection is complete :)");
//        } else {
//            System.out.println("You're not done yet! these are the " + (59 - completion.getCards().size()) + " cards"
//                    + " you have left to collect, in order of rarity");
//            CardCollection cardsLeft = new CardCollection();
//            for (int i = 0; i < ListOfAllCards.allCards.size(); i++) {
//                for (int j = 0; j < completion.getCards().size(); j++) {
//                    if (!completion.getCards().get(j).getName().equals(ListOfAllCards.allCards.get(i).getName())) {
//                        cardsLeft.insertCards(ListOfAllCards.allCards.get(i));
//                    }
//                }
//            }
//            OrderGivenCollection orderGivenCollection = new OrderGivenCollection(cardsLeft);
//            cardsLeft = orderGivenCollection.getCardCollectionOrderedByRarity();
//            printCardsInCollection(cardsLeft);
//        }
//        gameMainMenu();
//    }

    //EFFECTS: converts user input of 0 or 1 (effectively 0 or any other integer, but for consistency's sake, 0 or 1) to
    //boolean and returns the boolean
    public boolean intToBoolean(int input) {
        return input != 0;
    }

}
