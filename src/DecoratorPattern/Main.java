package DecoratorPattern;
// component interface: defines a common interface for mario and all power-up decorators.

interface Character{
    String getAbilities();
}

//concrete component: Basic mario character with no power ups

class Mario implements Character{
    public String getAbilities(){
        return "Mario";
    }
}
// abstract decorator: characterDecorator "is a" character and "has-a" character.

abstract  class CharacterDecorator implements Character{
    protected Character character;

    public CharacterDecorator(Character c){
        this.character=c;
    }
}
// cincrete Decorator: Height-increasing power-up.

class HeightUp extends CharacterDecorator{
    public HeightUp(Character c){
        super(c);
    }

    public String getAbilities(){
        return character.getAbilities()+" with HeightUp";
    }
}

// concrete decorator: gun shooting power-up.

class GunPowerUp extends CharacterDecorator{
    public GunPowerUp(Character c){
        super(c);
    }

    public String getAbilities(){
        return character.getAbilities()+" with gun";
    }
}
// concrete decorator: star power up(temporary ability).

class StarPowerUp extends CharacterDecorator{
    public StarPowerUp(Character c){
        super(c);
    }

    public String getAbilities(){
        return character.getAbilities()+ "with star power(limited time";
    }
}
public class Main {
    public static void main(String[] args){
        // create a basic mario character.

        Character mario=new Mario();
        System.out.println("Basic character:"+mario.getAbilities());

        // decorate mario with a heightup power-up.
        mario=new HeightUp(mario);
        System.out.println("After heightup: "+mario.getAbilities());

        // decorate mario further with a gunpowerup

        mario=new GunPowerUp(mario);
        System.out.println("after gunpowerUp"+mario.getAbilities());

        // finally, add a starpowerup decoration

        mario=new StarPowerUp(mario);
        System.out.println("After startpowerup: "+mario.getAbilities());
    }
}
