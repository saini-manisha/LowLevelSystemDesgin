package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface ISubscriber{
    void update();
}

// observable interface: a youtube channel interface

interface IChannel{
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers();
}

// concrete subjet: a youtube channel that observers can subscribe to

class Channel implements IChannel{
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name){
        this.name=name;
        this.subscribers=new ArrayList<>();

    }

    public void subscribe(ISubscriber subscriber){
        if(!subscribers.contains(subscriber)){
            subscribers.add(subscriber);
        }
    }

    public void unsubscribe(ISubscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(){
        for(ISubscriber sub: subscribers){
            sub.update();
        }
    }

    public void uploadVideo(String title){
        latestVideo=title;
        System.out.println("\n["+name+" uploaded \""+ title+"\"]");
        notifySubscribers();
    }

    public String getVideoData(){
        return "\n checkout our new video : "+ latestVideo+"\n";
    }
}

// concrete observer: represents a subscriber to the channel

class Subscriber implements  ISubscriber{
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel){
        this.name=name;
        this.channel=channel;
    }

    public void update(){
        System.out.println("Hey "+name+","+channel.getVideoData());
    }
}



public class ObserverPattern {
    public static void main(String args[]){

        // create a channel and subscribers

        Channel channel= new Channel("CoderArmy");

        Subscriber subs1=new Subscriber("varun",channel);
        Subscriber subs2=new Subscriber("tarun",channel);

        channel.subscribe(subs1);
        channel.subscribe(subs2);
        // upload a video: both varun and tarun are notified
        channel.uploadVideo("obserber pattern tutorial");

        // varun unsubscribes; tarun remain subscribed
        channel.unsubscribe(subs1);

        // upload another video: only tarun is notified

        channel.uploadVideo("decorator pattern tutorial");



    }
}
