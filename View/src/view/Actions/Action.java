package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public abstract class Action {
    private List<Double> myArgs;

    public Action(List<Double> args) {
        myArgs = args;
    }


    public abstract void execute(TurtleView turtle, TurtleDisplay turtleDisplay);


    public double getArgsDouble(int index){
        return myArgs.get(index);
    }
}


