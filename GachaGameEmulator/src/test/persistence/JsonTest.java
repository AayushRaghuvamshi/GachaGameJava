package persistence;

import model.Card;

import static org.junit.jupiter.api.Assertions.assertTrue;

//All code in this class is modelled based on the code found in the JsonTest class in the code found at the link
// here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//Contains a useful method for checking cards, used in the two test classes JsonWriterTest and JsonReaderTest
public class JsonTest {

    //REQUIRES: card is not null
    //EFFECTS: checks card has same name as input name
    protected void checkCard(String name, Card card) {
        assertTrue(card.getName().equals(name));
    }
}
