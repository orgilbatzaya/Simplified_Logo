package view.environmentdisplays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import view.Function;

import java.util.HashMap;
import java.util.List;

/**
 * This class is intended to display the different functions created by the user.
 * @author Austin Kao
 */
public class FunctionDisplay implements EnvironmentDisplay {
    private static final String NAME_TITLE = "Functions";
    private static final String DEFINITION_TITLE = "Function Definitions";
    private static final String NAME_PROPERTY = "functionName";
    private static final String DEFINITION_PROPERTY = "variableValue";

    private TableView<Function> currentDisplay;
    private ObservableList<Function> currentItems;
    private VBox myBox;
    private TableColumn currentFunctionNames;
    private TableColumn currentFunctions;

    public FunctionDisplay(double height, String label) {
        currentDisplay = new TableView<>();
        Label displayLabel = new Label(label);
        currentItems = FXCollections.observableArrayList();
        currentFunctionNames = createTableColumn(NAME_TITLE, NAME_PROPERTY);
        currentFunctions = createTableColumn(DEFINITION_TITLE, DEFINITION_PROPERTY);
        currentDisplay.setMaxHeight(height);
        currentDisplay.getColumns().addAll(currentFunctionNames, currentFunctions);
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
        column.setCellValueFactory(new PropertyValueFactory<Function, String>(property));
        return column;
    }

    public void update(HashMap<String, List<String>> functionMap) {
        currentItems.clear();
        for(String s : functionMap.keySet()) {
            Function f = new Function(s, functionMap.get(s));
            currentItems.add(f);
        }
        currentDisplay.setItems(currentItems);
    }
}
