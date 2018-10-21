package model.commands.MathOps;

import model.Command;
import view.TurtleDisplay;

import java.util.LinkedList;

public class Variable extends Command {

    private String value;
    public Variable(String val) {
        super(new LinkedList<>());
        value = val;
    }

    @Override
    public double execute(TurtleDisplay display) {
        return 1;
    }

    public void setValue(String val) {
        value = val;
    }

    public String getValue() { return value; }
}
