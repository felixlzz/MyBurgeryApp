package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Order implements Writable {
    private List<Food> orderList;        //all the food items added to this order

    //EFFECTS: constructs an order that starts with no food items and tracks the food items being added
    public Order() {
        orderList = new ArrayList<>();
    }

    //EFFECTS: returns a string representation of this current Order
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Your order includes: \n");
        for (Food f : orderList) {
            s.append(f);
            s.append("\n");
        }
        EventLog.getInstance().logEvent(new Event("Order info is updated!"));
        return s.toString();
    }

    //EFFECTS: return the integer representation for total price of the order
    public int getTotalPrice() {
        int i = 0;
        for (Food f : orderList) {
            i += f.getPrice();
        }
        return i;
    }

//    //EFFECTS: return the integer representation of price
//    public int getPrice() {
//        return price;
//    }

    //MODIFIES: this
    //EFFECTS: adds a food item to the current order
    public void addToOrder(Food f) {
        orderList.add(f);
        EventLog.getInstance().logEvent(new Event(f.toString() + " has been added to order!"));
    }


    //EFFECTS: return the foods in the current order
    public List<Food> getOrderList() {
        return this.orderList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("order", foodsToJson());
        EventLog.getInstance().logEvent(new Event("Order saved!"));
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray foodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f: orderList) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
