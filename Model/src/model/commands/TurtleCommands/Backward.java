package model.commands.TurtleCommands;

import model.Command;
import view.TurtleView;

import java.util.List;

public class Backward extends Command {
    public Backward(List<String> args){
        super(args);
    }

    @Override
    public double execute() {
        TurtleView x = new TurtleView();
        x.getView();
        return 0;
    }
}
