package model.commands.MathOps;

import model.Command;

import java.util.List;

public class Random extends Command {
    public Random(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double maxVal = getArgsDouble(0);
        return Math.random()*maxVal;
    }
}

