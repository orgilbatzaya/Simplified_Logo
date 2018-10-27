package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Clear extends Action {

    public Clear(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {

        turtleDisplay.clearScreen();

    }
}
