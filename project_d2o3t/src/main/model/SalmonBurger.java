package model;

import org.json.JSONObject;

public class SalmonBurger extends Burger {

    //Constructs a Salmon burger, adds a salmon fillet to the ingredient, and set the price of the burger to 8 dollars
    public SalmonBurger(String name, int price) {
        super(name, price);
        ingredients.add("salmon fillet");
    }

    //EFFECTS: returns a string representation of this SalmonBurger
    //note: implemented separately from toString() in BeefBurger
    // because SalmonBurger includes salmon fillet as ingredient
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Salmon burger with ");
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
        json.put("type", "Salmon");
        return json;
    }

}
