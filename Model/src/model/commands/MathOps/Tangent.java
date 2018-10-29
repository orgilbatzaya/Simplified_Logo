package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Tangent extends Command {
    public Tangent(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double inputVal = getArgsDouble(0);
        return Math.tan(Math.toRadians(inputVal));
    }
}
