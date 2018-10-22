package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class SetTowards extends TurtleCommand {
    public SetTowards(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double xVec = getArgsDouble(0);
        double yVec = getArgsDouble(1);
        double newHeading = Math.tan(Math.toRadians(yVec/xVec));
        return heading(newHeading,turtleAction,turtleActionArgs,turtleParams);

    }
}
