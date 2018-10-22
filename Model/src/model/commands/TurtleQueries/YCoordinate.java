package model.commands.TurtleQueries;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class YCoordinate extends TurtleCommand {
    public YCoordinate(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return turtleParams.get(Y_KEY);

    }
}
