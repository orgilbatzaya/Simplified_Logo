package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class SetPenColor extends DisplayCommand {
    public SetPenColor(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double newIndex = getArgsDouble(0);
        setDoubleForKey(newIndex,SET_PEN_COLOR, turtleAction,turtleActionArgs,turtleParams);
        return newIndex;
    }
}
