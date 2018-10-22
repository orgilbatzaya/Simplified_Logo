package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class Right extends TurtleCommand {
    public Right(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double valueRotate = getArgsDouble(0);
        rotate(-valueRotate,turtleAction,turtleActionArgs,turtleParams);
        return 0;
    }
}
