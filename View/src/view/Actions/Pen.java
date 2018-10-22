package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Pen extends Action {

    public Pen(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        if(getArgsDouble(0) == 0.0){
            turtleDisplay.hidePen();

        }
        else if(getArgsDouble(0) == 1.0){
            turtleDisplay.showPen();
        }
    }
}
