package CommandPattern;

//----------------
// Command Interface
//----------------

interface Command{
    void execute();
    void undo();
}

// -----------------
// Receivers
//------------------

class Light{
    public void on(){
        System.out.println("Light is on");
    }

    public void off(){
        System.out.println("Light is off");
    }
}

class Fan{
    public void on(){
        System.out.println("Fan is on");
    }

    public void off(){
        System.out.println("fan is off");
    }
}

//----------------
// concrete command for light
//-----------------

class LightCommand implements Command{
    private Light light;

    public LightCommand(Light l){
        this.light=l;
    }
    public void execute(){
        light.on();
    }
    public void undo(){
        light.off();
    }
}

// ----------------
// Concret command for fan
//---------------

class FanCommand implements  Command{
    private Fan fan;

    public FanCommand(Fan f){
        this.fan=f;
    }
    public void execute(){
        fan.on();
    }
    public void undo(){
        fan.off();
    }
}
//----------------------
// Invoker: Remote Controller with static array of 4 buttons
//----------------------

class RemoteController{
    private static final int numButtons=4;
    private Command[] buttons;
    private boolean[] buttonPressed;

    public RemoteController(){
        buttons=new Command[numButtons];
        buttonPressed=new boolean[numButtons];

        for(int i=0;i<numButtons;i++){
            buttons[i]=null;
            buttonPressed[i]=false;
        }
    }

    public void setCommand(int idx,Command cmd){
        if(idx>=0 && idx<numButtons){
            buttons[idx]=cmd;
            buttonPressed[idx]=false;
        }
    }

    public void pressButton(int idx){
        if(idx>=0 && idx<numButtons && buttons[idx]!=null){
            if(!buttonPressed[idx]){
                buttons[idx].execute();
            }
            else{
                buttons[idx].undo();
            }

        }
        else{
            System.out.println("No command assigned at button "+idx);
        }
    }

}

public class CommandPattern {
    public static void main(String [] args){
        Light livingRommLight= new Light();
        Fan ceilingFan =new Fan();

        RemoteController remote=new RemoteController();

        remote.setCommand(0,new LightCommand(livingRommLight));
        remote.setCommand(1,new FanCommand(ceilingFan));

        // simulate button presses (toggle behavior)


        System.out.println("-------Toggling Light Button 0 ----");

        remote.pressButton(0);
        remote.pressButton(0);

        System.out.println("---- Toggling Fan Button 1 -------");
        remote.pressButton(1);
        remote.pressButton(1);

        // Press unassigned button to show default message

        System.out.println("-- Pressing Unassigned Button 2 ------");
        remote.pressButton(2);


    }
}
