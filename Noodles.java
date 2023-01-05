package Ordering;

import javax.swing.JList;


public class Noodles extends Food implements Orderable {
	
	String NoodleType;
	java.util.List<Object> VegtableType;
	String MeatType;
	java.util.List<Object> SauceType;
	
	public Noodles(int price, String size) {
		super(price, size);
		
	}

	@Override
	public void AddItem() {
		String question1 = "What type of noodles do you want?";
		String answers1[]= {"Rice Noodles", "Egg noodles","Glutn-free noodles"};
		NoodleType = Menu.Menu1(question1, answers1);
		
		String question2 = "Which vegtables do you want in your noodles?\n"
				+ "To choose multiple vegtables please press ctrl and click";
		JList<Object> list = new JList<Object>(new String[] {"Broccoli","Green beans","Cabbage", "Califlower","Carrot", "Mushrooms", "Sprouts"});
		Menu.ShowMessage(question2);
		VegtableType = Menu.MultiChoice(list);
		
		String question3 = "What kind of meat do you want in your noodles?";
		String answers3[]= {"Beef","Chicken", "Shrimp", "Tofu","Lamb"};
		MeatType = Menu.Menu1(question3, answers3);
		
	
		String question4 = "What sauce do you want?\nFor every sauce except the first one is an additional 2 shekels"
				+ "\nTo choose multiple sauces please press ctrl and click";
		JList<Object> list2 = new JList<Object>(new String[] {"Soy", "Teriyaki","Sweet and Sour", "Spicy Mayo"});
		Menu.ShowMessage(question4);
		SauceType = Menu.MultiChoice(list2);
		
		if (SauceType.size() > 1 ) {
			this.Price += (SauceType.size()-1 )*2 ;
		} 
				
	}

	@Override
	public String PrintOrder() {
		return "Noodles - " +
				super.toString() + ", " +
				"with : " +
				NoodleType + ", and vegtables " +
				VegtableType + ", and " +
				MeatType + ", and sauce " +
				SauceType;
		
	}
	
	@Override
	public String toString() {
		return PrintOrder();
		
	}

    public void DulpicateOrder() {
	    Menu.customer_curr.AddOrder(this);
	
    }

}

