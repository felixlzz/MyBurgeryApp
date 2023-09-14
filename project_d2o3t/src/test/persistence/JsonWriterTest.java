package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests were taken from JsonWriterTest class in:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Order odr = new Order();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOrder() {
        try {
            Order odr = new Order();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(odr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            odr = reader.read();
            assertEquals(0, odr.getTotalPrice());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalOrder() {
        try {
            Order odr = new Order();
            odr.addToOrder(new BeefBurger("Beef Burger", 7));
            odr.addToOrder(new Fries("Fries", 0));
            JsonWriter writer = new JsonWriter("./data/testWriterNormalOrder.json");
            writer.open();
            writer.write(odr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalOrder.json");
            odr = reader.read();
            List<Food> foods = odr.getOrderList();
            assertEquals(2, foods.size());
            checkItem("Beef Burger", 7, foods.get(0));
            checkItem("Fries", 0, foods.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
