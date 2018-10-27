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

public class TurtleSelector extends DropdownMenu {
    Map<Integer, TurtleView> turtles;

    public TurtleSelector(String label, TurtleDisplay td){
        super(new ArrayList<>(),label);
        turtles = td.getTurtles();
        ArrayList<String> ids = new ArrayList<>();
        for(Integer key : td.getTurtles().keySet()) {
            ids.add(key.toString());
        }
        getChoiceBox().setItems(FXCollections.observableArrayList(ids));
    }

    public void processChoice(String choice){
        TurtleView t = turtles.get(Integer.parseInt(choice));
        turnOthersOff(choice);
        t.getView().setEffect(new DropShadow());
        t.activate();
    }

    private void turnOthersOff(String choice){
        for(int i = 0; i < turtles.size(); i++){
            if(i != (int) Integer.parseInt(choice)){
                turtles.get(i).deactivate();
                turtles.get(i).getView().setEffect(null);
            }
        }
    }
}