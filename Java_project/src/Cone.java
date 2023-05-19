public class Cone extends Icecream {
	//argument constructor
	public Cone(String object, String name, double price, double calorie, int amount) {
		super(object, name, price, calorie, amount);
	}//argument constructor
	

	@Override
	public void calculatePayment() { //calculate the payment and get the totalprice
		totalPrice = price*amount;
	}
		
}
