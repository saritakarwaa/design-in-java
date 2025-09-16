interface Product{
    void doWork();
}
class ConcreteProduct implements Product{
    public void doWork(){
        System.out.println("Working with ConcreteProduct");
    }
}
abstract class Creator{
    public abstract Product factoryMethod();
    public void someOperation(){
        Product product=factoryMethod();
        product.doWork();
    }
}
class ConcreteCreator extends Creator{
    public Product factoryMethod(){
        return new ConcreteProduct();
    }
}
public class FactoryMain {
    public static void main(String[] args) {
        Creator creator=new ConcreteCreator();
        creator.someOperation();
    }
}
//instead of using new() directly in your logic you call factoryMethod()

// You need to create objects but you don’t want to hardcode which class to instantiate. 
// Because maybe the type depends on user input, configuration or business rules.
//  The factory methods solves this by pushing object creation into subclasses.
//  So your main code doesn’t need to know which class to instantiate.