package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public abstract class Food implements Writable {
    protected String name;                      //name of food item
    protected int price;                        //price of food item

    //EFFECTS: return the int of current price of the food item
    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}


