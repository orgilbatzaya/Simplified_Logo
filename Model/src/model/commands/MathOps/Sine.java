package model.commands.MathOps;

import model.Command;

import java.util.List;

public class Sine extends Command {
    public Sine(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double inputVal = getArgsDouble(0);
        return Math.sin(Math.toRadians(inputVal));
    }
}

