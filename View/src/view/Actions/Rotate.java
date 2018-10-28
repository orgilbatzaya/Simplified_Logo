package view.Actions;

import view.TurtleDisplay;
import view.TurtleView;

import java.util.List;
import java.util.Map;

public class Rotate extends Action{
    private static final int FIRST_INDEX = 0;

    public Rotate(List<Double> args){
        super(args);
    }

    @Override
    public void execute(TurtleDisplay turtleDisplay) {
        Map<Integer, TurtleView> map = turtleDisplay.getTurtles();
        for(int i = 0; i < map.size(); i++){
            if(map.get(i).isActive()){
                TurtleView t = map.get(i);
                t.rotate(t.getView().getRotate() + getArgsDouble(FIRST_INDEX));
            }
        }
    }
}
