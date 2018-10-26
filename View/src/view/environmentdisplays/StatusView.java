package view.environmentdisplays;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import view.TurtleView;

public class StatusView implements EnvironmentDisplay {
    DoubleProperty xProperty = new SimpleDoubleProperty();
    DoubleProperty yProperty = new SimpleDoubleProperty();
    double xPos;
    double yPos;
    TurtleView myTurtle;
    private TableView<String> currentDisplay;
    private ObservableList<String> currentItems;
    private VBox myBox;


    public StatusView(double height, String label,TurtleView turtle) {
        currentDisplay = new TableView<>();
        currentDisplay.setMaxHeight(height);
        Label displayLabel = new Label(label);
        xPos = turtle.getX();
        yPos = turtle.getY();
        myTurtle = turtle;
        xProperty.setValue(xPos);
        xProperty.addListener(updater);
        myBox = new VBox(displayLabel, currentDisplay);
    }

    ChangeListener updater = (observable, oldValue, newValue) -> {
       xPos = myTurtle.getX();
    };

    public void addItem(String newItem) {
        if(!currentItems.contains(newItem)) {
            currentItems.add(newItem);
        }
        currentDisplay.setItems(currentItems);
    }

    public void removeItem(String item) {
        if(currentItems.contains(item)) {
            currentItems.remove(item);
        }
        currentDisplay.setItems(currentItems);
    }

    public void editItem(String item) {
        System.out.println("Hi");
    }

    public VBox getDisplay() {
        return myBox;
    }

}
