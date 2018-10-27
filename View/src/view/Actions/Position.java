package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Position extends Action {

    public Position(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setToNewPosition(getArgsDouble(0), -getArgsDouble(1));
    }
}
