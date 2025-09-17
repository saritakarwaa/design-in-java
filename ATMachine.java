class Account{
    String acc_number;
    int balance;
    Account(String acc_number,int balance){
        this.acc_number=acc_number;
        this.balance=balance;
    }
    int getBalance(){
        return this.balance;
    }
    void deposit(double amount){
        balance+=amount;
    }
    boolean withdraw(double amount){
        if(amount<=balance){
            balance-=amount;
            return true;
        }
        return false;
    }
}
class Card{
    String card_number;
    String pin;
    Account account;
    Card(String card_number,String pin,Account account){
        this.card_number=card_number;
        this.pin=pin;
        this.account=account;
    }
    boolean validPin(String inputPin){
        return this.pin.equals(inputPin);
    }
    Account getAccount(){
        return account;
    }
}
class ATM{
    Card currentCard;
    boolean insertCard(Card card,String pin){
        if(card.validPin(pin)){
            currentCard=card;
            System.out.println("Card validated successfully.");
            return true;
        }
        else{
            System.out.println("Invalid PIN");
            return false;
        }
    }
    void checkBalance(){
        System.out.println("Balance: " + currentCard.getAccount().getBalance());
    }
    void deposit(int amount){
        currentCard.getAccount().deposit(amount);
        System.out.println("Deposited: "+amount);
    }
    void withdraw(int amount){
        if(currentCard.getAccount().withdraw(amount)){
            System.out.println("Withdrawn: "+amount);
        }
        else System.out.println("Insufficient funds.");
    }
    void ejectCard(){
        System.out.println("Card ejected.");
        currentCard = null;
    }
}
public class ATMachine {
    public static void main(String[] args) {
        Account acc=new Account("12345",1000);
        Card card=new Card("1111-2222-3333","1234",acc);
        ATM atm=new ATM();
        if(atm.insertCard(card,"1234")){
            atm.checkBalance();
            atm.withdraw(200);
            atm.checkBalance();
            atm.deposit(500);
            atm.checkBalance();
            atm.ejectCard();
        }
    }
}
