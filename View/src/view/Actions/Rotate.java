package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Rotate extends Action{
    private static final int FIRST_INDEX = 0;

    public Rotate(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.getMyTurtle().rotate(turtleDisplay.getMyTurtle().getView().getRotate() + getArgsDouble(FIRST_INDEX));

    }
}
