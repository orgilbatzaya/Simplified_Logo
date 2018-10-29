package model.commands.TurtleQueries;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class IsPenDown extends TurtleCommand {
    public IsPenDown(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        return turtleParams.get(0).get(PEN_KEY);

    }
}
