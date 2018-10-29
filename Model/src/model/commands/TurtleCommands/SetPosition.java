package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class SetPosition extends TurtleCommand {
    public SetPosition(List<String> args){
        super(args);
    }
    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double xPos = getArgsDouble(0);
        double yPos = getArgsDouble(1);
        return position(xPos,yPos,turtleAction,turtleActionArgs,turtleParams);
    }
}
