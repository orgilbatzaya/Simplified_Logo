package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class PenDown extends TurtleCommand {
    public PenDown(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        pen(ONE_DOUBLE,turtleAction,turtleActionArgs,turtleParams);
        return 1;
    }
}
