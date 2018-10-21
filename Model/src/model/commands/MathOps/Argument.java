package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.List;

public class Argument extends Command {
    public Argument(List<String> args) {
        super(args);
    }

    @Override
    public double execute(TurtleDisplay display) {
        return getArgsDouble(0);
    }
}
