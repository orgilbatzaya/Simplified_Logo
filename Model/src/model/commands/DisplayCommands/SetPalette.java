package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class SetPalette extends DisplayCommand {
    public SetPalette(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double index = getArgsDouble(0);
        double r = getArgsDouble(1);
        double g = getArgsDouble(2);
        double b = getArgsDouble(3);
        double newVal = index+1000*r+1000000*g+1000000000*b; //"encode" the information every 3
        setDoubleForKey(newVal,SET_PALETTE, turtleAction,turtleActionArgs,turtleParams);
        return newVal;
    }
}
