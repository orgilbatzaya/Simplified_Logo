package model.commands.DisplayCommands;

import model.commands.DisplayCommand;

import java.util.List;
import java.util.Map;

public class GetPenColor extends DisplayCommand {
    public GetPenColor(List<String> args){super(args);}

    @Override
    public double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams) {
        return turtleParams.get(0).get(PEN_COLOR_KEY);
    }
}
