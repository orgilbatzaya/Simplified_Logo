package view;

import view.Actions.Action;

import java.util.*;

public class ActionRunner implements ViewResourceBundles {

    private Map<String,Integer> numArgsActions;

    public ActionRunner(){
        numArgsActions = getNumArgsMap();
    }

    public void performActions(List<String> actions, List<Double> totalArgs, TurtleDisplay display){
        int argIndex = 0;

        for(String a: actions){
            int numArgs = numArgsActions.get(a);
            List<Double> args = getArgs(totalArgs,numArgs,argIndex);
            argIndex+=(numArgs-1);//because one indexed
            interpretCommand(a,args,display);
        }
    }

    public Action makeAction(String actionName, List<Double> args){
        try{
            String name = "view.Actions."+actionName;

            Class cls = Class.forName(name);
            var constructor = cls.getConstructor(List.class);
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

    public Map<String,Integer> getNumArgsMap(){
        var outMap = new HashMap<String,Integer>();
        for (String key : myArgs.keySet()) {
            String value = myArgs.getString(key);
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
