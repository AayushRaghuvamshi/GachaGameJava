package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// This class is the core of the game, responsible for all the summoning functionality of the app, and adds summoned
// cards to a card collection preselected by the user
public class Summoning {
    private boolean naruto;
    private boolean marvel;
    private boolean dragonBall;
    private List<Card> summonOnCards;
    Random randomCard = new Random();
    ListOfAllCards listOfAllCards = new ListOfAllCards();

    //Creates an instance of Summoning
    //MODIFIES: this
    //EFFECTS: sets up Summoning instance for summoning only on the categories specified by the user. Initializes list
    //for cards user can summon on, and calls method to insert cards into it accordingly.
    public Summoning(boolean naruto, boolean marvel, boolean dragonBall) {
        this.naruto = naruto;
        this.marvel = marvel;
        this.dragonBall = dragonBall;
        summonOnCards = new ArrayList<>();
        insertCards();
    }

    //REQUIRES: At least one of naruto, marvel, or dragonBall must be true
    //MODIFIES: this
    //EFFECTS: modifies list of cards to summon on based on user preference indicated by boolean choices
    public void insertCards() {
        if (this.naruto) {
            for (Card card : ListOfAllCards.allCards) {
                if (card.getCategory().equals("Naruto")) {
                    summonOnCards.add(card);
                }
            }
        }
        if (this.dragonBall) {
            for (Card card : ListOfAllCards.allCards) {
                if (card.getCategory().equals("Dragon Ball")) {
                    summonOnCards.add(card);
                }
            }
        }
        if (this.marvel) {
            for (Card card : ListOfAllCards.allCards) {
                if (card.getCategory().equals("Marvel")) {
                    summonOnCards.add(card);
                }
            }
        }
    }

    // this calls on 5 other methods used for summoning a card from each respective
    // rarity, this splitting was done to handle the lines per method restriction.
    //EFFECTS: returns a list of five cards randomly inserted from the list of all cards in the game into the list of
    // cards you obtain from summoning.
    public List<Card> summon() {


        List<Card> summonResult = new ArrayList<>();
        while (summonResult.size() < 5) {
            Random r = new Random();
            int result = r.nextInt(1000);
            if (result > 990) {
                summonResult.add(summonLr());
            } else if (result > 900) {
                summonResult.add(summonUr());
            } else if (result > 700) {
                summonResult.add(summonSsr());
            } else if (result > 400) {
                summonResult.add(summonSr());
            } else {
                summonResult.add(summonR());
            }
        }
        return summonResult;
    }

    //One of the five methods used for summoning for cards, this was done to handle the lines per method restriction.
    //EFFECTS: returns a random LR card from the list of all cards
    public Card summonLr() {
        List<Card> lrs = new ArrayList<>();
        for (Card card : summonOnCards) {
            if (card.getRarity().equals("LR")) {
                lrs.add(card);
            }
        }
        return lrs.get(randomCard.nextInt(lrs.size()));
    }

    //One of the five methods used for summoning for cards, this was done to handle the lines per method restriction.
    //EFFECTS: returns a random UR card from the list of all cards
    public Card summonUr() {
        List<Card> urs = new ArrayList<>();
        for (Card card : summonOnCards) {
            if (card.getRarity().equals("UR")) {
                urs.add(card);
            }
        }
        return urs.get(randomCard.nextInt(urs.size()));
    }

    //One of the five methods used for summoning for cards, this was done to handle the lines per method restriction.
    //EFFECTS: returns a random SSR card from the list of all cards
    public Card summonSsr() {
        List<Card> ssrs = new ArrayList<>();
        for (Card card : summonOnCards) {
            if (card.getRarity().equals("SSR")) {
                ssrs.add(card);
            }
        }
        return ssrs.get(randomCard.nextInt(ssrs.size()));
    }

    //One of the five methods used for summoning for cards, this was done to handle the lines per method restriction.
    //EFFECTS: returns a random SR card from the list of all cards
    public Card summonSr() {
        List<Card> srs = new ArrayList<>();
        for (Card card : summonOnCards) {
            if (card.getRarity().equals("SR")) {
                srs.add(card);
            }
        }
        return srs.get(randomCard.nextInt(srs.size()));
    }

    //One of the five methods used for summoning for cards, this was done to handle the lines per method restriction.
    //EFFECTS: returns a random R card from the list of all cards
    public Card summonR() {
        List<Card> rs = new ArrayList<>();
        for (Card card : summonOnCards) {
            if (card.getRarity().equals("R")) {
                rs.add(card);
            }
        }
        return rs.get(randomCard.nextInt(rs.size()));
    }

    public List<Card> getSummonOnCards() {
        return summonOnCards;
    }
}
