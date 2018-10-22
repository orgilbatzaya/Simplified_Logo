package model.commands.OtherCommands;

import model.Command;

import java.util.List;
import java.util.Map;

public class For extends Command {

    private double originalIterations;
    private double iterations;

    public For(List<String> args) {
        super(args);
        originalIterations = (getArgsDouble(0) - getArgsDouble(1)) / getArgsDouble(2);
        iterations = originalIterations;
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return 0;
    }

    public void setValue(double val) {
        iterations = val;
    }

    public double getOriginalValue() {
        return originalIterations;
    }

    public double execute() {
        iterations--;
        return iterations;
    }
}
