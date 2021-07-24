package persistence;

import model.CardCollection;
import model.ListOfAllCards;
import model.ListOfCardCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//All code in this class is modelled based on the code found in the JsonWriterTest class in the code found at the link
// here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//tests the JsonWriter class
public class JsonWriterTest extends JsonTest {
    ListOfCardCollections listOfCardCollections;

    @BeforeEach
    public void setUp() {
        listOfCardCollections = new ListOfCardCollections("Ya boi's collections");
        CardCollection cardCollection1 = new CardCollection();
        CardCollection cardCollection2 = new CardCollection();
        CardCollection cardCollection3 = new CardCollection();
        cardCollection1.insertCards(ListOfAllCards.SPIDER_MAN);
        cardCollection1.insertCards(ListOfAllCards.HOKAGE_NARUTO);
        cardCollection2.insertCards(ListOfAllCards.ANDROID_17);
        cardCollection2.insertCards(ListOfAllCards.BULMA);
        cardCollection2.insertCards(ListOfAllCards.ADULT_SASUKE);
        cardCollection3.insertCards(ListOfAllCards.BLACK_WIDOW);
        listOfCardCollections.addCollection(cardCollection1);
        listOfCardCollections.addCollection(cardCollection2);
        listOfCardCollections.addCollection(cardCollection3);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfCardCollections l = new ListOfCardCollections("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfCardCollections() {
        try {
            ListOfCardCollections l = new ListOfCardCollections("Aayush's collections");
            JsonWriter writer = new JsonWriter("./GachaGameEmulator/data/testWriterEmptyListOfCardCollections1.json");
            writer.open();
            writer.write(l);
            writer.close();

            JsonReader reader = new JsonReader("./GachaGameEmulator/data/testWriterEmptyListOfCardCollections1.json");
            l = reader.read();
            assertEquals("Aayush's collections", l.getName());
            assertEquals(0, l.numCollections());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./GachaGameEmulator/data/testWriterGeneralListOfCardCollections1.json");
            writer.open();
            writer.write(listOfCardCollections);
            writer.close();

            JsonReader reader = new JsonReader("./GachaGameEmulator/data/testWriterGeneralListOfCardCollections1.json");
            listOfCardCollections = reader.read();
            assertEquals("Ya boi's collections", listOfCardCollections.getName());
            List<CardCollection> thingies = listOfCardCollections.getCardCollections();
            assertEquals(3, thingies.size());
            checkCard(ListOfAllCards.SPIDER_MAN.getName(),thingies.get(0).getCards().get(0));
            checkCard(ListOfAllCards.ADULT_SASUKE.getName(),thingies.get(1).getCards().get(2));
            checkCard(ListOfAllCards.BLACK_WIDOW.getName(),thingies.get(2).getCards().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
