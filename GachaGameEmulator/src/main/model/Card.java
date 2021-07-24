package model;

import org.json.JSONObject;
import persistence.Writable;

// represents the blueprint for a Card Object
public class Card implements Writable {

    private String name;
    private String rarity;
    private String category;

    //MODIFIES: this
    //EFFECTS: Constructs object of Card while initialising all fields
    public Card(String name, String rarity, String category) {
        this.name = name;
        this.rarity = rarity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getCategory() {
        return category;
    }

    //EFFECTS: converts card object to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rarity", rarity);
        json.put("category", category);
        return json;
    }
}
