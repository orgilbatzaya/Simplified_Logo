package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Random extends Command {
    public Random(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double maxVal = getArgsDouble(0);
        return Math.random()*maxVal;
    }
}

