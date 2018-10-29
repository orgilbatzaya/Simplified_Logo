package model.commands.MathOps;

import model.Command;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Variable extends Command {

    private String value;
    public Variable(String val) {
        super(new LinkedList<>());
        value = val;
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {

        return 1;
    }

    public void setValue(String val) {
        value = val;
    }

    public String getValue() { return value; }
}
