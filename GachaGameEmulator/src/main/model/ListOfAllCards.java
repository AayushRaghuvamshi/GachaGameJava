package model;

import java.util.*;

// This class only stores all cards available in the game, with a few helper functions for storing them into a single
// list
public class ListOfAllCards {

    //LRs begin
    public static final Card MASTERED_ULTRA_INSTINCT_GOKU = new Card("Mastered Ultra Instinct Goku", "LR",
            "Dragon Ball");

    public static final Card HOKAGE_NARUTO = new Card("Hokage Naruto", "LR",
            "Naruto");

    public static final Card BABY_GROOT = new Card("Baby Groot", "LR",
            "Marvel");

    //LRs end


    //URs begin
    public static final Card ULTRA_INSTINCT_GOKU = new Card("Ultra Instinct Goku", "UR",
            "Dragon Ball");
    public static final Card SSB_EVOLUTION_VEGETA = new Card("SSB Evolution Vegeta",
            "UR", "Dragon Ball");

    public static final Card MIGHT_GUY = new Card("Might Guy", "UR",
            "Naruto");
    public static final Card ADULT_SASUKE = new Card("Adult Sasuke", "UR",
            "Naruto");
    public static final Card HASHIRAMA = new Card("Hashirama", "UR",
            "Naruto");
    public static final Card MADARA = new Card("Madara", "UR",
            "Naruto");

    public static final Card SPIDER_MAN = new Card("Spider-man", "UR",
            "Marvel");
    public static final Card THANOS = new Card("Thanos", "UR",
            "Marvel");
    //URs end

    //SSRs begin
    public static final Card JIREN = new Card("Jiren", "SSR",
            "Dragon Ball");
    public static final Card BROLY = new Card("Broly", "SSR",
            "Dragon Ball");
    public static final Card KEFLA = new Card("Kefla", "SSR",
            "Dragon Ball");
    public static final Card KAKASHI = new Card("Kakashi", "SSR",
            "Naruto");
    public static final Card OBITO = new Card("Obito", "SSR",
            "Naruto");
    public static final Card KONOHAMARU = new Card("Konohamaru", "SSR",
            "Naruto");

    public static final Card HAWKEYE = new Card("Hawkeye", "SSR",
            "Marvel");
    public static final Card BLACK_WIDOW = new Card("Black Widow", "SSR",
            "Marvel");
    public static final Card CAPTAIN_AMERICA = new Card("Captain America", "SSR",
            "Marvel");
    public static final Card HULK = new Card("Hulk", "SSR",
            "Marvel");
    public static final Card IRON_MAN = new Card("Iron-Man", "SSR",
            "Marvel");
    public static final Card SENTRY = new Card("Sentry", "SSR",
            "Marvel");
    public static final Card WOLVERINE = new Card("Wolverine", "SSR",
            "Marvel");

    //SSRs end

    //SRs begin
    public static final Card KRILLIN = new Card("Krillin", "SR",
            "Dragon Ball");
    public static final Card PICCOLO = new Card("Piccolo", "SR",
            "Dragon Ball");
    public static final Card ROSHI = new Card("Master Roshi", "SR",
            "Dragon Ball");
    public static final Card ANDROID_17 = new Card("Android 17", "SR",
            "Dragon Ball");
    public static final Card ANDROID_18 = new Card("Android 18", "SR",
            "Dragon Ball");
    public static final Card BARDOCK = new Card("Bardock", "SR",
            "Dragon Ball");
    public static final Card NAIL = new Card("Nail", "SR",
            "Dragon Ball");
    public static final Card TRUNKS = new Card("Trunks", "SR",
            "Dragon Ball");
    public static final Card GOTEN = new Card("Goten", "SR",
            "Dragon Ball");
    public static final Card JANEMBA = new Card("Janemba", "SR",
            "Dragon Ball");
    public static final Card COOLER = new Card("Cooler", "SR",
            "Dragon Ball");
    public static final Card SAKURA = new Card("Sakura", "SR",
            "Naruto");
    public static final Card DARUI = new Card("Darui", "SR",
            "Naruto");
    public static final Card OMOI = new Card("Omoi", "SR",
            "Naruto");
    public static final Card SHINO = new Card("Shino", "SR",
            "Naruto");
    public static final Card CHOJI = new Card("Choji", "SR",
            "Naruto");
    public static final Card ASUMA = new Card("Asuma", "SR",
            "Naruto");
    public static final Card KABUTO = new Card("Kabuto", "SR",
            "Naruto");
    public static final Card IRUKA = new Card("Iruka", "SR",
            "Naruto");
    public static final Card KUSHINA = new Card("Kushina", "SR",
            "Naruto");
    public static final Card MS_MARVEL = new Card("Ms Marvel", "SR",
            "Marvel");
    public static final Card CYCLOPS = new Card("Cyclops", "SR",
            "Marvel");
    public static final Card NIGHTCRAWLER = new Card("Nightcrawler", "SR",
            "Marvel");
    public static final Card FALCON = new Card("Falcon", "SR",
            "Marvel");
    public static final Card MANTIS = new Card("Mantis", "SR",
            "Marvel");
    public static final Card DRAX = new Card("Drax", "SR",
            "Marvel");


