package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class ClearScreen extends TurtleCommand {
    public ClearScreen(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        clear(turtleAction, turtleActionArgs,turtleParams);
        double distanceMoved = turtleParams.get(DISTANCE_MOVED_KEY);
        turtleParams.put(DISTANCE_MOVED_KEY,ZERO_DOUBLE);
        return distanceMoved;
    }
}
