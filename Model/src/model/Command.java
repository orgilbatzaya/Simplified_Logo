package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Command {


    private List<String> myArgs;
    private List<String> myTurtleActions;
    private List<Double> myTurtleArgs;

    public Command(List<String> args) {
        myTurtleActions = new ArrayList<String>();
        myTurtleArgs = new ArrayList<Double>();
        myArgs = args;

    }
    public Double getArgsDouble(int index){
        return Double.parseDouble(myArgs.get(index));
    }

    public void setArgs(List<String> args) { myArgs = args; }

    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams);

    public List<String> getMyTurtleActions(){

        return myTurtleActions;
    }

    public List<Double> getMyTurtleArgs(){
        return myTurtleArgs;
    }

    public void setMyTurtleActions(List<String> actions){
        myTurtleActions = actions;
    }

    public void setMyTurtleArgs(List<Double> turtleArgs){
        myTurtleArgs = turtleArgs;
    }

}
