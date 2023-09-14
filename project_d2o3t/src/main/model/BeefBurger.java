package model;

import org.json.JSONObject;
import persistence.Writable;

public class BeefBurger extends Burger {

    //Constructs a beef burger, adds a beef patty to the ingredient, and set the price of the burger to 7 dollars
    public BeefBurger(String name, int price) {
        super(name, price);
        ingredients.add("beef patty");
    }

    //EFFECTS: returns a string representation of this BeefBurger
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Beef burger with ");
        for (int i = 0; i < ingredients.size(); i++) {
            s.append(ingredients.get(i));
            if (i != ingredients.size() - 1) {
                s.append(", ");
            } else {
                s.append(".");
            }
        }
        return s.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("type", "Beef");
        return json;
    }

}
