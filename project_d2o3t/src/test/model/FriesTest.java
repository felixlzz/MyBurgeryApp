package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FriesTest {
    private Fries friesR;
    private Fries friesL;
    private Fries friesSize;

    @BeforeEach
    public void setup() {
        friesSize = new Fries("Fries", 4, "Regular");
        friesR = new Fries("Fries", 0);
        friesL = new Fries("Fries", 0);
        friesR.setSizeR();
        friesL.setSizeL();
    }

    @Test
    public void testConstructorSize() {
        assertEquals("Regular", friesSize.getSize());
        assertEquals(4, friesSize.getPrice());
    }

    @Test
    public void testSetPriceR() {
        assertEquals("Regular", friesR.getSize());
        assertEquals(3, friesR.getPrice());
    }

    @Test
    public void testSetPriceL() {
        assertEquals("Large", friesL.getSize());
        assertEquals(5, friesL.getPrice());
    }

    @Test
    public void testToStringR() {
        String expected = "Regular fries.";
        assertEquals(expected, friesR.toString());
    }

    @Test
    public void testToStringL() {
        String expected = "Large fries.";
        assertEquals(expected, friesL.toString());
    }
}
