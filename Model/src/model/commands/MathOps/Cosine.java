package model.commands.MathOps;

import model.Command;

import java.util.List;

public class Cosine extends Command {
    public Cosine(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        double inputVal = getArgsDouble(0);
        return Math.cos(Math.toRadians(inputVal));
    }
}

