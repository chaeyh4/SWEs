
public class Freezie extends Icecream{
	//argument constructor
	public Freezie(String object, String name, double price, double calorie, int amount) {
		super(object, name, price, calorie, amount);
	}//argument constructor
	
	@Override
	public void calculatePayment() { //calculate the payment
		totalPrice = price*amount;
	}
}
