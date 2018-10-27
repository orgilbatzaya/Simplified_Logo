package view.environmentdisplays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class VariableDisplay implements EnvironmentDisplay{
    private TableView<String> currentDisplay;
    private TableColumn currentVariables;
    private TableColumn currentValues;
    private ObservableList<String> currentItems;
    private VBox myBox;

    public VariableDisplay(double height, String label) {
        currentDisplay = new TableView<>();
        Label displayLabel = new Label(label);
        currentItems = FXCollections.observableArrayList();
        currentDisplay.setMaxHeight(height);
        myBox = new VBox(displayLabel, currentDisplay);
        currentDisplay.setOnMouseClicked(e -> editItem(currentDisplay.getSelectionModel().getSelectedItem()));
    }

    @Override
    public void addItem(String item) {

    }

    @Override
    public void removeItem(String item) {

    }

    @Override
    public void editItem(String item) {

    }

    public VBox getDisplay() {
        return myBox;
    }
}
