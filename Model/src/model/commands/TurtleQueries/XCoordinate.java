package model.commands.TurtleQueries;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class XCoordinate extends TurtleCommand {
    public XCoordinate(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        return turtleParams.get(X_KEY);

    }
}
