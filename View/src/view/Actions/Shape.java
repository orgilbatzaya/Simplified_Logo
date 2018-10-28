package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Shape extends Action {
    private static final int FIRST_INDEX = 0;

    public Shape(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.changeTurtleImage((int) getArgsDouble(0));
    }
}

