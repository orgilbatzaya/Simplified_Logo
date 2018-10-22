package view;

import view.Actions.Action;

import java.util.*;

public class ActionRunner {
    public String NUM_ARGS_ACTIONS_MAP_PATH = "view/NumArgsActions";

    private Map<String,Integer> numArgsActions;

    public ActionRunner(){
        numArgsActions = getNumArgsMap(NUM_ARGS_ACTIONS_MAP_PATH);
    }

    public void performActions(List<String> actions, List<Double> totalArgs,TurtleDisplay display){
        int argIndex = 0;
        for(String a: actions){
            int numArgs = numArgsActions.get(a);
            List<Double> args = getArgs(totalArgs,numArgs,argIndex);
            argIndex+=numArgs;
            interpretCommand(a,args,display);
        }
    }

    public Action makeAction(String commandName, List<Double> args){
        try{
            Class cls = Class.forName(commandName);
            var constructor = cls.getConstructor();
            Object newCommand = constructor.newInstance(args);
            return (Action) newCommand;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void interpretCommand(String actionName,List<Double> args, TurtleDisplay display){

        Action act = makeAction(actionName,args);
        act.execute(display);
    }

    public Map<String,Integer> getNumArgsMap(String path){
        ResourceBundle properties = ResourceBundle.getBundle(path);
        var outMap = new HashMap<String,Integer>();
        for (String key : properties.keySet()) {
            String value = properties.getString(key);
            outMap.put(key, Integer.valueOf(value));
        }
        return outMap;
    }

    public List<Double> getArgs(List<Double> totalArgs, int numArgs, int currentArgIndex){
        var args = new ArrayList<Double>();
        for(int i =currentArgIndex; i<currentArgIndex+numArgs; i++){
                args.add(totalArgs.get(i));
        }
        return args;
    }
}
