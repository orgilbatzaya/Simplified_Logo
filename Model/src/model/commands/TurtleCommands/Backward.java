package model.commands.TurtleCommands;

import model.commands.TurtleCommand;


import java.util.List;
import java.util.Map;

public class Backward extends TurtleCommand {
    public static final String MOVE_ACTION = "move";

    public Backward(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double valueForward = getArgsDouble(0);
        move(-valueForward,turtleAction,  turtleActionArgs, turtleParams);
        return valueForward;
    }
}
