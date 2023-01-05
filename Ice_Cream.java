package Ordering;

import java.util.ArrayList;
import javax.swing.JList;

public class Ice_Cream extends Food implements Orderable {
	
	String Container;
	java.util.List<Object> Flavor ;
	ArrayList<String> Toppings = new ArrayList<String>();
	
	public Ice_Cream(int price, String size) {
		super(price, size);
	
	}

	@Override
	public void AddItem() {
		
		int k = 0;               // num of flavors by the size
		
		String question1 = "Do you want a cone or a cup?";
		String answers1[]= {"Cone", "Cup"};
		Container = Menu.Menu1(question1, answers1);
		
		if (getSize() == "S") {                 //setting the num of flavors
			k = 2;
		}
		else if (getSize() == "M") {
			k =4;
		}
		else if (getSize() == "L") {                // the client can add k flavors
			k = 6;
		}
		
		
		String question2 = "What ice-cream flavor do you want?\nYou can have up to " + k + " flavors\nMore flavors will be 4 shekels per flavor"
				+"\nTo select multiple flavors press ctrl and click";
		Menu.ShowMessage(question2);
		JList<Object> list = new JList<Object>(new String[] {"Chocolate", "Vanilla","Pistachio", "Coffee","Cookie and Cream", "Mint", "Strawberry", "Mango", "Kinder","Passionfruit"});
		Flavor = Menu.MultiChoice(list);
		
		if (Flavor.size() > k ) {
			this.Price += (Flavor.size()-k )*4 ;
		}  
		
		String question3= "What kind of topping do you want on your ice cream?";    // choosing the toppings
		String answers3[]= {"Sprinkles", "Hot Fudge", "None"};
		Toppings.add(Menu.Menu1(question3, answers3));	
		
		if(!Toppings.contains("None"))
		{
		String question4= "Do you want another topping on your ice cream?\nIt will cost 2 shekels more";   // if wants 2 toppings
		String answers4[]= {"Yes", "No"};
		String ans = Menu.Menu1(question4, answers4);
		
		if (ans.equals("Yes")) {    //if wants the 2 toppings and add to price
			String question5= "What kind of topping do you want on your ice cream?";
			String answers5[]= {"Sprinkles", "Hot Fudge", "None"};
			Toppings.add(Menu.Menu1(question5, answers5));	
			
			if (Toppings.size() > 1 ) {
				this.Price += (Flavor.size()-1 )*2 ;
			}  
		}
		}
	}

	@Override
	public String PrintOrder() {
		String toPrint = "Ice-Cream - " +
				super.toString() + ", " +
				"with : in a " +
				Container + ", with the flavors " +
				Flavor + ", and the toppings " +
				Toppings;
		return toPrint;
		
	}
	
	@Override
    public String toString() {
		return PrintOrder();
		
	}

    public void DulpicateOrder() {
	    Menu.customer_curr.AddOrder(this);
    }
	
}
