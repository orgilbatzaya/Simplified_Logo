package model.commands;

import java.util.List;
import java.util.Map;

public abstract class MultipleTurtleCommand extends TurtleCommand {
   public static final String TELL_ACTION = "Tell";

    public static final String ID_KEY = "id";
    public static final String ACTIVE_KEY = "active";


    public MultipleTurtleCommand(List<String> args){super(args);}

    public void Tell(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams){
        turtleAction.add(TELL_ACTION);
        for(int i = 0; i<getMyArgs().size(); i++){
            turtleActionArgs.add(getArgsDouble(i));
        }
    }


    public abstract double execute(List<String> turtleAction, List<Double> turtleActionArgs, Map<String, Double> turtleParams);
}
