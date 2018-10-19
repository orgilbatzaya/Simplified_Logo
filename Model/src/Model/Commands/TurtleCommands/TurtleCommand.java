package Model.Commands.TurtleCommands;

import Model.Command;

import java.util.List;

public abstract class TurtleCommand extends Command {

    public TurtleCommand(List<String> args){
        super(args);
    }


    public abstract double execute();
}
