package ui;

import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//loadFavOrder() and saveFavOrder() were taken from saveWorkroom() and loadWorkroom() in:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class BurgerApp {
    private static final String JSON_STORE = "./data/favOrder.json";
    private Order currentOrder;
    private OrderHistory pastOrders;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the burger application
    public BurgerApp() {
        runBurger();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runBurger() {
        boolean keepGoing = true;
        String command;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for your visit! \n See you next time!");
    }

    //EFFECTS: displays menu of options to the user
    private void displayMenu() {
        System.out.println("\nThe Burgery Menu:");
        System.out.println("\ta -> Start an order");
        System.out.println("\tc -> See current order");
        System.out.println("\tf -> Finish the order");
        System.out.println("\tp -> See past orders");
        System.out.println("\ts -> Save this order as favourite");
        System.out.println("\tl -> Load favourite order");
        System.out.println("\tq -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user commands
    private void processCommand(String command) {
        if (command.equals("a")) {
            startOrder();
        } else if (command.equals("c")) {
            showCurrentOrder();
        } else if (command.equals("f")) {
            finishOrder();
        } else if (command.equals("p")) {
            showPastOrder();
        } else if (command.equals("s")) {
            saveFavOrder();
        } else if (command.equals("l")) {
            loadFavOrder();
        } else {
            System.out.println("Please select from the available choices...");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads favourite order from file
    private void loadFavOrder() {
        try {
            currentOrder = jsonReader.read();
            System.out.println("Loaded your favourite order to your current order from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the order to file
    private void saveFavOrder() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentOrder);
            jsonWriter.close();
            System.out.println("Saved this order as your favourite to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //Constructs a new Order (clears out previous content), and guides customer through ordering burger and fries.
    private void startOrder() {
        this.currentOrder = new Order();
        Burger selected = selectBurger();
        String choice = "";
        String choice2 = "";
        processOrder(choice, selected);
        System.out.println("Confirm add to order?");
        while (!(choice2.equals("y") || choice2.equals("n"))) {
            System.out.println(selected + "\n y -> Add this burger to current order");
            choice2 = input.next();
            choice2 = choice2.toLowerCase();
            if ((choice2.equals("y"))) {
                break;
            }
        }
        if (choice2.equals("y")) {
            this.currentOrder.addToOrder(selected);
        } else {
            System.out.println("Please input a valid selection...");
        }
        String choice3 = "";
        addFries(choice3);
    }

    //EFFECTS: prompts user with a choice of adding fries to order
    private void addFries(String choice3) {
        Fries fries = new Fries("Fries", 0);
        System.out.println("Would you like some fries with your order?");
        while (!(choice3.equals("y") || choice3.equals("n"))) {
            System.out.println("\n y -> Add fries to current order \n n -> Do not add fries");
            choice3 = input.next();
            choice3 = choice3.toLowerCase();
            if ((choice3.equals("y") || choice3.equals("n"))) {
                break;
            }
        }
        if (choice3.equals("y")) {
            String answer = "";
            chooseSize(answer, fries);
            this.currentOrder.addToOrder(fries);
            System.out.println(fries.getSize() + " fries has been added");
        } else if (choice3.equals("n")) {
            System.out.println("No fries have been added");
        } else {
            System.out.println("Please input a valid selection...");
        }


    }

    //MODIFIES: this
    //EFFECTS: prompts user to select the size for their fries order
    private void chooseSize(String answer, Fries fries) {
        while (!(answer.equals("r") || answer.equals("l"))) {
            System.out.println("What size? \n r -> Regular \n l -> Large");
            answer = input.next();
            answer = answer.toLowerCase();
            if ((answer.equals("r") || answer.equals("l"))) {
                break;
            }
        }
        if (answer.equals("r")) {
            fries.setSizeR();
        } else if (answer.equals("l")) {
            fries.setSizeL();
        } else {
            System.out.println("Please input a valid selection...");
        }

    }

    //MODIFIES: this
    //EFFECTS: prompts user to modify their burger in the order
    private void processOrder(String choice, Burger selected) {
        while (!(choice.equals("a") || choice.equals("r"))) {
            System.out.println(selected + "\n a -> Add ingredients \n r -> Remove ingredients");
            choice = input.next();
            choice = choice.toLowerCase();
            if ((choice.equals("a") || choice.equals("r"))) {
                break;
            }
        }

        if (choice.equals("a")) {
            System.out.println("which ingredient?");
            String i = input.next();
            selected.addIngredient(i);
        } else if (choice.equals("r")) {
            System.out.println("which ingredient?");
            String r = input.next();
            selected.removeIngredient(r);
        } else {
            System.out.println("Please input a valid selection...");
        }
    }

    //EFFECTS: prompts user to select a type of burger to order
    private Burger selectBurger() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("beef") || selection.equals("salmon"))) {
            System.out.println("beef -> View beef burger");
            System.out.println("salmon -> View salmon burger");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("beef")) {
            return new BeefBurger("Beef burger", 7);
        } else {
            return new SalmonBurger("Salmon burger", 8);
        }
    }

    //REQUIRES: non-empty order list (has to initiate an order with s before using this method)
    //EFFECTS: prints out the detail of this order, as well as the current total price
    private void showCurrentOrder() {
        System.out.println(currentOrder);
        System.out.println("Your current total is: " + currentOrder.getTotalPrice());
    }

    //MODIFIES: this
    //EFFECTS: prompts user to finish the order, if yes, prints out a finish message
    //if not, then order continues
    private void finishOrder() {
        String isDone = "";
        while (!(isDone.equals("y") || isDone.equals("n"))) {
            System.out.println("Are you finished with this order? \n y -> yes \n n -> no");
            isDone = input.next();
            isDone = isDone.toLowerCase();
        }

        if (isDone.equals("y")) {
            System.out.println("Thank you for your order!\n Your total is: $" + currentOrder.getTotalPrice());
            this.pastOrders.addToPast(currentOrder);
            this.currentOrder = new Order();
        } else if (isDone.equals("n")) {
            System.out.println("Please continue your order!");
        } else {
            System.out.println("Please input a valid selection...");
        }
    }

    //EFFECTS: print our past orders
    private void showPastOrder() {
        System.out.println(pastOrders);
    }

    // MODIFIES: this
    // EFFECTS: initializes the order
    private void initialize() {
        currentOrder = new Order();
        pastOrders = new OrderHistory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

}
