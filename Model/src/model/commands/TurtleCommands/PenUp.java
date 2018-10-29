package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class PenUp extends TurtleCommand{
    public PenUp(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        pen(ZERO_DOUBLE,turtleAction,turtleActionArgs,turtleParams);

        return 0;
    }
}
