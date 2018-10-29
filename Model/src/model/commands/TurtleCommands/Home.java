package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class Home extends TurtleCommand {
    public Home(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double distanceMoved = home(turtleAction,turtleActionArgs,turtleParams);
        return distanceMoved;
    }
}
