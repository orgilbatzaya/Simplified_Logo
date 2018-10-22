package model;
import java.util.List;
import java.util.Map;

public abstract class Command {


    private List<String> myArgs;
    public Command(List<String> args) {
        myArgs = args;

    }
    public Double getArgsDouble(int index){
        return Double.parseDouble(myArgs.get(index));
    }

    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams);

}
