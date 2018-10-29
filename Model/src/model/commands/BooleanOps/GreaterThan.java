package model.commands.BooleanOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class GreaterThan extends Command {
    public GreaterThan(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        return (getArgsDouble(0).compareTo(getArgsDouble(1)))>0 ? 1:0;
    }
}
