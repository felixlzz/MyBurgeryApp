package model;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> pastOrders;        //all orders that a customer has placed before

    //Constructs a new list of orders
    public OrderHistory() {
        pastOrders = new ArrayList<Order>();
    }

    //EFFECTS: returns a string representation of the past orders
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Your past orders were: \n");
        for (Order o: pastOrders) {
            s.append(o);
        }
        return s.toString();
    }

    //REQUIRES: order cannot be empty
    //MODIFIES: this
    //EFFECTS: add the current order description to collection of order history
    public void addToPast(Order order) {
        this.pastOrders.add(order);
    }

    //EFFECTS:  return the orders in order
    public List<Order> getPastOrders() {
        return this.pastOrders;
    }
}
