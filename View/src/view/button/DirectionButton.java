package view.button;

import view.Console;

public class DirectionButton extends SLogoButton{
    private String direction;
    private Console console;
    private int distance = 50;

    public DirectionButton(String direction, Console console){
        super(direction);
        this.direction = direction;
        this.console = console;


    }

    public void processCommand(){
        if(direction.equals("Up")){
            console.runCommand("setheading 0");
            console.runCommand("Forward " + Integer.toString(distance));
        }
        if(direction.equals("Down")){
            console.runCommand("setheading 180");
            console.runCommand("Forward " + Integer.toString(distance));
        }
        if(direction.equals("Left")){
            console.runCommand("setheading 270");
            console.runCommand("Forward " + Integer.toString(distance));
        }
        if(direction.equals("Right")){
            console.runCommand("setheading 90");
            console.runCommand("Forward " + Integer.toString(distance));
        }
    }
}
