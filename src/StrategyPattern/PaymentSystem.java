package StrategyPattern;
// -- strategy interface for payment--
interface PaymentSystemMain{
    void pay();
}

// -- concrete class for interface paymentSystemMain
class UPI implements PaymentSystemMain{
    public void pay(){
        System.out.println("paying by upi");
    }
}
class Debit implements PaymentSystemMain {
    public void pay() {
        System.out.println("paying by debit");
    }
}

class NetB implements PaymentSystemMain{
    public void pay(){
        System.out.println("paying by netb");
    }
}
public class PaymentSystem {
    public static void main(String[] args){
        PaymentSystemMain obj=new NetB();
        obj.pay();
        obj=new Debit();
        obj.pay();
    }
}
