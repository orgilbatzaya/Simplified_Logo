package model.commands.TurtleQueries;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class Heading extends TurtleCommand {
    public Heading(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        return turtleParams.get(0).get(HEADING_KEY);

    }
}
