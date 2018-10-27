package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Background extends Action {

    public Background(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setBackgroundColorAction((int) getArgsDouble(0));
    }
}

