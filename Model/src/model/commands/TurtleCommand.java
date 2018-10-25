package model.commands;

import model.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TurtleCommand extends Command {
    //string names for the turtle actions
    public static final String MOVE_ACTION = "Move";
    public static final String CLEAR_ACTION = "Clear";
    public static final String SET_VISIBLE = "Visible"; //argument is 1 for visible, 0 for invisible
    public static final String HOME_ACTION = "Home";
    public static final String ROTATE_ACTION = "Rotate";
    public static final String SET_PEN = "Pen"; //argument is 1 for pen down, 0 for pen up
    public static final String SET_HEADING = "Heading";
    public static final String SET_POSITION = "Position";


    //key values from turtle parameters map
    public static final String HEADING_KEY = "heading";
    public static final String X_KEY = "xPos";
    public static final String Y_KEY = "yPos";
    public static final String DISTANCE_MOVED_KEY = "distanceMoved";
    public static final String PEN_KEY = "pen";
    public static final String VISIBLE_KEY= "visible";


    //convenient doubles
    public static final Double ZERO_DOUBLE = (double) 0;
    public static final Double ONE_DOUBLE = (double) 1;

    private List<String> myArgs;

    public TurtleCommand(List<String> args) {
        super(args);

    }

    public Map<List<String>,List<Double>> move(double distance,List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(MOVE_ACTION);
        turtleArgs.add(distance);
        double newX = turtleParams.get(X_KEY)+distance*Math.cos(Math.toRadians(turtleParams.get(HEADING_KEY)));
        double newY = turtleParams.get(Y_KEY)+distance*Math.sin(Math.toRadians(turtleParams.get(HEADING_KEY)));
        turtleParams.put(X_KEY,newX);
        turtleParams.put(Y_KEY,newY);
        turtleParams.put(DISTANCE_MOVED_KEY,turtleParams.get(DISTANCE_MOVED_KEY)+distance);
        ArrayList<List<String>> out = new ArrayList<List<String>>();
        Map<List<String>,List<Double>> outMap = new HashMap<List<String>,List<Double>>();
        outMap.put(turtleActions,turtleArgs);
        return outMap;
    }

    public void rotate(double angle,List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(ROTATE_ACTION);
        turtleArgs.add(angle);
        double finalAngle = angle+turtleParams.get(HEADING_KEY);
        turtleParams.put(HEADING_KEY,finalAngle);
    }

    public void clear(List<String> turtleActions){
        turtleActions.add(CLEAR_ACTION);
    }

    public double home(List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(HOME_ACTION);
        return turtleParams.get(DISTANCE_MOVED_KEY);
    }

    public void visible(double isVisible, List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(SET_VISIBLE);
        turtleArgs.add(isVisible);
    }

    public void pen(double isPenDown,List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(SET_PEN);
        turtleArgs.add(isPenDown);
    }
    public void position(double x, double y, List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(SET_POSITION);
        turtleArgs.add(x);
        turtleArgs.add(y);
    }
    public double heading(double angle, List<String> turtleActions, List<Double> turtleArgs, Map<String,Double> turtleParams){
        turtleActions.add(SET_HEADING);
        turtleArgs.add(angle);
        double oldHeading = turtleParams.get(HEADING_KEY);
        turtleParams.put(HEADING_KEY,angle);
        return angle-oldHeading;
    }


    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams);
}
