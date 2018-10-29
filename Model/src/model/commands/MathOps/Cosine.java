package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Cosine extends Command {
    public Cosine(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double inputVal = getArgsDouble(0);
        return Math.cos(Math.toRadians(inputVal));
    }
}

