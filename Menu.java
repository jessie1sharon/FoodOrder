package Ordering;

import java.awt.Image;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.*;


public class Menu {
	
	JFrame f;
	
	static Customer customer_first = new Customer("me","home");        // the customer for finished orders
	static Customer customer_curr = new Customer("me1","home1");              // the current customer(unfinished orders)
	
	static {                                                           // the past orders
		Orderable order1 = null;
		Calendar calendar = Calendar.getInstance();
		
		java.util.List<Object> Vegtables1 = List.of("Onion","Lettuce","Tomatoe","Pickles");
		java.util.List<Object> Sauces1 = List.of("Ketchup");
		Hamburger hamburger = new Hamburger(30, "M", "Medium", "Beef", "White Bread", Sauces1, Vegtables1, "French Fries");
		
		Drinks drink = new Drinks(10, "M", "Fanta");
		
		java.util.List<Object> Toppings1 = List.of("Green Olives","Onion");
		Pizza pizza = new Pizza(55, "L", "Thick-Crust", Toppings1);
		
		order1 = hamburger;
		calendar.set(2019, 12, 1, 12, 15, 32);
		customer_first.AddOrder(order1, calendar.getTime());        // adding the item to the order
		order1 = drink;
		calendar.set(2019, 12, 5, 14, 00, 11);
		customer_first.AddOrder(order1, calendar.getTime());        // adding the item to the order
		order1 = pizza;
		calendar.set(2019, 12, 12, 20, 30, 54);
		customer_first.AddOrder(order1, calendar.getTime());        // adding the item to the order
		
	}
	
	public Menu() {                       // the start message
		
		f = new JFrame();  
		
		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");            // load the image to a imageIcon
		Image image = icon.getImage();                              // transform it
		Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH);     // scale it the smooth way
		icon = new ImageIcon(newimg);                              // transform it back

