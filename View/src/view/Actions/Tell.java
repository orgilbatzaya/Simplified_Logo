package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;

public class Tell extends Action {
    private static final int FIRST_INDEX = 0;

    public Tell(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleView turtle, TurtleDisplay display)
    {

        for (int i = 0; i<getMyArgs().size(); i++){
            display.deactivateAllTurtles();
            if(display.getTurtles().containsKey(getArgsDouble(i))){
                display.getTurtles().get(getArgsDouble(i)).activate();
            }
        }
    }
}
