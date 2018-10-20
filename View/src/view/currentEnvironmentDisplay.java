package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class currentEnvironmentDisplay {
    private ListView<String> currentDisplay;
    private ObservableList<String> currentItems;
    private int maxSize;

    public currentEnvironmentDisplay(double height) {
        this(height, -1);
    }

    public currentEnvironmentDisplay(double height, int max) {
        currentDisplay = new ListView<>();
        currentItems = FXCollections.observableArrayList();
        currentDisplay.setMaxHeight(height);
        maxSize = max;
    }

    public void addItem(String newItem) {
        currentItems.add(0, newItem);
        if(maxSize != -1 && currentItems.size() > maxSize) {
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

    public ListView<String> getDisplay() {
        return currentDisplay;
    }
}
