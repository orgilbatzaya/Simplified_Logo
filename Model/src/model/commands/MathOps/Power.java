package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Power extends Command {
    public Power(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double base = getArgsDouble(0);
        double exp = getArgsDouble(1);
        return Math.pow(base,exp);
    }
}

