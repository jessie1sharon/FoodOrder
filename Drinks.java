package Ordering;

public class Drinks extends Food implements Orderable {
	
	String Beverage;
	
	public Drinks(int price, String size) {
		super(price, size);
		
	}
	
	public Drinks(int price, String size, String beverage) {
		super(price, size);
		this.Beverage = beverage;
		
	}

	@Override
	public void AddItem() {
		String question1 = "Which drink do you want?";
		String answers1[]= {"Coke", "Sprite", "Fanta", "Lemonade","Water", "Orange Juice", "Diet Coke", "Soda"};
		Beverage = Menu.Menu1(question1, answers1);
		
	}

	@Override
	public String PrintOrder() {
		return "Drink - " +
				super.toString() + ", " +
				" : " +
				Beverage;
		
	}

	@Override
	public String toString() {
		return PrintOrder();
		
	}
	
	@Override
	public void DulpicateOrder() {
		Menu.customer_curr.AddOrder(this);
	}
	
}
