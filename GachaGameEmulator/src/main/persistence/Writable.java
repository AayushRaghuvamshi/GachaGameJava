package persistence;

import org.json.JSONObject;

//All code in this class is modelled based on the code found in the Writable Interface in the code found at the link
// here: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

//interface representing method that must be present in the Card, CardCollection, and ListOfCardCollections classes,
// all of which implement this interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}