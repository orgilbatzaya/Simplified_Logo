package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Clear extends Action {

    public Clear(List<Double> args){
        super(args);
    }

    public void execute(TurtleView view,TurtleDisplay turtleDisplay) {

        turtleDisplay.clearScreen();

    }
}
