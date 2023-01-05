package Ordering;

import javax.swing.JList;


public class Hamburger extends Food implements Orderable {

	String Doneness;
	String PattyType;
	String BunType;
	java.util.List<Object> Sauce;
	java.util.List<Object> Vegtable;
	String Extras;
	
	public Hamburger(int price, String size) {
		
		super(price, size);
		
	}
	
    public Hamburger(int price, String size, String doneness, String pattyType, String bunType, java.util.List<Object> sauce, java.util.List<Object> vegtable, String extras) {
		
		super(price, size);
		this.Doneness = doneness;
		this.PattyType = pattyType;
		this.BunType = bunType;
		this.Sauce = sauce;
		this.Vegtable = vegtable;
		this.Extras = extras;
		
	}

	@Override
	public void AddItem() {
		
		String question2 = "How do you want your hamburger done?";
		String answers2[]= {"Rare","Medium-Rare", "Medium","Medium-Well", "Well-Done"};
		Doneness = Menu.Menu1(question2, answers2);
		
		String question1 = "What kind of patty do you want?";
		String answers1[]= {"Vegi", "Beef","Lamb", "Tofu","Chicken"};
		PattyType = Menu.Menu1(question1, answers1);
		
		String question3 = "What kind of bun do you want?";
		String answers3[]= {"White Bread","Whole-grain"};
		BunType = Menu.Menu1(question3, answers3);
		
		String question4 = "What kind of sauce do you want?\n"
				+"To select multiple sauces press ctrl and click";
		Menu.ShowMessage(question4);
		JList<Object> list = new JList<Object>(new String[] {"Mayo","Ketchup", "BBQ", "Spicy Mayo", "Sweet and Sour","Sweet Chili"});
		Sauce = Menu.MultiChoice(list);
		
		String question5 = "Which vegtables do you want in your hamburger?\n"
				+"To select multiple vegtables press ctrl and click";
		Menu.ShowMessage(question5);
		JList<Object> list2 = new JList<Object>(new String[]{"Pickles","Tomatoes", "Lettuce", "Onion"});
		Vegtable = Menu.MultiChoice(list2);
		
		String question6 = "Do you want extras with your hamburger?";
		String answers6[]= {"Home-fries","French Fries", "Onion Rings","Apple Slices", "Mushed Potatoes"};
		Extras = Menu.Menu1(question6, answers6);
				
	}

	@Override
	public String PrintOrder() {
		return "Hamburger - " +
				super.toString() + ", " +
				"with : " +
				Doneness + ", and " +
				PattyType + ", " +
				BunType + ", and sauce " +
				Sauce + ", and vegtables " +
				Vegtable + ", with a " +
				Extras +" aside ";
		
	}
	
	@Override
	public String toString() {
		return PrintOrder();
		
	}

    public void DulpicateOrder() {
	    Menu.customer_curr.AddOrder(this);
    }
}
