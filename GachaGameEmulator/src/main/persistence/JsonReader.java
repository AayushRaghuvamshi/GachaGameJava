package persistence;


import model.Card;
import model.CardCollection;
import model.ListOfAllCards;
import model.ListOfCardCollections;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//All code in this class is modelled based on the code found in the JsonReader class in the code found at the link
// here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads ListOfCardCollections from JSON data stored in file
public class JsonReader {
    private String source;
    ListOfAllCards listOfAllCards = new ListOfAllCards();

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfCardCollections from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfCardCollections read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfCardCollections(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listOfCardCollections from JSON object and returns it
    private ListOfCardCollections parseListOfCardCollections(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfCardCollections listOfCardCollections = new ListOfCardCollections(name);
        addCardCollections(listOfCardCollections, jsonObject);
        return listOfCardCollections;
    }

    // MODIFIES: listOfCardCollections
    // EFFECTS: parses Card Collections from JSON object and adds them to listOfCardCollections
    private void addCardCollections(ListOfCardCollections listOfCardCollections, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Card Collections");
        for (Object json : jsonArray) {
            CardCollection cardCollection = new CardCollection();
            JSONObject nextCardCollection = (JSONObject) json;
            addCardCollection(listOfCardCollections, cardCollection, nextCardCollection);
            listOfCardCollections.addCollection(cardCollection);
        }
    }

    // MODIFIES: cardCollection from above method
    // EFFECTS: parses cards from JSON object and adds them to cardCollection
    private void addCardCollection(ListOfCardCollections loccs, CardCollection c, JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCards(loccs, c, nextCard);
        }

    }

    // MODIFIES: cardCollection from above method
    // EFFECTS: adds cards from JSON object to cardCollection
    private void addCards(ListOfCardCollections listOfCardCollections, CardCollection c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Card card = listOfAllCards.getCard(name);
        c.insertCards(card);
    }
}
