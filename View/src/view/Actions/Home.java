package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Home extends Action {

    private static final int ORIGIN = 0;
    public Home(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setToNewPosition(ORIGIN, ORIGIN);

    }
}
