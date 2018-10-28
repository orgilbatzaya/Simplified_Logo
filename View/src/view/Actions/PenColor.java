package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class PenColor extends Action {

    public PenColor(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setPenColorAction((int) getArgsDouble(0));
    }
}

