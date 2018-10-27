package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class SetPalette extends DisplayCommand {
    public SetPalette(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams) {
        double r = getArgsDouble(0);
        double g = getArgsDouble(0);
        double b = getArgsDouble(0);
        double newVal = r+1000*g+1000000*b; //"encode" the information every 3
        setDoubleForKey(newVal,SET_PALETTE,PALETTE_KEY,turtleAction,turtleActionArgs,turtleParams);
        return newVal;
    }
}