    //SRs end

    //Rs begin
    public static final Card YAMCHA = new Card("Yamcha", "R",
            "Dragon Ball");
    public static final Card HERCULE = new Card("Hercule", "R",
            "Dragon Ball");
    public static final Card BULMA = new Card("Bulma", "R",
            "Dragon Ball");
    public static final Card NEBULA = new Card("Nebula", "R",
            "Marvel");
    public static final Card STAR_LORD = new Card("Star Lord", "R",
            "Marvel");
    public static final Card ROCKET = new Card("Rocket", "R",
            "Marvel");
    public static final Card RAMEN_GUY = new Card("Ramen Guy", "R",
            "Naruto");
    public static final Card EBISU = new Card("Ebisu", "R",
            "Naruto");
    public static final Card UDON = new Card("Udon", "R",
            "Naruto");

    //Rs end


    public static List<Card> allCards = new ArrayList<>();

    //since there are 59 cards, inserting them was taking too much space for one method, hence I split it into 3.
    //This is one of them, all 3 are called in the constructor to set the list of all cards available in the game.
    //MODIFIES: this
    //EFFECTS: inserts cards into list of all cards available in the game, to be used in any other method that needs it.
    public void putCards1() {
        insertCard(MASTERED_ULTRA_INSTINCT_GOKU);
        insertCard(THANOS);
        insertCard(ULTRA_INSTINCT_GOKU);
        insertCard(SSB_EVOLUTION_VEGETA);
        insertCard(MIGHT_GUY);
        insertCard(HOKAGE_NARUTO);
        insertCard(ADULT_SASUKE);
        insertCard(HASHIRAMA);
        insertCard(MADARA);
        insertCard(SPIDER_MAN);
        insertCard(JIREN);
        insertCard(BROLY);
        insertCard(KEFLA);
        insertCard(KAKASHI);
        insertCard(RAMEN_GUY);
        insertCard(EBISU);
        insertCard(UDON);
    }

    //since there are 59 cards, inserting them was taking too much space for one method, hence I split it into 3.
    //This is one of them, all 3 are called in the constructor to set the list of all cards available in the game.
    //MODIFIES: this
    //EFFECTS: inserts cards into list of all cards available in the game, to be used in any other method that needs it.
    public void putCards2() {
        insertCard(OBITO);
        insertCard(HAWKEYE);
        insertCard(BLACK_WIDOW);
        insertCard(CAPTAIN_AMERICA);
        insertCard(HULK);
        insertCard(IRON_MAN);
        insertCard(SENTRY);
        insertCard(KRILLIN);
        insertCard(PICCOLO);
        insertCard(ROSHI);
        insertCard(ANDROID_17);
        insertCard(ANDROID_18);
        insertCard(SAKURA);
        insertCard(YAMCHA);
        insertCard(WOLVERINE);
        insertCard(KONOHAMARU);
        insertCard(DARUI);
        insertCard(OMOI);
        insertCard(SHINO);
        insertCard(CHOJI);
        insertCard(BABY_GROOT);
        insertCard(BARDOCK);
        insertCard(NAIL);
    }

    //since there are 59 cards, inserting them was taking too much space for one method, hence I split it into 3.
    //This is one of them, all 3 are called in the constructor to set the list of all cards available in the game.
    //MODIFIES: this
    //EFFECTS: inserts cards into list of all cards available in the game, to be used in any other method that needs it.
    public void putCards3() {
        insertCard(JANEMBA);
        insertCard(TRUNKS);
        insertCard(GOTEN);
        insertCard(COOLER);
        insertCard(ASUMA);
        insertCard(KABUTO);
        insertCard(IRUKA);
        insertCard(KUSHINA);
        insertCard(MS_MARVEL);
        insertCard(CYCLOPS);
        insertCard(NIGHTCRAWLER);
        insertCard(FALCON);
        insertCard(MANTIS);
        insertCard(DRAX);
        insertCard(HERCULE);
        insertCard(BULMA);
        insertCard(NEBULA);
        insertCard(STAR_LORD);
        insertCard(ROCKET);
    }

    //In order to make adding cards faster, this method was created to be used in th putCards methods.
    //REQUIRES: a non-null card is passed as a parameter
    //MODIFIES: this
    //EFFECTS: adds input card to the list of all cards available in the game
    public void insertCard(Card unit) {
        allCards.add(unit);
    }

    //MODIFIES: this
    //EFFECTS: Creates object of ListOfAllCards, adds all available cards to the list of all cards,
    //via the putCards and insertCard methods.
    public ListOfAllCards() {
        putCards1();
        putCards2();
        putCards3();
    }

    //EFFECTS: Returns card having the input name
    public Card getCard(String name) {
        for (Card card : allCards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }


}


