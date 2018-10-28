package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Heading extends Action {
    private static final int FIRST_INDEX = 0;

    public Heading(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay display)
    {
        display.getMyTurtle().rotate(getArgsDouble(FIRST_INDEX));
    }
}
