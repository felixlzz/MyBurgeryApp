package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderHistoryTest {
    private OrderHistory orderHistory1;
    private OrderHistory orderHistory2;
    private List<Order> pastOrders;
    private List<Order> pastOrders2;
    private Order order1;
    private Order order2;
    private Food burger;
    private Fries fries;

    @BeforeEach
    public void setup() {
        orderHistory1 = new OrderHistory();
        orderHistory2 = new OrderHistory();
        burger = new BeefBurger("Beef burger", 7);
        fries = new Fries("Fries", 0);
        fries.setSizeR();
        order1 = new Order();
        order1.addToOrder(burger);
        order2 = new Order();
        order2.addToOrder(fries);
        order2.addToOrder(burger);
        pastOrders = orderHistory1.getPastOrders();
        pastOrders2 = orderHistory2.getPastOrders();
        orderHistory1.addToPast(order1);
        orderHistory2.addToPast(order1);
        orderHistory2.addToPast(order2);
    }

    @Test
    public void testAddToPastOne() {
        assertEquals(1, pastOrders.size());
        assertEquals(order1, pastOrders.get(0));
        assertFalse(pastOrders.contains(order2));
    }

    @Test
    public void testAddToPastMultiple() {
        assertEquals(2, pastOrders2.size());
        assertEquals(order1, pastOrders2.get(0));
        assertEquals(order2, pastOrders2.get(1));
    }

    @Test
    public void testToStringOne() {
        String expected = "Your past orders were: \n" + "Your order includes: \n" +
                "Beef burger with lettuce, ketchup, tomato, onions, beef patty.\n";
        assertEquals(expected, orderHistory1.toString());
    }

    @Test
    public void testToStringMultiple() {
        String expected = "Your past orders were: \n" + "Your order includes: \n" +
        "Beef burger with lettuce, ketchup, tomato, onions, beef patty.\n" + "Your order includes: \n" +
        "Regular fries.\n" + "Beef burger with lettuce, ketchup, tomato, onions, beef patty.\n";
                assertEquals(expected, orderHistory2.toString());
    }
}
