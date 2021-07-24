package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Test the ListOfAllCards class
public class TestListOfAllCards {
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
    public void testListOfAllCardsSize() {
        //since lists in this method are static, only one test is necessary
        assertEquals(59, ListOfAllCards.allCards.size());
    }

    @Test
    public void testGetNullCard() {
        List<Card> testCards = new ArrayList<>();
        testCards.add(listOfAllCards.getCard("hgh"));
        assertFalse(ListOfAllCards.allCards.contains(testCards.get(0)));
    }

}
