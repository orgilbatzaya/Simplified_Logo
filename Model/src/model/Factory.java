package model;
import java.util.List;

public class Factory {
    public Command makeCommand(String commandName, List<String> args){
        try{

            String name = "model.commands.TurtleCommands."+commandName;
            Class cls = Class.forName(name);
            var constructor = cls.getConstructor(List.class);
            Object newCommand = constructor.newInstance(args);
            return (Command) newCommand;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
