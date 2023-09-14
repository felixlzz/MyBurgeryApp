package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Method was taken from JsonTest class in:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTest {
    protected void checkItem(String name, int price, Food food) {
        assertEquals(name, food.getName());
        assertEquals(price, food.getPrice());
    }
}