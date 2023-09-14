package ui;

import model.BeefBurger;
import model.Event;
import model.EventLog;
import model.Order;
import model.SalmonBurger;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame implements ActionListener, MouseListener {
    private JFrame mainFrame;

    private JScrollPane scrollPane;

    private JPanel mainPanel;
    private JPanel burgerPanel;
    private JPanel orderPanel;
    private JPanel welcomePanel;
    private JPanel buttonPanel;


    private JLabel welcomeLabel;
    private JTextArea curOrderLabel;
    private JLabel beefBurgerImage;
    private JLabel salmonBurgerImage;

    private JButton startOrderButton;
    private JButton finishOrderButton;
    private JButton saveOrderButton;
    private JButton loadOrderButton;
    private JButton quitButton;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/favOrder.json";

    private Order curOrder;


    //EFFECTS: Sets up the GUI and main page for the program
    public GUI() {
        setupMainFrame();
        mainFrame.setBackground(Color.orange);

        welcomeLabel = new JLabel("Welcome to the Burgery! What would you like today?");
        welcomeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));

        mainPanel = new JPanel();
        orderPanel = new JPanel();
        welcomePanel = new JPanel();
        burgerPanel = new JPanel();
        buttonPanel = new JPanel();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        addButtons();
        addPanelsAndFrame();
        mainPanel.setBackground(Color.orange);
        setFont();
        setVisibility();
    }

    //EFFECTS: Sets up the mainframe
    public void setupMainFrame() {
        mainFrame = new JFrame("The Bugery App");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 1000);

    }

    //MODIFIES: mainFrame
    //EFFECTS: adds the panels and frame for the main page of the GUI
    public void addPanelsAndFrame() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(orderPanel);
        mainPanel.add(welcomePanel);
        mainPanel.add(burgerPanel);
        mainPanel.add(buttonPanel);
        mainFrame.add(mainPanel);

        ImageIcon beef = new ImageIcon("./lib/images/beefBurger.jpeg");
        beef.setImage(beef.getImage().getScaledInstance(380, 380, Image.SCALE_DEFAULT));
        beefBurgerImage = new JLabel(beef);
        burgerPanel.add(beefBurgerImage);
        beefBurgerImage.addMouseListener(this);

        ImageIcon salmon = new ImageIcon("./lib/images/salmonBurger.jpeg");
        salmon.setImage(salmon.getImage().getScaledInstance(380, 380, Image.SCALE_DEFAULT));
        salmonBurgerImage = new JLabel(salmon);
        burgerPanel.add(salmonBurgerImage);
        salmonBurgerImage.addMouseListener(this);

        welcomePanel.add(welcomeLabel);

        curOrderLabel = new JTextArea("Order Info:");
        curOrderLabel.setEditable(false);

        scrollPane = new JScrollPane(curOrderLabel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        orderPanel.add(curOrderLabel);
    }

    //EFFECTS: Set initial visibility
    public void setVisibility() {
        orderPanel.setVisible(false);
        welcomePanel.setVisible(true);
        burgerPanel.setVisible(true);
        buttonPanel.setVisible(true);
        mainFrame.setVisible(true);
    }

    //MODIFIES: buttonPanel
    //EFFECTS: adds all button options to the buttonPanel on the mainFrame
    public void addButtons() {
        startOrderButton = new JButton("Start your order!");
        finishOrderButton = new JButton("Checkout your order!");
        saveOrderButton = new JButton("Save order as favourite!");
        loadOrderButton = new JButton("Load your favourite order!");
        quitButton = new JButton("Exit...");

        startOrderButton.setActionCommand("start");
        finishOrderButton.setActionCommand("finish");
        saveOrderButton.setActionCommand("save");
        loadOrderButton.setActionCommand("load");
        quitButton.setActionCommand("quit");

        startOrderButton.addActionListener(this);
        finishOrderButton.addActionListener(this);
        saveOrderButton.addActionListener(this);
        loadOrderButton.addActionListener(this);
        quitButton.addActionListener(this);

        buttonPanel.add(startOrderButton);
        buttonPanel.add(finishOrderButton);
        buttonPanel.add(saveOrderButton);
        buttonPanel.add(loadOrderButton);
        buttonPanel.add(quitButton);
    }

    //EFFECTS: sets the font style and size for the buttons and labels
    private void setFont() {
        Font myFont = new Font("Times New Roman", Font.PLAIN, 30);
        startOrderButton.setFont(myFont);
        finishOrderButton.setFont(myFont);
        saveOrderButton.setFont(myFont);
        loadOrderButton.setFont(myFont);
        quitButton.setFont(myFont);
        curOrderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("start")) {
            startOrder();
        } else if (e.getActionCommand().equals("finish")) {
            finishOrder();
        } else if (e.getActionCommand().equals("save")) {
            saveOrder();
        } else if (e.getActionCommand().equals("load")) {
            loadOrder();
        } else if (e.getActionCommand().equals("quit")) {
            quit();
        }
    }

    //MODIFIES: this
    //EFFECTS: Initializes a new instance of order and changes text on screen
    public void startOrder() {
        curOrder = new Order();
        curOrderLabel.setText(null);
        orderPanel.setVisible(true);
        welcomeLabel.setText("Choose the burger(s) you want!");
        curOrderLabel.setBackground(null);
    }

    //EFFECTS: Displays new text indicating the end of an order if an order is not empty
    // else a warning message is displayed
    public void finishOrder() {
        curOrderLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        welcomeLabel.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        if (curOrder != null) {
            curOrderLabel.setText("Thank you for ordering");

            welcomeLabel.setText("Enjoy your meal!");
        } else {
            welcomeLabel.setText("You haven't added anything yet... \n Start an order?");
        }
    }


    //EFFECTS: Saves the current order to JSON directory and displays a message
    public void saveOrder() {
        try {
            jsonWriter.open();
            jsonWriter.write(curOrder);
            jsonWriter.close();

            curOrderLabel.setBackground(Color.yellow);
            curOrderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            curOrderLabel.setText(curOrder.toString());

            welcomeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            welcomeLabel.setText("Successfully saved this order as your favourite!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: Loads the saved order from JSON directory and displays a success message
    // or a warning message if there is no order saved
    public void loadOrder() {
        try {

            curOrder = new Order();
            curOrderLabel.setVisible(true);
            curOrderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            curOrderLabel.setText(curOrder.toString());
            curOrderLabel.setBackground(null);

            welcomeLabel.setVisible(true);
            welcomeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
            curOrder = jsonReader.read();

            if (curOrder.getTotalPrice() > 0) {
                welcomeLabel.setText("Re-order your favourite order! Total price: " + curOrder.getTotalPrice());
            } else {
                welcomeLabel.setText("No favourite order yet...");
            }
            curOrderLabel.setText(curOrder.toString());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: exits the program
    public void quit() {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    //EFFECTS: print the log of the application
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
            System.out.println("\n\n");
        }
    }


    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getLocationOnScreen().getX() < 600) {
            curOrder.addToOrder(new BeefBurger("Beef burger", 7));
            curOrderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            curOrderLabel.setMaximumSize(new Dimension(1000, 200));
            curOrderLabel.setText(curOrder.toString());

            welcomeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            welcomeLabel.setText("Total price: " + curOrder.getTotalPrice());
        } else {
            curOrder.addToOrder(new SalmonBurger("Salmon burger", 8));
            curOrderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            curOrderLabel.setMaximumSize(new Dimension(1000, 200));
            curOrderLabel.setText(curOrder.toString());
            welcomeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            welcomeLabel.setText("Total price: " + curOrder.getTotalPrice());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getLocationOnScreen().getX() < 600) {
            Border blueLine = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue);
            beefBurgerImage.setBorder(blueLine);
        } else {
            Border redLine = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red);
            salmonBurgerImage.setBorder(redLine);
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        beefBurgerImage.setBorder(null);
        salmonBurgerImage.setBorder(null);
    }
}
