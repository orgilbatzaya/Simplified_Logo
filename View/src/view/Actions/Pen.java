package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Pen extends Action {
    private static final int FIRST_INDEX = 0;


    public Pen(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay) {
        turtleDisplay.updatePen(getArgsDouble(FIRST_INDEX));

    }
}
