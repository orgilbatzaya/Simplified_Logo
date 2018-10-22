package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class ArcTangent extends Command {
    public ArcTangent(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double inputVal = getArgsDouble(0);
        return Math.atan(Math.toRadians(inputVal));
    }
}
