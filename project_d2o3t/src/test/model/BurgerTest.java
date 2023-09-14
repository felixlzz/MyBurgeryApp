package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    private BeefBurger burger1;
    private SalmonBurger burger2;
    private List<String> ingredientsTest;
    private List<String> ingredientsTest2;

    @BeforeEach
    public void setup() {
        burger1 = new BeefBurger("Beef burger", 7);
        ingredientsTest = burger1.getIngredients();

        burger2 = new SalmonBurger("Salmon burger", 8);
        ingredientsTest2 = burger2.getIngredients();
    }

    @Test
    public void testBeefConstructor() {
        assertEquals(7, burger1.getPrice());
        assertEquals(ingredientsTest, burger1.getIngredients());
        assertEquals(5, ingredientsTest.size());
        assertTrue(ingredientsTest.contains("beef patty"));
    }

    @Test
    public void testSalmonConstructor() {
        assertEquals(8, burger2.getPrice());
        assertEquals(ingredientsTest2, burger2.getIngredients());
        assertEquals(5, ingredientsTest2.size());
        assertTrue(ingredientsTest2.contains("salmon fillet"));
    }

    @Test
    public void testRemoveOneIngredient() {
        burger1.removeIngredient("onions");
        assertEquals(4, ingredientsTest.size());
        assertFalse(ingredientsTest.contains("onions"));
        assertEquals("lettuce", ingredientsTest.get(0));
        assertEquals("ketchup", ingredientsTest.get(1));
        assertEquals("tomato", ingredientsTest.get(2));
        assertEquals(7, burger1.getPrice());
    }

    @Test
    public void testRemoveMultiple() {
        burger2.removeIngredient("onions");
        burger2.removeIngredient("tomato");
        assertEquals(3, ingredientsTest2.size());
        assertFalse(ingredientsTest2.contains("onions"));
        assertEquals("lettuce", ingredientsTest2.get(0));
        assertEquals("ketchup", ingredientsTest2.get(1));
        assertFalse(ingredientsTest2.contains("tomato"));
        assertEquals("salmon fillet", ingredientsTest2.get(2));
        assertEquals(7, burger1.getPrice());
    }

    @Test
    public void testRemoveMultipleSame() {
        burger1.removeIngredient("onions");
        burger1.removeIngredient("onions");
        assertEquals(4, ingredientsTest.size());
        assertFalse(ingredientsTest.contains("onions"));
        assertEquals("lettuce", ingredientsTest.get(0));
        assertEquals("ketchup",ingredientsTest.get(1));
        assertEquals("tomato", ingredientsTest.get(2));
        assertEquals("beef patty", ingredientsTest.get(3));
        assertEquals(7, burger1.getPrice());
    }

    @Test
    public void testAddOneIngredient() {
        burger1.addIngredient("onions");
        assertEquals(6, ingredientsTest.size());
        assertEquals("lettuce", ingredientsTest.get(0));
        assertEquals("ketchup",ingredientsTest.get(1));
        assertEquals("tomato", ingredientsTest.get(2));
        assertEquals("onions", ingredientsTest.get(3));
        assertEquals("beef patty", ingredientsTest.get(4));
        assertEquals("onions(added)", ingredientsTest.get(5));
        assertEquals(8, burger1.getPrice());
    }

    @Test
    public void testAddMultiple() {
        burger1.addIngredient("onions");
        burger1.addIngredient("tomato");
        assertEquals("lettuce", ingredientsTest.get(0));
        assertEquals("ketchup",ingredientsTest.get(1));
        assertEquals("tomato", ingredientsTest.get(2));
        assertEquals("onions", ingredientsTest.get(3));
        assertEquals("beef patty", ingredientsTest.get(4));
        assertEquals("onions(added)", ingredientsTest.get(5));
        assertEquals("tomato(added)", ingredientsTest.get(6));
        assertEquals(9, burger1.getPrice());
    }

    @Test
    public void testAddMultipleSame() {
        burger1.addIngredient("onions");
        burger1.addIngredient("onions");
        assertEquals("lettuce", ingredientsTest.get(0));
        assertEquals("ketchup",ingredientsTest.get(1));
        assertEquals("tomato", ingredientsTest.get(2));
        assertEquals("onions", ingredientsTest.get(3));
        assertEquals("beef patty", ingredientsTest.get(4));
        assertEquals("onions(added)", ingredientsTest.get(5));
        assertEquals("onions(added)", ingredientsTest.get(6));
        assertEquals(9, burger1.getPrice());
    }

    @Test
    public void testAddOneIngredientS() {
        burger2.addIngredient("onions");
        assertEquals(6, ingredientsTest2.size());
        assertEquals("lettuce", ingredientsTest2.get(0));
        assertEquals("ketchup",ingredientsTest2.get(1));
        assertEquals("tomato", ingredientsTest2.get(2));
        assertEquals("onions", ingredientsTest2.get(3));
        assertEquals("salmon fillet", ingredientsTest2.get(4));
        assertEquals("onions(added)", ingredientsTest2.get(5));
        assertEquals(7, burger1.getPrice());
    }

    @Test
    public void testAddMultipleS() {
        burger2.addIngredient("onions");
        burger2.addIngredient("tomato");
        assertEquals("lettuce", ingredientsTest2.get(0));
        assertEquals("ketchup",ingredientsTest2.get(1));
        assertEquals("tomato", ingredientsTest2.get(2));
        assertEquals("onions", ingredientsTest2.get(3));
        assertEquals("salmon fillet", ingredientsTest2.get(4));
        assertEquals("onions(added)", ingredientsTest2.get(5));
        assertEquals("tomato(added)", ingredientsTest2.get(6));
        assertEquals(10, burger2.getPrice());
    }

    @Test
    public void testAddMultipleSameS() {
        burger2.addIngredient("onions");
        burger2.addIngredient("onions");
        assertEquals("lettuce", ingredientsTest2.get(0));
        assertEquals("ketchup",ingredientsTest2.get(1));
        assertEquals("tomato", ingredientsTest2.get(2));
        assertEquals("onions", ingredientsTest2.get(3));
        assertEquals("salmon fillet", ingredientsTest2.get(4));
        assertEquals("onions(added)", ingredientsTest2.get(5));
        assertEquals("onions(added)", ingredientsTest2.get(6));
        assertEquals(10, burger2.getPrice());
    }

}