package persistence;

import model.CardCollection;
import model.ListOfAllCards;
import model.ListOfCardCollections;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//All code in this class is modelled based on the code found in the JsonReaderTest class in the code found at the link
// here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Tests the JsonReader class
public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfCardCollections listOfCardCollections = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfCardCollections() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyListOfCardCollections.json");
        try {
            ListOfCardCollections listOfCardCollections = reader.read();
            assertEquals("Aayush's collections", listOfCardCollections.getName());
            assertEquals(0, listOfCardCollections.numCollections());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListOfCardCollections() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralListOfCardCollections.json");
        try {
            ListOfCardCollections wr = reader.read();
            assertEquals("Ya boi's collections", wr.getName());
            List<CardCollection> cardCollections = wr.getCardCollections();
            assertEquals(3, cardCollections.size());
            checkCard(ListOfAllCards.SPIDER_MAN.getName(),cardCollections.get(0).getCards().get(0));
            checkCard(ListOfAllCards.ADULT_SASUKE.getName(),cardCollections.get(1).getCards().get(2));
            checkCard(ListOfAllCards.BLACK_WIDOW.getName(),cardCollections.get(2).getCards().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
