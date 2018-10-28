package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Position extends Action {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;

    public Position(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay) {
        turtleDisplay.setToNewPosition(getArgsDouble(FIRST_INDEX), -getArgsDouble(SECOND_INDEX));
    }
}
