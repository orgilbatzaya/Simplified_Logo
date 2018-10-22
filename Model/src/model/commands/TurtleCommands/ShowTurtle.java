package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class ShowTurtle extends TurtleCommand {
    public ShowTurtle(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        visible(ONE_DOUBLE,turtleAction,turtleActionArgs,turtleParams);
        return 0;
    }
}
