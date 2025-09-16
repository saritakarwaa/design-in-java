//Sometimes your application needs to support multiple algorithms or behaviors that can vary at runtime. 
//Here is how most of does that, a giant if else block
// This violates the open/close principle.Because our code isn’t open for extension. Its open for modification.
// The strategy pattern solves this by encapsulating each algorithm in its own class and making them interchangeable.

interface PaymentStrategy{
    void pay(int amount);
}
class CreditCardPayment implements PaymentStrategy{
    public void pay(int amount){
        System.out.println("Paid "+amount+" using credit card");
    }
}
class PayPalPayment implements PaymentStrategy{
    public void pay(int amount){
        System.out.println("Paid "+amount+" using PayPal");
    }
}
class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void makePayment(int amount) {
        strategy.pay(amount);
    }
}
public class StrategyMain {
    public static void main(String[] args){
        PaymentContext context;
        context=new PaymentContext(new CreditCardPayment());
        context.makePayment(100);
        context=new PaymentContext(new PayPalPayment());
        context.makePayment(250);
    }
}
// Think of the strategy pattern like plugging different sim cards into the same phone. 
// The phone is your context. Each sim is a diff strategy.
