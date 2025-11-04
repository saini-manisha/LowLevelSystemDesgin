package NotificationSystem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// Notification and Decorators

interface INotification{
    String getContent();
}

// concrete notification: simple text notification

class SimpleNotification implements INotification{
    private String text;

    public SimpleNotification(String msg){
        this.text=msg;
    }

    public String getContent(){
        return text;
    }
}

// Abstract Decorator: wraps a notification object. is a relationship and has a relationship
abstract class INotificationDecorator implements INotification{
    protected INotification notification;
    public INotificationDecorator(INotification n){
        this.notification=n;
    }
}

//Decorator to add a timestamp to the content.

class TimestampDecorator extends INotificationDecorator{
    public TimestampDecorator(INotification n){
        super(n);
    }

    public String getContent(){
        return LocalDate.now()+notification.getContent();
    }
}

// Decorator to append a signature to the content.

class SignatureDecorator extends INotificationDecorator{
    private String signature;

    public SignatureDecorator(INotification n, String sig){
        super(n);
        this.signature=sig;
    }
    public String getContent(){
        return notification.getContent()+"\n-- "+signature+"\n\n";
    }
}


/*========================
   Observer Pattern Components
 */

interface IObserver{
    void update();
}

interface IObservable{
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservsers();
}

//concrete observable

class NotificationObservable implements IObservable{
    private List<IObserver> observers=new ArrayList<>();
    private INotification currentNotification;

    public void addObserver(IObserver obs){
        observers.add(obs);
    }
    public void removeObserver(IObserver obs){
        observers.remove(obs);
    }




    public void notifyObservsers(){
        for(IObserver observer:observers){
            observer.update();
        }
    }

    public void setNotification(INotification notification){
        this.currentNotification=notification;
        notifyObservsers();
    }

    public INotification getNotification(){
        return currentNotification;
    }

    public String getNotificationContent(){
        return currentNotification.getContent();
    }


}

// concrete observer 1

class Logger implements  IObserver{
    private NotificationObservable notificationObservable;

    public Logger(NotificationObservable observable){
        this.notificationObservable=observable;
    }

    public void update(){
        System.out.println("Logging new Notificaiton: \n"+ notificationObservable.getNotificationContent());
    }
}


/*=========================

  Strategy Pattern components (Concrete observer 2)
* ========================*/

interface INotificationStrategy{
    void sendNotification(String content);
}

class EmailStrategy implements INotificationStrategy{
    private String emailId;

    public EmailStrategy(String emailId){
        this.emailId=emailId;
    }
    public void sendNotification(String content){
        System.out.println("Sending email notification to: "+emailId+"\n"+content);
    }
}

class SMSStrategy implements INotificationStrategy{
    private String mobileNumber;

    public SMSStrategy(String mobileNumber){
        this.mobileNumber=mobileNumber;
    }

    public void sendNotification(String content){
        System.out.println("sending sms notification to: "+mobileNumber+"\n"+content);
    }
}

class PopUpStrategy implements INotificationStrategy{
    public void sendNotification(String content){
        System.out.println("sending popup notification: \n"+content);
    }
}

class NotificationEngine implements IObserver{
    private NotificationObservable notificationObservable;
    private List<INotificationStrategy> notificationStrategies=new ArrayList<>();

    public NotificationEngine(NotificationObservable observable){
        this.notificationObservable=observable;
    }

    public void addNotificationStrategy(INotificationStrategy ns){
        this.notificationStrategies.add(ns);
    }

    public void update(){
        String notificationContent =notificationObservable.getNotificationContent();
        for(INotificationStrategy strategy : notificationStrategies){
            strategy.sendNotification(notificationContent);
        }
    }
}

/*======================

     NotificationService
======================== */

// The NotificaitonService  manages notificaitons. it keeps track of notificaiton
// Any client code will interact with this service.

// Singleton class

class NotificationService{
    private NotificationObservable observable;
    private List<INotification> notifications=new ArrayList<>();
    private static NotificationService instance;

    private NotificationService(){
        observable =new NotificationObservable();
    }

    public static NotificationService getInstance(){
        if(instance==null){
            instance=new NotificationService();
        }
        return instance;
    }

    public NotificationObservable getObservable(){
        return observable;
    }

    public void sendNotificaiton(INotification notification){
        notifications.add(notification);
        observable.setNotification(notification);
    }
}




public class Main {
    public static void main(String[] args){
        // create notificatioon service;
        NotificationService notificationService=NotificationService.getInstance();

        // get observable

        NotificationObservable notificationObservable= notificationService.getObservable();

        // create logger observer

        Logger logger=new Logger(notificationObservable);
        // create notification observers.

        NotificationEngine notificationEngine= new NotificationEngine(notificationObservable);

        notificationEngine.addNotificationStrategy(new EmailStrategy("randome.person@gmail.com"));
        notificationEngine.addNotificationStrategy(new SMSStrategy("98567845678"));
        notificationEngine.addNotificationStrategy(new PopUpStrategy());

        // attach these observers.

        notificationObservable.addObserver(logger);
        notificationObservable.addObserver(notificationEngine);

        // create a notification with decorators.

        INotification notification=new SimpleNotification("Your order has been shiped");
        notification=new TimestampDecorator(notification);
        notification=new SignatureDecorator(notification,"customer care");

        notificationService.sendNotificaiton(notification);
    }
}
