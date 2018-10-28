package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class PenSize extends Action {
    private static final int FIRST_INDEX = 0;


    public PenSize(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setPenWidthAction(getArgsDouble(FIRST_INDEX));
    }
}

