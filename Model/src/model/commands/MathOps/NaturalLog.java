package model.commands.MathOps;

import model.Command;

import java.util.List;

public class NaturalLog extends Command {
    public NaturalLog(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double inputVal = getArgsDouble(0);
        return Math.log(inputVal);
    }
}

