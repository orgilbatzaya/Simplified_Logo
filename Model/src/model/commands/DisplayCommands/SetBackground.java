package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class SetBackground extends DisplayCommand {
    public SetBackground(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double newIndex = getArgsDouble(0);
        System.out.println("HIIIIII");
        setDoubleForKey(newIndex,SET_BACKGROUND,BACKGROUND_INDEX_KEY,turtleAction,turtleActionArgs,turtleParams);
        return newIndex;
    }
}
