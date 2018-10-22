package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Position extends Action {

    public Position(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        TurtleView myTurt = turtleDisplay.getMyTurtle();
        myTurt.setXTurtle(getArgsDouble(0));
        myTurt.setYTurtle(getArgsDouble(1));
    }
}
