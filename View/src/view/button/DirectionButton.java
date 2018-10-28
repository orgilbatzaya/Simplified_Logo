package view.button;

import view.Console;

public class DirectionButton extends SLogoButton{
    private String direction;
    private Console console;
    private int distance = 50;

    public DirectionButton(String Label, String direction, Console console){
        super(Label);
        this.direction = direction;
        this.console = console;


    }

    public void processCommand(){

    }
}
