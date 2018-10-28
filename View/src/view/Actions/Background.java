package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Background extends Action {

    private static final int FIRST_INDEX = 0;

    public Background(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setBackgroundColorAction((int) getArgsDouble(FIRST_INDEX));
    }
}

