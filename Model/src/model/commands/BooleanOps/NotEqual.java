package model.commands.BooleanOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class NotEqual extends Command {
    public NotEqual(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return !(getArgsDouble(0).equals(getArgsDouble(1))) ? 1:0;
    }
}
