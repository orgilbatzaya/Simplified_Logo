package Model.Commands.TurtleCommands;

import Model.Command;
import View.TurtleView;

import java.util.List;
import View.TurtleView;

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
