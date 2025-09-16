public class Singleton {
    private static volatile Singleton instance=null;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance==null){
            synchronized (Singleton.class){
                if(instance==null) instance=new Singleton();
            }
        }
        return instance;
    }
}
//a class has only one instance and it provides a global access point to that instance. 
//Useful when you want to manage shared resources.
//Singleton should be used for logging,configuration,environment setup and thread pools.