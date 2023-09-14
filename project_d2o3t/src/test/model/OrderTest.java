package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order1;
    private Order order2;
    private BeefBurger burger1;
    private SalmonBurger burger2;
    private List<Food> foods;
    private List<Food> foods2;
    private Fries regFries;


    @BeforeEach
    public void setup() {
        order1 = new Order();
        order2 = new Order();
        burger1 = new BeefBurger("Beef burger", 7);
        burger2 = new SalmonBurger("Salmon burger", 8);
        regFries = new Fries("Fries", 0);
        regFries.setSizeR();
        order1.addToOrder(burger1);
        order2.addToOrder(burger2);
        order2.addToOrder(regFries);
        foods = order1.getOrderList();
        foods2 = order2.getOrderList();
    }

    @Test
    public void testAddToOrderOne() {
        assertEquals(7, order1.getTotalPrice());
        assertEquals(burger1, foods.get(0));
        assertFalse(foods.contains(burger2));
        assertFalse(foods.contains(regFries));
    }

    @Test
    public void testAddToOrderMultiple() {
        assertEquals(3+8, order2.getTotalPrice());
        assertEquals(burger2, foods2.get(0));
        assertEquals(regFries, foods2.get(1));
    }
}
