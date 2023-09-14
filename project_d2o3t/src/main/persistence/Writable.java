package persistence;

import org.json.JSONObject;

//Methods were taken from Writable class in:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
