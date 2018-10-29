package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class SetHeading extends TurtleCommand {
    public SetHeading(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double newHeading = getArgsDouble(0);
        double angleChange = heading(newHeading,turtleAction,turtleActionArgs,turtleParams);
        return angleChange;
    }
}
