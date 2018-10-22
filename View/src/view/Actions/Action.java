package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public abstract class Action {
    private String myCommand;
    private List<Double> myArgs;

    public Action(List<Double> args) {
        myArgs = args;
    }


    public abstract void execute(TurtleDisplay turtleDisplay);

    public Double getArgsDouble(int index){
        return myArgs.get(index);
    }
}


