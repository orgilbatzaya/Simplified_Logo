package Model.Commands.TurtleCommands;

import Model.Command;

import java.util.List;

public abstract class TurtleCommand extends Command {

    public TurtleCommand(String commandName, List<String> args){
        super(commandName,args);
    }


    public abstract double execute();
}
