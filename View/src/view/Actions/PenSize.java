package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class PenSize extends Action {

    public PenSize(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setPenWidthAction(getArgsDouble(0));
    }
}

