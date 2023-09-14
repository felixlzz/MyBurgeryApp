package model;

import org.json.JSONObject;

public class Fries extends Food {
    protected String size;                      //size of the fries

    //EFFECTS: Constructs a new instance of Fries with no price and size
    public Fries(String name, int price) {
        this.name = name;
        this.price = price;
        this.size = "Regular";
    }

    public Fries(String name, int price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    //MODIFIES: this
    //EFFECTS: set the price and size of the fries to Regular standards
    public void setSizeR() {
        this.price = 3;
        this.size = "Regular";
    }

    //MODIFIES: this
    //EFFECTS: set the price and size of the fries to Large standards
    public void setSizeL() {
        this.price = 5;
        this.size = "Large";
    }

    //EFFECTS: return the size of the fries
    public String getSize() {
        return this.size;
    }

    //EFFECTS: returns a string representation of the fries with its size
    @Override
    public String toString() {
        if (size.equals("Regular") || size.equals("Large")) {
            return size + " fries.";
        }
        return "Fries";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("price", price);
        json.put("type", "Fries");
        json.put("size", size);
        return json;
    }
}
