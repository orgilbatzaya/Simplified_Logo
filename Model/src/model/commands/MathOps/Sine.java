package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Sine extends Command {
    public Sine(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double inputVal = getArgsDouble(0);
        return Math.sin(Math.toRadians(inputVal));
    }
}

