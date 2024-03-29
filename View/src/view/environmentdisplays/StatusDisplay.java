package view.environmentdisplays;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import view.TurtleDisplay;
import view.TurtleView;

import java.util.ArrayList;

/**
 * @author Orgil Batzaya, Austin Kao
 */
public class StatusDisplay implements EnvironmentDisplay{
    private static final String NAME_TITLE = "Turtles";
    private static final String X_POSITION_TITLE = "X Position";
    private static final String Y_POSITION_TITLE = "Y Position";
    private static final String HEADING_TITLE = "Heading";
    private static final String NAME_PROPERTY = "myID";
    private static final String X_POSITION_PROPERTY = "xPos";
    private static final String Y_POSITION_PROPERTY = "yPos";
    private static final String HEADING_PROPERTY = "heading";

    private TableView<TurtleView> currentDisplay;
    private TableColumn currentTurtles;
    private TableColumn currentXPositions;
    private TableColumn currentYPositions;
    private TableColumn currentHeadings;
    private ObservableList<TurtleView> currentItems;
    private VBox myBox;


    public StatusDisplay(double height, String label, TurtleDisplay display) {
        currentDisplay = new TableView<>();
        currentDisplay.setMaxHeight(height);
        Label displayLabel = new Label(label);
        currentItems = FXCollections.observableArrayList();
        currentTurtles = createTableColumn(NAME_TITLE, NAME_PROPERTY);
        currentXPositions = createTableColumn(X_POSITION_TITLE, X_POSITION_PROPERTY);
        currentYPositions = createTableColumn(Y_POSITION_TITLE, Y_POSITION_PROPERTY);
        currentHeadings = createTableColumn(HEADING_TITLE, HEADING_PROPERTY);
        for(TurtleView turtle : display.getTurtles().values()) {
            currentItems.add(turtle);
        }
        currentDisplay.getColumns().addAll(currentTurtles, currentXPositions, currentYPositions, currentHeadings);
        currentDisplay.setItems(currentItems);
        currentDisplay.setEditable(true);
        myBox = new VBox(displayLabel, currentDisplay);
    }

    public VBox getDisplay() {
        return myBox;
    }

    @Override
    public TableColumn createTableColumn(String title, String property) {
        TableColumn column = new TableColumn(title);
        column.setCellValueFactory(new PropertyValueFactory<TurtleView, Double>(property));
        return column;
    }

    public void update(TurtleDisplay display) {
        currentItems.clear();
        for(TurtleView turtle : display.getTurtles().values()) {
            currentItems.add(turtle);
        }
        currentDisplay.setItems(currentItems);
    }
}
