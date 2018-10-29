package view.environmentdisplays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class VariableDisplay implements EnvironmentDisplay{
    private static final String NAME_TITLE = "Variables";
    private static final String VALUE_TITLE = "Values";
    private static final String NAME_PROPERTY = "variableName";
    private static final String VALUE_PROPERTY = "variableValue";

    private TableView<Variable> currentDisplay;
    private TableColumn currentVariables;
    private TableColumn currentValues;
    private ObservableList<Variable> currentItems;
    private VBox myBox;

    public VariableDisplay(double height, String label) {
        currentDisplay = new TableView<>();
        Label displayLabel = new Label(label);
        currentItems = FXCollections.observableArrayList();
        currentDisplay.setMaxHeight(height);
        currentVariables = createTableColumn(NAME_TITLE, NAME_PROPERTY);
        currentValues = createTableColumn(VALUE_TITLE, VALUE_PROPERTY);
        currentDisplay.getColumns().addAll(currentVariables, currentValues);
        myBox = new VBox(displayLabel, currentDisplay);
        //currentDisplay.setOnMouseClicked(e -> editItem(currentDisplay.getSelectionModel().getSelectedItem()));
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

    private TableColumn createTableColumn(String title, String property) {
        TableColumn column = new TableColumn(title);
        column.setCellValueFactory(new PropertyValueFactory<Variable, String>(property));
        return column;
    }

    public void update(HashMap<String, String> variableMap) {
        currentItems.clear();
        for(String s : variableMap.keySet()) {
            Variable v = new Variable(s, variableMap.get(s));
            currentItems.add(v);
        }
        currentDisplay.setItems(currentItems);
    }
}
