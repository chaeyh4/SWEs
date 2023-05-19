public abstract class Icecream implements Payment{
	//set the arguments
	public String object;
	public String name;
	public double price;
	public double totalPrice;
	public double calorie;
	public int amount;
	
	public Icecream(String object, String name, double price, double calorie, int amount) {
		setObject(object);
		setName(name);
		setPrice(price);
		setTotalPrice(totalPrice);
		setCalorie(calorie);
		setAmount(amount);
	}//end argument constructor
	
	//set object
	public void setObject(String object) {
		this.object = object;
	}//end method setObject
	
	//get model
	public String getObject() {
		return object;
	}//end method getModel
	
	
	//set name
	public void setName(String name) {
		this.name = name;
	}//end method setObject
	
	//get name
	public String getName() {
		return name;
	}//end method getName
	
	//set price
	public void setPrice(double price) {
		this.price = price;
	}//end method setPrice
	
	//get price
	public double getPrice() {
		return price;
	}//end method getPrice
	
	//set total price
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}//end method setTotalPrice
	
	//get total price
	public double getTotalPrice() {
		return totalPrice;
	}//end method getTotalPrice
	
	//set calorie
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}//end method setCalorie
	
	//get calorie
	public double getCalorie() {
		return calorie;
	}//end method getCalorie
	
	//set amount
	public void setAmount(int amount) {
		this.amount = amount;
	}//end method setAmount
	
	//get amount
	public double getAmount() {
		return amount;
	}//end method getAmount
	
	@Override
	public void calculatePayment() {
		
	}
	
}
