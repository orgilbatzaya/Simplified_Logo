package view.Actions;

import view.TurtleDisplay;

import java.util.List;

public class Pen extends Action {

    public Pen(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        turtleDisplay.getCanvas().changePenVisibilty();
    }
}
