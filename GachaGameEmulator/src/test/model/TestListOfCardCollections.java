package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the ListOfCardCollections class
public class TestListOfCardCollections {
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
    public void testGetCardCollection() {
        assertEquals(null, listOfCardCollections.getCardCollection("collectionOne"));

        //tests for one collection where name valid and invalid
        CardCollection cardCollection1 = new CardCollection();
        cardCollection1.setCollectionName("Primero");
        listOfCardCollections.addCollection(cardCollection1);
        assertEquals(cardCollection1, listOfCardCollections.getCardCollection("Primero"));
        assertEquals(null, listOfCardCollections.getCardCollection("Another one"));

        //tests for multiple collections for some names valid and some invalid
        CardCollection cardCollection2 = new CardCollection();
        cardCollection2.setCollectionName("Segundo");
        listOfCardCollections.addCollection(cardCollection2);
        assertEquals(cardCollection2, listOfCardCollections.getCardCollection("Segundo"));
        assertEquals(null, listOfCardCollections.getCardCollection("Third"));

        //tests that there's actually two CardCollections in the listOfCollections
        assertEquals(2,listOfCardCollections.getCardCollections().size());
    }
}
