package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

// Tests the CardCollection class
class TestCardAndCardCollection {
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
    public void testCardCollectionGetCard() {
        //tests for when collection is empty, so return null
        assertEquals(null, cardCollection.getCard("Hashirama"));

        //tests for when there's one card in collection, so it should return the card if it's name is given as input,
        //null for any other input, since no other card is currently present
        cardCollection.insertCards(ListOfAllCards.HASHIRAMA);
        assertEquals(ListOfAllCards.HASHIRAMA, cardCollection.getCard("Hashirama"));

        //tests for one card in collection of multiple cards
        cardCollection.insertCards(ListOfAllCards.ADULT_SASUKE);
        cardCollection.insertCards(ListOfAllCards.ANDROID_17);
        assertEquals(ListOfAllCards.ANDROID_17, cardCollection.getCard("Android 17"));
        assertEquals(ListOfAllCards.ADULT_SASUKE, cardCollection.getCard("Adult Sasuke"));
    }

    @Test
    public void testGetCards() {
        //tests for when card collection is empty
        assertEquals(Collections.emptyList(), cardCollection.getCards());

        //tests for non-empty card collection
        cardCollection.insertCards(ListOfAllCards.HASHIRAMA);
        cardCollection.insertCards(ListOfAllCards.ADULT_SASUKE);
        cardCollection.insertCards(ListOfAllCards.ANDROID_17);
        assertEquals(3, cardCollection.getCards().size());
    }


    @Test
    public void testGetCollectionName() {
        assertEquals(null, cardCollection.getCollectionName());
        cardCollection.setCollectionName("testName");
        assertEquals("testName", cardCollection.getCollectionName());
    }

    @Test
    public void testRemoveDuplicatesOfOneCard() {
        cardCollection.insertCards(ListOfAllCards.HULK);
        assertFalse(cardCollection.removeDuplicatesOfOneCard(ListOfAllCards.HULK));
        cardCollection.insertCards(ListOfAllCards.HULK);
        cardCollection.insertCards(ListOfAllCards.HULK);
        assertTrue(cardCollection.removeDuplicatesOfOneCard(ListOfAllCards.HULK));
        assertEquals(1,cardCollection.getCards().size());
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        assertFalse(cardCollection.removeDuplicatesOfOneCard(ListOfAllCards.HULK));
        assertEquals(2,cardCollection.getCards().size());
    }

    @Test
    public void testRemoveAllDuplicatesOfAllCardsInCollection() {
        cardCollection.removeAllDuplicateCardsInCollection();
        assertEquals(0,cardCollection.getCards().size());
        cardCollection.insertCards(ListOfAllCards.HULK);
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        cardCollection.removeAllDuplicateCardsInCollection();
        assertEquals(2,cardCollection.getCards().size());
        cardCollection.insertCards(ListOfAllCards.HULK);
        cardCollection.insertCards(ListOfAllCards.SPIDER_MAN);
        cardCollection.removeAllDuplicateCardsInCollection();
        assertEquals(2,cardCollection.getCards().size());
    }

}