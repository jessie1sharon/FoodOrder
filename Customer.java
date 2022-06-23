package Ordering;
//Group number 3
//Margarita kaplan 321775579
//Jessica Llanos 327083184
//Yarden Phahima 318647211
//Shahar Ganani 208983247

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Customer {
	
    private String name;
    private String address;
    protected List<Orderable> orders = new ArrayList<>();
    private List<Date> dates = new ArrayList<>();

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }
    
    public String getAddress() {
        return this.address;
    }

    public void setNameAddress(String name1, String address1) {    // setting the name & address
        this.name = name1;
        this.address = address1;
    }
    
    public List<Orderable> getOrders() { return this.orders; }

    public List<Date> getDates() { return this.dates; }
    
    public void PrintOrders() {                               // printing the order 
        PrintOrders(this.orders, this.dates);
    }

    public void PrintOrders(List<Orderable> orders, List<Date> dates) {                // printing the order
        StringBuilder ordersString = new StringBuilder();
        int length = orders.size();
        for (int i = 0; i < length; i++) {
            ordersString.append(dates.get(i)).append(": ").append(orders.get(i).PrintOrder());
         
            if (i < length - 1) {
                ordersString.append("\n");
            }
        }
        String massage = "These are your orders:\n" + ordersString;      // printing the orders
        Menu.ShowMessage(massage);
    }

    public void AddOrder(Orderable order) {      // adding the order to the list of orders & get the time
    	 AddOrder(order,Calendar.getInstance().getTime());
    }
    
    public void AddOrder(Orderable order, Date date) {      // adding the order to the list of orders & get the time
        orders.add(order);
        dates.add(date);
    }

    
    public int NumOfOrders() {               // getting the num of orders
        return this.orders.size();
    }
    
    public String toString() {
		return name + "\nWith the address : " + address;

	}
    
    public void SearchOrder() {
        DateFormat dateFormat = new SimpleDateFormat("d/M/y");
        Date searchDateLower = null;
        Calendar calendar = Calendar.getInstance();

        do {
            String dateStr = Menu.AskMessage("The order search is done by date. \nPlease type a date in this format \"D/M/Y\" (e.g. 20/3/2020):");
            if (dateStr == null) return;

            try {
                searchDateLower = dateFormat.parse(dateStr); //getting the date from the user
            } catch (ParseException e) {}
        } while (searchDateLower == null);

        // Set the upper time to be 00:00:00.000 of the next day
        calendar.setTime(searchDateLower);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date searchDateUpper = calendar.getTime();

        // Set the lower time to be 23:59:59.999 of the search day
        calendar.setTime(searchDateLower);
        calendar.add(Calendar.MILLISECOND, -1);
        searchDateLower = calendar.getTime();

        ArrayList<Integer> foundIndexes = new ArrayList<>(); //here we save the index of the order we found

        for (int i = 0; i < orders.size(); i++) { 
            Date date = dates.get(i);
            if (searchDateLower.before(date) && searchDateUpper.after(date)) {
                foundIndexes.add(i);
            }
        }

        if (foundIndexes.size() == 0) {
            Menu.ShowMessage("No orders were found on that date");
            return;
        }

        List<Orderable> foundOrders = new ArrayList<>();
        List<Date> foundDates = new ArrayList<>();

        for (Integer index : foundIndexes) { //adding the orders with the index i 
            int i = index.intValue();
            foundOrders.add(orders.get(i));  //adding to arraylist of orders
            foundDates.add(dates.get(i));    //adding to arraylist of dates
        }

        PrintOrders(foundOrders, foundDates); //print the date and order we found 
 }
    
    
 // Called from customer_first (finished orders) to cancel orders that didn't pass the 10 since they were ordered.
    public void CancelOrder() {
        ArrayList<Integer> cancelable = new ArrayList<>();

        // 0. Pizza 2020
        // 1. Hamburger 2020
        // 2. Noodles 2020
        // 3. *Ice-Cream 2021
        // 4. *Pizza 2021
        // cancelable = [ 3, 4 ]

        for (int i = 0; i < dates.size(); i++) {
            Date date = dates.get(i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);           //saving the curr date 
            calendar.add(Calendar.MINUTE, 10); //saving the curr date +10 min 
            Date datePlus10 = calendar.getTime();
            // 12:15 -->(plus 10 minutes)--> 12:25 compare to current time 12:20 (if after, can be canceled)
            if (datePlus10.after(Calendar.getInstance().getTime())) { //if 10 min past -cant delete it 
                cancelable.add(i);
            }
        }

        if (cancelable.size() == 0) {
            Menu.ShowMessage("There are no orders that can be canceled");
            return;
        }

        String question = "Which order do you want to cancel?";
        String[] options = new String[cancelable.size()];

        for (int i = 0; i < cancelable.size(); i++) {
            options[i] = dates.get(cancelable.get(i)) + ": " + orders.get(cancelable.get(i));
        }

        String answer = Menu.Menu1(question, options); //checking that we didn't get null
        if (answer == null) {
            return;
        }
        
      //deleting the orders by the index i we get in the previous for loop

        for (int i = 0; i < cancelable.size(); i++) {  
            if (options[i].equals(answer)) {
                dates.remove(cancelable.get(i).intValue());
                orders.remove(cancelable.get(i).intValue());
                break;
            }
        }

        Menu.ShowMessage("Your order has been canceled");
    }
    
    public void sortOrdersByPrice(List<Orderable> sortedOrders, List<Date> sortedDates) {
        class FoodDate {      //we unite the orders with the dates so we can sort them together
            public Food food;  // so we create a new class that contains both of them 
            public Date date;
            public FoodDate(Food food, Date date) {
                this.food = food;
                this.date = date;
            }
        }
      //running on all the class of the unite orders and dates
        List<FoodDate> foodDates = new ArrayList<>(); 
        for (int i = 0; i < orders.size(); i++) {
            foodDates.add(new FoodDate((Food)orders.get(i), dates.get(i)));
        }
        foodDates.sort(new Comparator<FoodDate>() { //sorting 
            @Override
            public int compare(FoodDate o1, FoodDate o2) {
                return -(o1.food.getPrice() - o2.food.getPrice()); //from high to low 
            }
        });

        sortedOrders.clear();
        sortedDates.clear();
        for (FoodDate foodDate : foodDates) {
            sortedOrders.add((Orderable)foodDate.food); //adding the sorted to the new list 
            sortedDates.add(foodDate.date);
        }
    }
}
