package model.commands.TurtleCommands;

import model.commands.TurtleCommand;


import java.util.List;
import java.util.Map;

public class Backward extends TurtleCommand {
    public static final String MOVE_ACTION = "move";

    public Backward(List<String> args){
        super(args);
    }

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double valueForward = getArgsDouble(0);
        var actionMap = move(-valueForward,turtleAction,  turtleActionArgs, turtleParams);

        List<String> actionNames = (List<String>)(actionMap.keySet().toArray()[0]);
        List<Double> actionArgs = (List<Double>)(actionMap.values().toArray()[0]);
        setMyTurtleActions(actionNames);
        setMyTurtleArgs(actionArgs);
        return valueForward;
    }
}
