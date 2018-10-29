package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class SetBackground extends DisplayCommand {
    public SetBackground(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double newIndex = getArgsDouble(0);
        setDoubleForKey(newIndex,SET_BACKGROUND, turtleAction,turtleActionArgs,turtleParams);
        return newIndex;
    }
}
