package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents the blueprint for a list of card collections
public class ListOfCardCollections implements Writable {
    List<CardCollection> cardCollections;
    private String name;

    //EFFECTS: constructs an object of this class
    public ListOfCardCollections(String name) {
        this.name = name;
        cardCollections = new ArrayList<>();
    }

    //EFFECTS: given a card collection name, returns the card collection if it's in the list of card collections, else
    //returns a null object and indicates its absence in the list
    public CardCollection getCardCollection(String collectionName) {
        for (CardCollection cardCollection : cardCollections) {
            if (cardCollection.getCollectionName().equals(collectionName)) {
                return cardCollection;
            }
        }
        System.out.println("A collection of the name " + collectionName + " was not found");
        return null;
    }

    //REQUIRES: cardCollection is not null
    //MODIFIES: this
    //EFFECTS: adds input cardCollection to list of Card Collections
    public void addCollection(CardCollection cardCollection) {
        cardCollections.add(cardCollection);
    }

    public List<CardCollection> getCardCollections() {
        return cardCollections;
    }

    public String getName() {
        return name;
    }

    //EFFECTS: returns list of card collections size
    public int numCollections() {
        return cardCollections.size();
    }

    //EFFECTS: converts list of card collections object to JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Card Collections", cardCollectionsToJson());
        return json;
    }

    //EFFECTS: converts returns each card collection in the list of card collections as a JSON array
    private JSONArray cardCollectionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CardCollection cardCollection : cardCollections) {
            jsonArray.put(cardCollection.toJson());
        }

        return jsonArray;
    }

}
