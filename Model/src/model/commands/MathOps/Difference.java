package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Difference extends Command {
    public Difference(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double firstVal = getArgsDouble(0);
        double secondVal = getArgsDouble(1);
        return firstVal-secondVal;
    }
}
