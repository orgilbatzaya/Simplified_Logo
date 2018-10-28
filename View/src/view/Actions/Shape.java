package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Shape extends Action {

    public Shape(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setShapeAction((int) getArgsDouble(0));
    }
}