		JOptionPane.showMessageDialog(null, "Hello!\nYou arrived to JRSY delivery",  "Start menu", JOptionPane.INFORMATION_MESSAGE, icon);
		
	}
	
	public static String Menu1(String question, String[] options) {      // for asking questions and return answer
		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");
        Image image = icon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
	    icon = new ImageIcon(newimg);  // transform it back
	    
        String n = (String)JOptionPane.showInputDialog(null, question,  
                "JRSY", JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
       return n;
	}

	public static void ShowMessage(String message) {
		
		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");
        Image image = icon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
	    icon = new ImageIcon(newimg);  // transform it back 
	    
		JOptionPane.showMessageDialog(null, message,  "JRSY", JOptionPane.INFORMATION_MESSAGE, icon);
		
	}


	public static String AskMessage(String message) {
		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg);  // transform it back

		return (String) JOptionPane.showInputDialog(null, message,
				"Client info", JOptionPane.QUESTION_MESSAGE, icon, null, null);
	}
	
	public static java.util.List<Object> MultiChoice( JList<Object> list) {      //to choose multiple answers
		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");
        Image image = icon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
	    icon = new ImageIcon(newimg);  // transform it back
	    
	    JOptionPane.showMessageDialog(null, list, "JRSY", JOptionPane.INFORMATION_MESSAGE, icon);
	    java.util.List<Object> answer = list.getSelectedValuesList();
	    return answer;
	    
	}
	
    public static List<Orderable> MultiChoice2( JList<Orderable> list) {      //to choose multiple answers
		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");
        Image image = icon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
	    icon = new ImageIcon(newimg);  // transform it back
	    
	    JOptionPane.showMessageDialog(null, list, "JRSY", JOptionPane.INFORMATION_MESSAGE, icon);
	    java.util.List<Orderable> answer = list.getSelectedValuesList();
	    return answer;
	    
	}

 	public static void main(String[] args) {
		
		new Menu().mainMenu();
	}

	public void mainMenu() {            // the start menu
	    
	    boolean hasOrdered = false;                // for if we want to print when the customer didn't order yet
	    
		do {
		
		String question = "What do you want to do?";
		String[] options = {"Order", "Print Order", "Cancel Order", "Search Order","Past Orders","Past Orders sorted by", "Finish Order"};
        
		String answer = Menu1(question, options);

		if (answer == null) {

			return;
		}
		
		Orderable order = null;
		
		switch (answer) {

		case "Order":
			order = orderingMenu();          // going to the ordering menu to select food
			if (order == null) break;
			
			customer_curr.AddOrder(order);        // adding the item to the order (to the unfinished orders)
			
			if (hasOrdered == false) hasOrdered = true;     // updating that we ordered
			break;

		case "Print Order":
			if (hasOrdered == false) {
				String messa1 = "You didn't order yet";     // if the client didn't order anything yet
				ShowMessage(messa1);
				break;
			}
			
			customer_curr.PrintOrders();       // print the orders
			break;
		
		case "Cancel Order":                   // cancel a order
			customer_first.CancelOrder();
			break;

		case "Search Order":
			customer_first.SearchOrder();     // search for a past order or a finished order
			break;
			
		case "Past Orders":
			customer_first.PrintOrders();           // print past orders 
			question = "Do you want to duplicate an order? ";
			String[] options1 = {"Yes", "No"};  
			answer = Menu1(question, options1);
			// ask if wants to select one of them and then use duplicate to add the past order to the current
			
			if(answer == null) {break;}
			
			if (answer.equals("Yes")) {                    // duplicating the past orders the client wants
				DefaultListModel<Orderable> list = new DefaultListModel<Orderable>() ;
				for (int i = 0; i<customer_first.NumOfOrders(); i++) {
					list.addElement(customer_first.orders.get(i));
				}
				JList<Orderable> listd = new JList<Orderable>(list);
				String msg = "Which order do you want to duplicate to current order?\nTo select multiple press ctrl and click"	;
				Menu.ShowMessage(msg);
				java.util.List<Orderable> dupOrders = Menu.MultiChoice2(listd);
				for (int i=0; i<dupOrders.size();i++) {
					dupOrders.get(i).DulpicateOrder();
				}
				String msg1 = "Items were added to your order";
				Menu.ShowMessage(msg1);
				hasOrdered = true;
			}
			
			
			break;

		case "Past Orders sorted by":
			question = "According to...";
			String[] options2 = {"Price", "Date"};        // sorting the orders by price or date
			answer = Menu1(question, options2);
			if (answer == null) {
				break;
			}
			if (answer.equals("Price")) {                 // print the orders by price
				List<Orderable> sortedOrders = new ArrayList<>();
				List<Date> sortedDates = new ArrayList<>();
				customer_first.sortOrdersByPrice(sortedOrders, sortedDates);
				customer_first.PrintOrders(sortedOrders, sortedDates);
				break;
			}
			else if (answer.equals("Date")) {
				customer_first.PrintOrders();           // print past orders by date
				break;
			}
			else {
				System.out.println("Error: the code shouldn't reach this line");
			}
			
		case "Finish Order":
			customerMenu();        // asking the client for them name & address
            FinishMenu();          //closing & sending the order
			break;

		default:
	        System.out.println("Please try again");
	        break;

		}

		}while(true);
	}

	private void FinishMenu() {      //confirm the client info and send order
		
		String info = "Your name is : " + customer_curr.toString() + "\nWith " + customer_curr.NumOfOrders() + " number of items in the order" ;
		ShowMessage(info);
		customer_curr.PrintOrders();                   // showing the info to the client
		
		String question1 = "Sending the order to " + customer_curr.getAddress() + "\nYou can cancel the order in the next 10 min if desired\nFinish the order?";      // the option to go back or send
		String maybe1[] = {"Done", "Cancel"};
		String answer1 = Menu1(question1 , maybe1);
		if (answer1 == null) return;

		if (answer1.equals("Done")) {
			List<Orderable> orders = customer_curr.getOrders();
			List<Date> dates = customer_curr.getDates();
			for (int i = 0; i < orders.size(); i++) {
				customer_first.AddOrder(orders.get(i));
			}
			orders.clear();
			dates.clear();
		}
		
		// *****************************************
		
	}

	private void customerMenu() {      // set the name and address of the client

		ImageIcon icon = new ImageIcon("src/Ordering/order.jpg");
        Image image = icon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(120, 120,  Image.SCALE_SMOOTH); // scale it the smooth way
	    icon = new ImageIcon(newimg);  // transform it back
	    
		String question = "Please enter your name";    // getting the name

		String customerName = null;
		do {
			
			customerName = (String) JOptionPane.showInputDialog(null, question,
					"Client info", JOptionPane.QUESTION_MESSAGE, icon, null, null);
		} while (customerName == null);

		
		
		String question1 = "Please enter your address:";  // getting the address
		
		String customerAddress = null;
		do {
			
			customerAddress = (String) JOptionPane.showInputDialog(null, question1,
					"Client info", JOptionPane.QUESTION_MESSAGE, icon, null, null);
		} while (customerAddress == null);

		customer_curr.setNameAddress(customerName, customerAddress);     // setting the name for the current customer
	}

	public Orderable orderingMenu() {       // Get a single order of food, and return it
	    
	    String messa = "We have the next options :\nHamburger\nPizza\nNoodles\nIce-Cream\nDrinks";
	    ShowMessage(messa);
	    
		String question = "What do you want to order?";
		String[] options = {"Hamburger", "Pizza", "Noodles", "Ice-Cream", "Drinks"};

		String answer = Menu1(question, options);

		if (answer == null) {

			return null;
		}

		String question1 = "What size of " + answer + "?";
		String[] options1 = {"S", "M", "L"};                // decide the size of the item

		String size = Menu1(question1, options1);
		int price = 0;

		Orderable order = null;

		switch (answer) {

			case "Hamburger":                       // going to the hamburger section
				if (size == "S" ) {
					price = 20;
				}
				else if (size == "M") {
					price = 30;
				}
				else if (size == "L") {
					price = 40;
				}
				Hamburger food1 = new Hamburger(price, size);
				food1.AddItem();                   // adding hamburger
				order = food1;
				break;

			case "Pizza":                            // going to the pizza section
				if (size == "S" ) {
					price = 25;
				}
				else if (size == "M") {
					price = 40;
				}
				else if (size == "L") {
					price = 50;
				}
				Pizza food2 = new Pizza(price, size);
				food2.AddItem();                     // adding pizza
				order = food2;
				break;

			case "Noodles":                          // going to the noodles section
				if (size == "S" ) {
					price = 30;
				}
				else if (size == "M") {
					price = 35;
				}
				else if (size == "L") {
					price = 40;
				}
				Noodles food3 = new Noodles(price, size);
				food3.AddItem();                      // adding noodles
				order = food3;
				break;

			case "Ice-Cream":                            // going to the ice-cream section
				if (size == "S" ) {
					price = 12;
				}
				else if (size == "M") {
					price = 17;
				}
				else if (size == "L") {
					price = 20;
				}
				Ice_Cream food4 = new Ice_Cream(price, size);
				food4.AddItem();                       // adding ice-cream
				order = food4;
				break;

			case "Drinks":                            // going to the drinks section
				if (size == "S" ) {
					price = 8;
				}
				else if (size == "M") {
					price = 10;
				}
				else if (size == "L") {
					price = 12;
				}
				Drinks drink = new Drinks(price, size);
				drink.AddItem();                         //adding drink
				order = drink;
				
				break;

			default:
				System.out.println("Please try again");
				break;

		}

		return order;

	}
}
