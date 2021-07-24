package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests the Summoning class
public class TestSummoning {
    CardCollection cardCollection;
    ListOfCardCollections listOfCardCollections;
    ListOfAllCards listOfAllCards;

    @BeforeEach
    public void setup() {
        cardCollection = new CardCollection();
        listOfCardCollections = new ListOfCardCollections("Aayush");
        listOfAllCards = new ListOfAllCards();
    }

    @Test
    public void testInsertCardsNoNaruto() {
        Summoning summoner = new Summoning(false, true, true);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertFalse(card.getCategory().equals("Naruto"));
            assertTrue((card.getCategory().equals("Dragon Ball") || card.getCategory().equals("Marvel")));

        }
    }

    @Test
    public void testInsertCardsNoMarvel() {
        Summoning summoner = new Summoning(true, false, true);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertFalse(card.getCategory().equals("Marvel"));
            assertTrue((card.getCategory().equals("Naruto") || card.getCategory().equals("Dragon Ball")));

        }
    }

    @Test
    public void testInsertCardsNoDragonBall() {
        Summoning summoner = new Summoning(true, true, false);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertFalse(card.getCategory().equals("Dragon Ball"));
            assertTrue((card.getCategory().equals("Naruto") || card.getCategory().equals("Marvel")));

        }
    }

    @Test
    public void testInsertCardsNoNarutoMarvel() {
        Summoning summoner = new Summoning(false, false, true);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertFalse((card.getCategory().equals("Naruto") || card.getCategory().equals("Marvel")));
            assertTrue(card.getCategory().equals("Dragon Ball"));
        }
    }

    @Test
    public void testInsertCardsNoNarutoDragonBall() {
        Summoning summoner = new Summoning(false, true, false);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertFalse((card.getCategory().equals("Naruto") || card.getCategory().equals("Dragon Ball")));
            assertTrue(card.getCategory().equals("Marvel"));
        }
    }

    @Test
    public void testInsertCardsNoMarvelDragonBall() {
        Summoning summoner = new Summoning(true, false, false);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertFalse((card.getCategory().equals("Dragon Ball") || card.getCategory().equals("Marvel")));
            assertTrue(card.getCategory().equals("Naruto"));
        }
    }

    @Test
    public void testInsertCardsContainsAllCategories() {
        Summoning summoner = new Summoning(true, true, true);
        summoner.insertCards();
        for (Card card : summoner.getSummonOnCards()) {
            assertTrue(card.getCategory().equals("Naruto") || card.getCategory().equals("Dragon Ball") ||
                    card.getCategory().equals("Marvel"));
        }
    }

    @Test
    public void testSummon() {
        //tests that we're getting 5 cards per summon
        Summoning summoner = new Summoning(false, true, true);
        summoner.insertCards();
        //the loop ensures a higher probability of using all rarity summon methods in the Summoning class, completing
        //our code coverage
        for (int i = 0; i<200; i++) {
            assertEquals(5, summoner.summon().size());
        }
    }
}
