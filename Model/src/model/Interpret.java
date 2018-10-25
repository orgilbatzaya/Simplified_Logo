package model;

//Performs actions for each command


import java.util.List;
import java.util.Map;

public class Interpret {

    public double interpretCommand(String commandName, List<String> args, Map<String,Double> turtleParams,List<String> turtleActions,List<Double> actionArgs){
        Factory fac = new Factory();
        System.out.println("a");
        Command com = fac.makeCommand(commandName,args);
        System.out.println("b");
        double out =  com.execute(turtleActions,actionArgs,turtleParams);
        System.out.println("c");
        return out;
    }
}
