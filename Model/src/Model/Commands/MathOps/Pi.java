package Model.Commands.MathOps;

import Model.Command;

import java.util.List;

public class Pi extends Command {
    public Pi(String commandName, List<String> args){
        super(commandName,args);
    }

    @Override
    public double execute() {
        return Math.PI;
    }
}

