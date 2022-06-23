package Ordering;
//Group number 3
//Margarita kaplan 321775579
//Jessica Llanos 327083184
//Yarden Phahima 318647211
//Shahar Ganani 208983247

import javax.swing.JList;


public class Pizza extends Food implements Orderable {
	
	String Dough;
	java.util.List<Object> Toppings;
	
	public Pizza(int price, String size) {
		super(price, size);

	}
	
	public Pizza(int price, String size, String dough , java.util.List<Object> toppings) {
		super(price, size);
		this.Dough = dough;
		this.Toppings = toppings;
	}
	
	@Override
	public void AddItem() {
		String question1 = "What kind of dough do you want?";
		String answers1[]= {"Thin-Crust", "Thick-Crust"};
		Dough = Menu.Menu1(question1, answers1);
		
		String question2 = "What kind of topping do you want on your pizza?\nMore than 1 topping is extra 5 for every topping"
				+ "\nTo choose multiple toppings please press ctrl and click";
		JList<Object> list = new JList<Object>(new String[] {"Green Olives", "Mushrooms", "Peppers", "Tomatoes", "Onion", "Garlic", "Tuna", "Corn", "Mozeralla", "Black Olives"});
		Menu.ShowMessage(question2);
		Toppings = Menu.MultiChoice(list);
		
		if (Toppings.size() > 1 ) {
			this.Price += (Toppings.size()-1 )*5 ;
		}  
		
	}
	
	@Override
	public String PrintOrder() {
		String toPrint = "Pizza - " + super.toString() +
				" , with : dough " +
				this.Dough + ", and the toppings " +
				this.Toppings;
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
