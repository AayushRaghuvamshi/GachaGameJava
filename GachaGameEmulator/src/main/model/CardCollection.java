package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// represents the blueprint for an object representing a collection of Cards collected
public class CardCollection implements Writable {
    List<Card> cards;
    private String collectionName;

    //MODIFIES: this
    //EFFECTS: creates object of CardCollection
    public CardCollection() {
        cards = new ArrayList<>();
    }

    //EFFECTS: given the name of a card, returns the card if it is in the collection, else returns a null card.
    public Card getCard(String cardName) {
        for (Card card : cards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        System.out.println(cardName + " isn't in this collection");
        return null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    //REQUIRES: Card is not null
    //MODIFIES: this
    //EFFECTS: adds input card to card collection
    public void insertCards(Card card) {
        this.cards.add(card);
    }


    //MODIFIES: this
    //EFFECTS: removes input card from card collection
    public boolean removeDuplicatesOfOneCard(Card card) {
        int count = 0;
        for (Card card1 : cards) {
            if (card1.getName().equals(card.getName())) {
                count++;
            }
        }

        if (count > 1) {
            for (int i = 1; i < count; i++) {
                cards.remove(card);
            }
            return true;
        }

        return false;

    }

    //Source: got the idea from https://www.baeldung.com/java-remove-duplicates-from-list
    //MODIFIES: this
    //EFFECTS: Removes all duplicates of all cards in the collection, leaving only unique
    //objects of Card
    public void removeAllDuplicateCardsInCollection() {
        List<Card> cardsNoDupes = new ArrayList<>(
                new HashSet<>(cards));
        cards = cardsNoDupes;
    }

    //EFFECTS: converts card collection object to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", collectionName);
        json.put("cards", cardsToJson());
        return json;
    }

    //EFFECTS: returns JSON array of cards in card collection
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card card : cards) {
            jsonArray.put(card.toJson());
        }

        return jsonArray;
    }

}
