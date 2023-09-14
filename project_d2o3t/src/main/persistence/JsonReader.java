package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Methods were taken from JsonReader class in:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads order from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses order from JSON object and returns it
    private Order parseOrder(JSONObject jsonObject) {
        Order odr = new Order();
        addFoods(odr, jsonObject);
        return odr;
    }

    // MODIFIES: odr
    // EFFECTS: parses foods list from JSON object and adds them to order
    private void addFoods(Order odr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("order");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(odr, nextFood);
        }
    }

    // MODIFIES: odr
    // EFFECTS: parses single food from JSON object and adds it to order
    private void addFood(Order odr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        int price = jsonObject.getInt("price");
        if (type.equals("Beef")) {
            BeefBurger beefBurger = new BeefBurger(name, price);
            odr.addToOrder(beefBurger);
        } else if (type.equals("Salmon")) {
            SalmonBurger salmonBurger = new SalmonBurger(name, price);
            odr.addToOrder(salmonBurger);
        } else if (type.equals("Fries")) {
            String size = jsonObject.getString("size");
            Fries fries = new Fries(name, price, size);
            odr.addToOrder(fries);
        }

    }
}