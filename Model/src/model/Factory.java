package model;

import java.util.List;

public class Factory {
    public Command makeCommand(String commandName, List<String> args){
        try{
            Class cls = Class.forName(commandName);
            var constructor = cls.getConstructor();
            Object newCommand = constructor.newInstance(args);
            return (Command) newCommand;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
