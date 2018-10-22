package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Pi extends Command {
    public Pi(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return Math.PI;
    }
}

