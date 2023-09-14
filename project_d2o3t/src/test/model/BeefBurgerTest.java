package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeefBurgerTest {
    private BeefBurger burger1;
    private BeefBurger burger2;
    private BeefBurger burger3;
    private BeefBurger burger4;

    @BeforeEach
    public void setup() {
        burger1 = new BeefBurger("Beef burger", 7);
        burger2 = new BeefBurger("Beef burger", 7);
        burger2.removeIngredient("onions");
        burger3 = new BeefBurger("Beef burger", 7);
        burger3.addIngredient("beef patty");
        burger4 = new BeefBurger("Beef burger", 7);
        burger4.addIngredient("onions");
        burger4.addIngredient(("ketchup"));
    }

    @Test
    public void testToStringBeef() {
        String expected = "Beef burger with lettuce, ketchup, tomato, onions, beef patty.";
        assertEquals(expected, burger1.toString());
    }

    @Test
    public void testToStringBeefAdd() {
        String expected = "Beef burger with lettuce, ketchup, tomato, beef patty.";
        assertEquals(expected, burger2.toString());
    }

    @Test
    public void testToStringBeefRemove() {
        String expected = "Beef burger with lettuce, ketchup, tomato, onions, beef patty, beef patty(added).";
        assertEquals(expected, burger3.toString());
    }

    @Test
    public void testToStringBeefMultipleAdd() {
        String expected = "Beef burger with lettuce, ketchup, tomato, onions, beef patty," +
                " onions(added), ketchup(added).";
        assertEquals(expected, burger4.toString());
    }
}
