package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;
import java.util.Map;

public class Equal extends Command {
    public Equal(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return (getArgsDouble(0).equals(getArgsDouble(1))) ? 1:0;
    }
}
