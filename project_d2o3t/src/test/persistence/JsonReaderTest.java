package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests were taken from JsonReaderTest class in:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Order odr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrder.json");
        try {
            Order odr = reader.read();
            assertEquals(0, odr.getTotalPrice());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalOrder() {
        JsonReader reader = new JsonReader("./data/testReaderNormalOrder.json");
        try {
            Order odr = reader.read();
            List<Food> foods = odr.getOrderList();
            assertEquals(2, foods.size());
            checkItem("Beef Burger", 7, foods.get(0));
            checkItem("Fries", 0, foods.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}