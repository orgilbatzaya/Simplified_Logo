package model.commands.BooleanOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;
import java.util.Map;

public class And extends Command {
    public And(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return ((getArgsDouble(0) != 0) && (getArgsDouble(1)!=0)) ? 1:0;
    }
}
