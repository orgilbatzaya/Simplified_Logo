package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Background extends Action {

    private static final int FIRST_INDEX = 0;

    public Background(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay turtleDisplay) {
        turtleDisplay.setBgColor((int) getArgsDouble(0));
    }
}

