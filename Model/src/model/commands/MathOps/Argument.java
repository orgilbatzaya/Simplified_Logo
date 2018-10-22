package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.LinkedList;
import java.util.List;

public class Argument extends Command {

    private double value;
    public Argument(Double val) {
        super(new LinkedList<>());
        value = val;
    }

    @Override
    public double execute(TurtleDisplay display) {
        return value;
    }

    public void setValue(Double val) {
        value = val;
    }
}
