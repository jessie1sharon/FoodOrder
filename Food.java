package Ordering;

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
