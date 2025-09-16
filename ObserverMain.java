// lets say you have one object maybe a data source or system component and multiple other parts of your app need to react when that object changes.
// The naïve way you call each of them directly one by one but that quickly becomes tightly coupled hard to manage and impossible to scale. 
//The observer pattern solves this by letting one object called the subject notify many dependent objects also called observers whenever it changes. And you don’t need to know how many observers there are or what they do. 
//You just notify and they react.
// This pattern is backbone of event-driver systems, ui frameworks, real time applications.

import java.util.ArrayList;
import java.util.List;

interface Observer{
    void update(String message);
}
class EmailSubscriber implements Observer{
    private String name;
    public EmailSubscriber(String name){
        this.name=name;
    }
    public void update(String message){
        System.out.println(name+" recieved update: "+message);
    }
}
interface Subject{
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(String message);
}
class NewsPublisher implements Subject{
    private List<Observer>observers=new ArrayList<>();
    public void attach(Observer o){
        observers.add(o);
    }
    public void detach(Observer o){
        observers.remove(o);
    }
    public void notifyObservers(String message){
        for(Observer o: observers){
            o.update(message);
        }
    }
}
public class ObserverMain {
    public static void main(String[] args) {
        NewsPublisher publisher=new NewsPublisher();
        Observer alice=new EmailSubscriber("Alice");
        Observer bob=new EmailSubscriber("Bob");
        publisher.attach(alice);
        publisher.attach(bob);
        publisher.notifyObservers("New design pattern just dropped");
    }
}
