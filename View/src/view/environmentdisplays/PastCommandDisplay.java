package view.environmentdisplays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * This class stores a list of past commands as well as the display of that list.
 * It will also communicate to the back end the current command and execute the command.
 * @author Austin Kao
 */
public class PastCommandDisplay implements EnvironmentDisplay {
    private VBox parentBox;
    private ListView<String> currentDisplay;
    private ObservableList<String> currentItems;
    private int maxSize;
    private Label selectionLabel;

    public PastCommandDisplay(double height, String label) {
        this(height, -1, label);
    }

    public PastCommandDisplay(double height, int max, String label) {
        currentDisplay = new ListView<>();
        Label displayLabel = new Label(label);
        currentItems = FXCollections.observableArrayList();
        currentDisplay.setMaxHeight(height);
        maxSize = max;
        selectionLabel = new Label("Selection");
        parentBox = new VBox(displayLabel, currentDisplay, selectionLabel);
        currentDisplay.setOnMouseClicked(e -> editItem(currentDisplay.getSelectionModel().getSelectedItem()));
    }

    @Override
    public void addItem(String newItem) {
        currentItems.add(0, newItem);
        if(maxSize >= 0 && currentItems.size() > maxSize) {
            currentItems.remove(maxSize - 1);
        }
        currentDisplay.setItems(currentItems);
    }

    @Override
    public void removeItem(String item) {
        if(currentItems.contains(item)) {
            currentItems.remove(item);
        }
        currentDisplay.setItems(currentItems);
    }

    public void clear() {
        currentItems.clear();
        currentDisplay.setItems(currentItems);
    }

    public VBox getDisplay() {
        return parentBox;
    }

    /**
     * This method should change depending on the type of PastCommandDisplay?
     * @param oldValue
     */
    @Override
    public void editItem(String oldValue) {
        selectionLabel.setText("You Selected " + oldValue);
    }
}
