package model;

//Performs actions for each command


import java.util.List;
import java.util.Map;

public class Interpret {

    public double interpretCommand(String commandName, List<String> args, Map<String,Double> turtleParams,List<String> turtleActions,List<Double> actionArgs){
        Factory fac = new Factory();
        Command com = fac.makeCommand(commandName,args);
        double out =  com.execute(turtleActions,actionArgs,turtleParams);
        return out;
    }
}
