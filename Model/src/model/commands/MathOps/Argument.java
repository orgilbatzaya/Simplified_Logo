package model.commands.MathOps;

import model.Command;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Argument extends Command {

    private double value;
    public Argument(Double val) {
        super(new LinkedList<>());
        value = val;
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {

        return value;
    }

    public void setValue(Double val) {
        value = val;
    }
}
