package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Tests the OrderGivenCollection class
public class TestOrderGivenCollection {
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
    public void testOrderGivenCollectionByCategoryMarvelNarutoDragonBall() {


        //tests that categories are in order described by user input
        cardCollection.insertCards(ListOfAllCards.ADULT_SASUKE);
        cardCollection.insertCards(ListOfAllCards.ANDROID_17);
        cardCollection.insertCards(ListOfAllCards.BULMA);
        cardCollection.insertCards(ListOfAllCards.HERCULE);
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        OrderGivenCollection orderGivenCollection;
        orderGivenCollection = new OrderGivenCollection(cardCollection);

        CardCollection testOrderedCardsByCategory = orderGivenCollection.getCardCollectionOrderedByCategory("Marvel",
                "Naruto",
                "Dragon Ball");
        assertTrue((testOrderedCardsByCategory.getCards().get(0).getCategory().equals("Marvel") &&
                testOrderedCardsByCategory.getCards().get(1).getCategory().equals("Naruto") &&
                testOrderedCardsByCategory.getCards().get(2).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(3).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(4).getCategory().equals("Dragon Ball")));
    }

    @Test
    public void testOrderGivenCollectionByCategoryNarutoMarvelDragonBall() {
        //tests that categories are in order described by user input
        cardCollection.insertCards(ListOfAllCards.ADULT_SASUKE);
        cardCollection.insertCards(ListOfAllCards.ANDROID_17);
        cardCollection.insertCards(ListOfAllCards.BULMA);
        cardCollection.insertCards(ListOfAllCards.HERCULE);
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        OrderGivenCollection orderGivenCollection;
        orderGivenCollection = new OrderGivenCollection(cardCollection);

        CardCollection testOrderedCardsByCategory = orderGivenCollection.getCardCollectionOrderedByCategory("Naruto",
                "Marvel",
                "Dragon Ball");
        assertTrue((testOrderedCardsByCategory.getCards().get(0).getCategory().equals("Naruto") &&
                testOrderedCardsByCategory.getCards().get(1).getCategory().equals("Marvel") &&
                testOrderedCardsByCategory.getCards().get(2).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(3).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(4).getCategory().equals("Dragon Ball")));
    }

    @Test
    public void testOrderGivenCollectionByCategoryNarutoDragonBallMarvel() {
        //tests that categories are in order described by user input
        cardCollection.insertCards(ListOfAllCards.ADULT_SASUKE);
        cardCollection.insertCards(ListOfAllCards.ANDROID_17);
        cardCollection.insertCards(ListOfAllCards.BULMA);
        cardCollection.insertCards(ListOfAllCards.HERCULE);
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        OrderGivenCollection orderGivenCollection;
        orderGivenCollection = new OrderGivenCollection(cardCollection);

        CardCollection testOrderedCardsByCategory = orderGivenCollection.getCardCollectionOrderedByCategory("Naruto",
                "Dragon Ball",
                "Marvel");
        assertTrue((testOrderedCardsByCategory.getCards().get(0).getCategory().equals("Naruto") &&
                testOrderedCardsByCategory.getCards().get(1).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(2).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(3).getCategory().equals("Dragon Ball") &&
                testOrderedCardsByCategory.getCards().get(4).getCategory().equals("Marvel")));
    }
    //I think the tests so far suffice, any more would be excessive and redundant given there are eight different combinations for
    //the 3 categories and there's no extra scenario not being covered by these tests.

    @Test
    public void testOrderGivenCollectionByRarity() {
        cardCollection.insertCards(ListOfAllCards.ANDROID_17);
        cardCollection.insertCards(ListOfAllCards.BULMA);
        cardCollection.insertCards(ListOfAllCards.HERCULE);
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        cardCollection.insertCards(ListOfAllCards.HOKAGE_NARUTO);
        cardCollection.insertCards(ListOfAllCards.HULK);
        OrderGivenCollection orderGivenCollection;
        orderGivenCollection = new OrderGivenCollection(cardCollection);
        CardCollection testOrderedByRarity = orderGivenCollection.getCardCollectionOrderedByRarity();
        assertTrue(testOrderedByRarity.getCards().get(0).getRarity().equals("LR") &&
                testOrderedByRarity.getCards().get(1).getRarity().equals("UR") &&
                testOrderedByRarity.getCards().get(2).getRarity().equals("SSR") &&
                testOrderedByRarity.getCards().get(3).getRarity().equals("SR") &&
                testOrderedByRarity.getCards().get(4).getRarity().equals("R"));


    }


}
