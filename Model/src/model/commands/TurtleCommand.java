package model.commands;

import model.Command;

import java.util.ArrayList;
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
    public static final String ID_KEY = "id";
    public static final String ACTIVE_KEY = "active";




    //convenient doubles
    public static final Double ZERO_DOUBLE = (double) 0;
    public static final Double ONE_DOUBLE = (double) 1;

    private List<Integer> activeIndexes;
    public TurtleCommand(List<String> args) {
        super(args);
        activeIndexes = new ArrayList<Integer>();

    }
    public void move(double distance,List<String> turtleActions, List<Double> turtleArgs, List<Map<String,Double>> turtleParams) {
        turtleActions.add(MOVE_ACTION);
        turtleArgs.add(distance);

        getActiveIndexes(turtleParams);
        for(int i = 0; i<activeIndexes.size();i++){
            double newX = turtleParams.get(activeIndexes.get(i)).get(X_KEY)+distance*Math.cos(Math.toRadians(turtleParams.get(activeIndexes.get(i)).get(HEADING_KEY))-90);
            double newY = turtleParams.get(activeIndexes.get(i)).get(Y_KEY)+distance*Math.sin(Math.toRadians(turtleParams.get(activeIndexes.get(i)).get(HEADING_KEY))-90);
            turtleParams.get(activeIndexes.get(i)).put(X_KEY,newX);
            turtleParams.get(activeIndexes.get(i)).put(Y_KEY,newY);
            turtleParams.get(activeIndexes.get(i)).put(DISTANCE_MOVED_KEY,turtleParams.get(activeIndexes.get(i)).get(DISTANCE_MOVED_KEY)+distance);
        }



    }

    public  void rotate(double angle,List<String> turtleActions, List<Double> turtleArgs, List<Map<String,Double>> turtleParams){
        turtleActions.add(ROTATE_ACTION);
        turtleArgs.add(angle);
        getActiveIndexes(turtleParams);
        for(int i = 0; i<activeIndexes.size();i++){
            double finalAngle = angle+turtleParams.get(activeIndexes.get(i)).get(HEADING_KEY);
            turtleParams.get(activeIndexes.get(i)).put(HEADING_KEY,finalAngle);
        }



    }

    public double clear(List<String> turtleActions, List<Double> turtleArgs, List<Map<String,Double>> turtleParams){
        turtleActions.add(CLEAR_ACTION);

        double distance = turtleParams.get(activeIndexes.get(0)).get(DISTANCE_MOVED_KEY);
        turtleParams.get(activeIndexes.get(0)).put(DISTANCE_MOVED_KEY,ZERO_DOUBLE);

        return distance; //can't return multiple distances
    }

    public double home(List<String> turtleActions, List<Double> turtleArgs, List<Map<String,Double>> turtleParams){
        turtleActions.add(HOME_ACTION);
        getActiveIndexes(turtleParams);
        double distance = turtleParams.get(activeIndexes.get(0)).get(DISTANCE_MOVED_KEY);
        turtleParams.get(activeIndexes.get(0)).put(DISTANCE_MOVED_KEY,ZERO_DOUBLE);

        return distance; //can't return multiple distances
    }

    public void visible(double isVisible, List<String> turtleActions, List<Double> turtleArgs,  List<Map<String,Double>> turtleParams){
        turtleActions.add(SET_VISIBLE);
        turtleArgs.add(isVisible);
    }

    public void pen(double isPenDown,List<String> turtleActions, List<Double> turtleArgs,  List<Map<String,Double>> turtleParams){
        turtleActions.add(SET_PEN);
        turtleArgs.add(isPenDown);
    }
    public double position(double x, double y, List<String> turtleActions, List<Double> turtleArgs, List<Map<String,Double>> turtleParams){
        turtleActions.add(SET_POSITION);
        turtleArgs.add(x);
        turtleArgs.add(y);

        getActiveIndexes(turtleParams);
        for(int i = 0; i<activeIndexes.size();i++){
            double distanceMoved = Math.sqrt(Math.pow(x-turtleParams.get(activeIndexes.get(i)).get(X_KEY),2)+Math.pow(y-turtleParams.get(activeIndexes.get(i)).get(Y_KEY),2));
            turtleParams.get(activeIndexes.get(i)).put(DISTANCE_MOVED_KEY, turtleParams.get(activeIndexes.get(i)).get(DISTANCE_MOVED_KEY)+distanceMoved);
            turtleParams.get(activeIndexes.get(i)).put(Y_KEY,y);
            turtleParams.get(activeIndexes.get(i)).put(X_KEY,x);
        }
        return turtleParams.get(activeIndexes.get(0)).get(DISTANCE_MOVED_KEY);
    }
    public double heading(double angle, List<String> turtleActions, List<Double> turtleArgs, List<Map<String,Double>> turtleParams){
        turtleActions.add(SET_HEADING);
        turtleArgs.add(angle);
        getActiveIndexes(turtleParams);
        for(int i = 0; i<activeIndexes.size();i++) {
            turtleParams.get(activeIndexes.get(0)).put(HEADING_KEY, angle);
        }
        return turtleParams.get(activeIndexes.get(0)).get(HEADING_KEY);

    }

    public void getActiveIndexes(List<Map<String,Double>> params){
        for(int i = 0; i<params.size(); i++){
            if(params.get(i).get(ACTIVE_KEY)==1){
                activeIndexes.add((int) Math.round(params.get(i).get(ID_KEY)));
            };
        }

    }

    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams);
}
