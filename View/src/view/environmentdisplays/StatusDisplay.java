package view.environmentdisplays;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import view.TurtleView;

import java.util.ArrayList;

public class StatusDisplay implements EnvironmentDisplay {
    private static final String NAME_PROPERTY = "Turtles";
    private static final String X_POSITION_PROPERTY = "X Position";
    private static final String Y_POSITION_PROPERTY = "Y Position";
    private static final String HEADING_PROPERTY = "Heading";

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
    private ObservableList<String> currentItems;
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
        currentItems = FXCollections.observableArrayList();
        currentItems.add("Zero");
        currentTurtles = createTableColumn(NAME_PROPERTY);
        currentXPositions = createTableColumn(X_POSITION_PROPERTY);
        currentYPositions = createTableColumn(Y_POSITION_PROPERTY);
        currentHeadings = createTableColumn(HEADING_PROPERTY);
        currentDisplay.getColumns().addAll(currentTurtles, currentXPositions, currentYPositions, currentHeadings);
        currentDisplay.setItems(currentItems);
        currentDisplay.setEditable(true);
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

    private TableColumn createTableColumn(String property) {
        TableColumn column = new TableColumn(property);
        column.setCellValueFactory(new PropertyValueFactory<TurtleView, String>(property));
        return column;
    }
}
