package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Pi extends Command {
    public Pi(List<String> args){
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return Math.PI;
    }
}

