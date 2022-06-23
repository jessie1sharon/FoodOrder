package Ordering;
//Group number 3
//Margarita kaplan 321775579
//Jessica Llanos 327083184
//Yarden Phahima 318647211
//Shahar Ganani 208983247


public abstract class Food {

	protected int Price;
	private String Size;
	
	public Food (int price, String size){
		
		this.Price = price;
		this.Size = size;
	}
	
	public final int getPrice() {
		
		return Price;

	}
	
    public final String getSize() {
		
		return Size;

	}
    
    @Override
    public String toString() {
    	return "" + Price + "₪, size " + Size;
    }

}
