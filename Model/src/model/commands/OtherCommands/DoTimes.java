package model.commands.OtherCommands;

import model.Command;

import java.util.List;
import java.util.Map;

public class DoTimes extends Command {

    private double originalIterations;
    private double iterations;

    public DoTimes(List<String> args) {
        super(args);
        originalIterations = getArgsDouble(0);
        iterations = originalIterations;
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
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
