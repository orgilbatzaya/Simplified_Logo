package view.button;

import view.Console;
import view.ViewResourceBundles;

public class DirectionButton extends SLogoButton implements ViewResourceBundles {
    private String direction;
    private Console console;
    private static final int DISTANCE = 50;
    private static final String SET_H = "setheading ";
    private static final String FORWARD = "Forward ";


    public DirectionButton(String direction, Console console){
        super(direction);
        this.direction = direction;
        this.console = console;


    }

    public void processCommand(){
        console.runCommand(SET_H + myDirections.getString(direction));
        console.runCommand(FORWARD + Integer.toString(DISTANCE));

    }
}
