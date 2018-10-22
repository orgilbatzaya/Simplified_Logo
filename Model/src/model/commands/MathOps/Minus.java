package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;
import java.util.Map;

public class Minus extends Command {
    public Minus(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double firstVal = getArgsDouble(0);
        return firstVal*(-1);
    }
}