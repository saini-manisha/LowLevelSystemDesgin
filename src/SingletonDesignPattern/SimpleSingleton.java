package SingletonDesignPattern;

public class SimpleSingleton {

    private static SimpleSingleton instance=null;
    private SimpleSingleton(){// making constructor private to restrict from making objects
        System.out.println("Singleton constructor called");

    }

    public static SimpleSingleton getInstance(){
        if(instance==null){
            instance=new SimpleSingleton();
        }
        return instance;
    }

    public static void main(String[] args){
        SimpleSingleton s1=SimpleSingleton.getInstance();
        SimpleSingleton s2=SimpleSingleton.getInstance();
        System.out.println(s1==s2);
    }
}
