package model.commands.TurtleCommands;

import model.commands.TurtleCommand;

import java.util.List;
import java.util.Map;

public class Forward extends TurtleCommand {
    public Forward(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double valueForward = getArgsDouble(0);

        move(valueForward,turtleAction, turtleActionArgs, turtleParams);


        return 0;
    }
}
