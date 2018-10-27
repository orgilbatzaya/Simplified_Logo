package view.environmentdisplays;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import view.TurtleView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class StatusDisplay implements EnvironmentDisplay {
    private DoubleProperty xProperty = new SimpleDoubleProperty();
    private DoubleProperty yProperty = new SimpleDoubleProperty();
    private double xPos;
    private double yPos;
    private TurtleView myTurtle;
    private TableView<String> currentDisplay;
    private TableColumn currentTurtles;
    private TableColumn currentXPositions;
    private TableColumn currentYPositions;
    private TableColumn currentHeadings;
    private ObservableList<String[]> currentItems;
    private VBox myBox;


    public StatusDisplay(double height, String label,TurtleView turtle) {
        currentDisplay = new TableView<>();
        currentDisplay.setMaxHeight(height);
        Label displayLabel = new Label(label);
        xPos = turtle.getX();
        yPos = turtle.getY();
        myTurtle = turtle;
        xProperty.setValue(xPos);
        xProperty.addListener(updater);
        ArrayList<String> turtleStatus = new ArrayList<>();
        turtleStatus.add("0");
        turtleStatus.add(Double.toString(turtle.getX()));
        turtleStatus.add(Double.toString(turtle.getY()));
        turtleStatus.add(Double.toString(turtle.getHeading()));
        //currentItems.add(turtleStatus.toArray(new String[0]));
        currentTurtles = new TableColumn("Turtles");
        currentXPositions = new TableColumn("X Position");
        currentYPositions = new TableColumn("Y Position");
        currentHeadings = new TableColumn("Heading");
        currentDisplay.getColumns().addAll(currentTurtles, currentXPositions, currentYPositions, currentHeadings);
        myBox = new VBox(displayLabel, currentDisplay);
    }

    ChangeListener updater = (observable, oldValue, newValue) -> {
       xPos = myTurtle.getX();
    };

    @Override
    public void addItem(String item) {
        return;
    }

    @Override
    public void removeItem(String item) {
        return;
    }

    @Override
    public void editItem(String item) {
        System.out.println("Hi");
    }

    public VBox getDisplay() {
        return myBox;
    }

}
