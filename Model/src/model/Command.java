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

    public void setArgs(List<String> args) { myArgs = args; }

    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, List<Map<String, Double>> turtleParams);

    public List<String> getMyArgs() {return myArgs; }


}
