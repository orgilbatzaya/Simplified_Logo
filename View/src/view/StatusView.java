package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class StatusView extends CurrentEnvironmentDisplay{
    DoubleProperty xProperty = new SimpleDoubleProperty();
    DoubleProperty yProperty = new SimpleDoubleProperty();
    double xPos;
    double yPos;
    TurtleView myTurtle;


    public StatusView(double height, String label,TurtleView turtle) {
        super(height, label);
        xPos = turtle.getX();
        yPos = turtle.getY();
        myTurtle = turtle;
        xProperty.setValue(xPos);
        xProperty.addListener(updater);
    }

    ChangeListener updater = (observable, oldValue, newValue) -> {
       xPos = myTurtle.getX();
    };





}
