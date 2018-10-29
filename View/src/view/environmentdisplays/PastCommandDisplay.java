package view.environmentdisplays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class stores a list of past commands as well as the display of that list.
 * It will also communicate to the back end the current command and execute the command.
 * @author Austin Kao
 */
public class PastCommandDisplay {
    private VBox parentBox;
    private ListView<String> currentDisplay;
    private ObservableList<String> currentItems;
    private int maxSize;

    public PastCommandDisplay(double height, String label) {
        this(height, -1, label);
    }

    public PastCommandDisplay(double height, int max, String label) {
        currentDisplay = new ListView<>();
        Label displayLabel = new Label(label);
        currentItems = FXCollections.observableArrayList();
        currentDisplay.setMaxHeight(height);
        maxSize = max;
        parentBox = new VBox(displayLabel, currentDisplay);
    }

    public void addItem(String newItem) {
        currentItems.add(0, newItem);
        if(maxSize >= 0 && currentItems.size() > maxSize) {
            currentItems.remove(maxSize - 1);
        }
        currentDisplay.setItems(currentItems);
    }

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

    public ListView<String> getPastCommandList() {
        return currentDisplay;
    }
}
