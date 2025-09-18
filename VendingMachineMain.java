interface State {
    void insertMoney();
    void selectItem();
    void dispense();
}

abstract class AbstractState implements State {
    protected VendingMachine vendingMachine;

    public AbstractState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
}


class IdleState extends AbstractState {
    public IdleState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney() {
        System.out.println("Money inserted.");
        vendingMachine.setState(vendingMachine.getHasMoneyState());
    }

    @Override
    public void selectItem() {
        System.out.println("Insert money first.");
    }

    @Override
    public void dispense() {
        System.out.println("Insert money and select item first.");
    }
}

class HasMoneyState extends AbstractState {
    public HasMoneyState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted.");
    }

    @Override
    public void selectItem() {
        if (vendingMachine.getItemCount() > 0) {
            System.out.println("Item selected.");
            vendingMachine.setState(vendingMachine.getDispenseState());
        } else {
            System.out.println("Item out of stock.");
            vendingMachine.setState(vendingMachine.getSoldOutState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("Select item before dispensing.");
    }
}

class DispenseState extends AbstractState {
    public DispenseState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney() {
        System.out.println("Please wait, dispensing in progress.");
    }

    @Override
    public void selectItem() {
        System.out.println("Already selected, dispensing now.");
    }

    @Override
    public void dispense() {
        System.out.println("Dispensing item...");
        vendingMachine.releaseItem();

        if (vendingMachine.getItemCount() > 0) {
            vendingMachine.setState(vendingMachine.getIdleState());
        } else {
            System.out.println("Machine is now empty.");
            vendingMachine.setState(vendingMachine.getSoldOutState());
        }
    }
}

class SoldOutState extends AbstractState {
    public SoldOutState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    @Override
    public void insertMoney() {
        System.out.println("Machine sold out. Cannot accept money.");
    }

    @Override
    public void selectItem() {
        System.out.println("Machine sold out.");
    }

    @Override
    public void dispense() {
        System.out.println("Machine sold out.");
    }
}

class VendingMachine {
    private final State idleState;
    private final State hasMoneyState;
    private final State dispenseState;
    private final State soldOutState;

    private State currentState;
    private int itemCount;

    public VendingMachine(int itemCount) {
        idleState = new IdleState(this);
        hasMoneyState = new HasMoneyState(this);
        dispenseState = new DispenseState(this);
        soldOutState = new SoldOutState(this);

        this.itemCount = itemCount;
        currentState = itemCount > 0 ? idleState : soldOutState;
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void selectItem() {
        currentState.selectItem();
    }

    public void dispense() {
        currentState.dispense();
    }

    public void releaseItem() {
        if (itemCount > 0) {
            itemCount--;
            System.out.println("One item released. Items left: " + itemCount);
        }
    }

    // Getters for states
    public State getIdleState() { return idleState; }
    public State getHasMoneyState() { return hasMoneyState; }
    public State getDispenseState() { return dispenseState; }
    public State getSoldOutState() { return soldOutState; }

    public void setState(State state) { currentState = state; }
    public int getItemCount() { return itemCount; }
}

public class VendingMachineMain {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine(2);

        vm.insertMoney();
        vm.selectItem();
        vm.dispense();

        System.out.println("---");

        vm.insertMoney();
        vm.selectItem();
        vm.dispense();

        System.out.println("---");

        vm.insertMoney(); // should say sold out
    }
}
