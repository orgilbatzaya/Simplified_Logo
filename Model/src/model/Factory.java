package model;
import java.util.*;

public class Factory {


    public Command makeCommand(String commandName, List<String> args,String commandType){
        try{
            String name = "model.commands."+commandType+"."+commandName;
            Class cls = Class.forName(name);
            var constructor = cls.getConstructor(List.class);
            Object newCommand = constructor.newInstance(args);
            return (Command) newCommand;
        }
        catch(Exception e){
           new ErrorAlert(e);
        }
        return null;
    }

}
