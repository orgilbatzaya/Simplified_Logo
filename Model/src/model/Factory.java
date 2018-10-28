package model;
import java.util.*;

public class Factory {
    private final Map<String,Set<String>> myCommandTypeMap;

    public Factory (Map<String,Set<String>> commandMap){
        myCommandTypeMap = commandMap;
    }


    public Command makeCommand(String commandName, List<String> args){
        try{
            String commandType = getCommandType(commandName);
            String name = "model.commands."+commandType+"."+commandName;
            Class cls = Class.forName(name);
            var constructor = cls.getConstructor(List.class);
            Object newCommand = constructor.newInstance(args);
            return (Command) newCommand;
        }
        catch(Exception e){
           ErrorAlert a =  new ErrorAlert(e);
           a.displayAlert();
        }
        return null;
    }
    private String getCommandType(String commandName){
        for (String key : myCommandTypeMap.keySet()) {
            if(myCommandTypeMap.get(key).contains(commandName)){
                return key;
            }
        }
        return null;
    }
}
