package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class ClearScreen extends TurtleCommand {
    public ClearScreen(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        return clear(turtleAction, turtleActionArgs,turtleParams);

    }
}
