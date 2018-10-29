package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class SetPenSize extends DisplayCommand {
    public SetPenSize(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        double value = getArgsDouble(0);
        setDoubleForKey(value,SET_PEN_SIZE, turtleAction,turtleActionArgs,turtleParams);
        return value;
    }
}
