package SingletonDesignPattern;

class Singleton{

    Singleton(){
        System.out.println("singleton constructor called. new object created.");
    }
}
public class NoSingleton {
    public static void main(String []args){

        Singleton s1=new Singleton();
        Singleton s2= new Singleton();
        System.out.println(s1==s2);
    }
}
