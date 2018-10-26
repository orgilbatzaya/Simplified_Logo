package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Heading extends Action {

    public Heading(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay)
    {
        turtleDisplay.getMyTurtle().rotate(getArgsDouble(0));
        turtleDisplay.getReturnValue();
    }
}
