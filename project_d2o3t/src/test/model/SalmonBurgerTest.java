package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalmonBurgerTest {
    private SalmonBurger burger1;
    private SalmonBurger burger2;
    private SalmonBurger burger3;
    private SalmonBurger burger4;
    private SalmonBurger burger5;

    @BeforeEach
    public void setup() {
        burger1 = new SalmonBurger("Salmon burger", 8);
        burger2 = new SalmonBurger("Salmon burger", 8);
        burger2.removeIngredient("salmon fillet");
        burger3 = new SalmonBurger("Salmon burger", 8);
        burger3.addIngredient("onions");
        burger4 = new SalmonBurger("Salmon burger", 8);
        burger4.addIngredient("tomato");
        burger4.addIngredient("ketchup");
        burger5 = new SalmonBurger("Salmon burger", 8);
        burger5.removeIngredient("tomato");
        burger5.removeIngredient("ketchup");
    }

    @Test
    public void testToStringBeef() {
        String expected = "Salmon burger with lettuce, ketchup, tomato, onions, salmon fillet.";
        assertEquals(expected, burger1.toString());
    }

    @Test
    public void testToStringBeefAdd() {
        String expected = "Salmon burger with lettuce, ketchup, tomato, onions.";
        assertEquals(expected, burger2.toString());
    }

    @Test
    public void testToStringBeefRemove() {
        String expected = "Salmon burger with lettuce, ketchup, tomato, onions, salmon fillet, onions(added).";
        assertEquals(expected, burger3.toString());
    }

    @Test
    public void testToStringSalmonMultipleAdd() {
        String expected = "Salmon burger with lettuce, ketchup, tomato, onions, salmon fillet," +
                " tomato(added), ketchup(added).";
        assertEquals(expected, burger4.toString());
    }

    @Test
    public void testToStringSalmonMultipleR() {
        String expected = "Salmon burger with lettuce, onions, salmon fillet.";
        assertEquals(expected, burger5.toString());
    }
}

