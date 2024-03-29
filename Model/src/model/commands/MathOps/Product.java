package model.commands.MathOps;

import model.Command;

import java.util.List;
import java.util.Map;

public class Product extends Command {
    public Product(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double firstVal = getArgsDouble(0);
        double secondVal = getArgsDouble(1);
        return firstVal*secondVal;
    }
}
