package model;

import java.util.ArrayList;

//Represents a burger on the menu with ingredients and a price
public abstract class Burger extends Food {
    protected ArrayList<String> ingredients;    //list of ingredients in a burger


    //EFFECTS: a burger is created with a set of ingredients
    public Burger(String name, int price) {
        ingredients = new ArrayList<>();
        ingredients.add("lettuce");
        ingredients.add("ketchup");
        ingredients.add("tomato");
        ingredients.add("onions");
        this.name = name;
        this.price = price;
    }



    //REQUIRES: the input string must match one of the ingredients already in the burger
    //MODIFIES: this
    //EFFECTS: removing an existing ingredient (string) from the ingredients' list, does NOT affect price
    public void removeIngredient(String s) {
        if (ingredients.contains(s)) {
            ingredients.remove(s);
        }
    }

    //REQUIRES: the input string must match one of the ingredients
    //MODIFIES: this and price
    //EFFECTS: adding an ingredient (string) from the ingredients' list and add one dollar to the price of the burger
    //same ingredients can be repeatedly added
    public void addIngredient(String s) {
        ingredients.add(s + "(added)");
        price += 1;
    }


    //EFFECTS: return the array list of ingredients in order
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

}

