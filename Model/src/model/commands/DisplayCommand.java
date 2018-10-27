package model.commands;

import model.Command;

import java.util.List;
import java.util.Map;

public abstract class DisplayCommand extends Command {
    public static final String SET_PEN_COLOR = "PenColor";
    public static final String SET_PEN_SIZE = "PenSize";
    public static final String SET_BACKGROUND = "Background";
    public static final String SET_PALETTE = "Palette";
    public static final String SET_SHAPE = "Shape";

    public static final String PEN_COLOR_KEY = "penIndex";
    public static final String SHAPE_INDEX_KEY = "shapeIndex";
    public static final String BACKGROUND_INDEX_KEY = "backgroundIndex";
    public static final String PEN_SIZE_KEY = "penSize";
    public static final String PALETTE_KEY = "palette";

    public DisplayCommand(List<String> args){super(args);}

    public void setDoubleForKey(double value, String actionName,String key, List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams){
        turtleAction.add(actionName);
        turtleActionArgs.add(value);
        turtleParams.put(key,value);
    }


    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams);
}
