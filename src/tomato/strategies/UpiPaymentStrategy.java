package tomato.strategies;

public class UpiPaymentStrategy implements PaymentStrategy {
    private String mobile;

    public UpiPaymentStrategy(String mob){
        this.mobile=mob;
    }

    public void pay(double amount){
        System.out.println("Paid $"+amount+" using upi ("+mobile+")");

    }
}
