package view.dropdown;

import javafx.collections.FXCollections;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Orgil Batzaya
 * Allows users to dynamically select a Turtle to draw with using a dropdown menu.
 * Allows multiple turtles to be active simultaneously
 */

public class TurtleSelector extends DropdownMenu {
    //Map<Integer, TurtleView> turtles;
    TurtleDisplay td;
    public TurtleSelector(String label, TurtleDisplay td){
        super(new ArrayList<>(),label);
        this.td = td;
        ArrayList<String> ids = new ArrayList<>();
        for(Integer key : td.getTurtles().keySet()) {
            ids.add(key.toString());
        }
        getChoiceBox().setItems(FXCollections.observableArrayList(ids));
        getChoiceBox().setValue(ids.get(0));
        getDisplay().setLayoutX(800);
        getDisplay().setLayoutY(200);
    }

    public void processChoice(String choice){
        TurtleView t = td.getTurtles().get(Integer.parseInt(choice));
        //turnOthersOff(choice);

        if(!t.isActive()){
            t.getView().setEffect(new DropShadow());
            t.activate();
        }
        else if(t.isActive()){
            t.getView().setEffect(null);
            t.deactivate();
        }
    }

//    private void turnOthersOff(String choice){
//        for(int i = 0; i < turtles.size(); i++){
//            if(i != (int) Integer.parseInt(choice)){
//                turtles.get(i).deactivate();
//                turtles.get(i).getView().setEffect(null);
//            }
//        }
//    }
}
