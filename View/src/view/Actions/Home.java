package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Home extends Action {

    public Home(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.setToNewPosition((0), (0));
    }
}
