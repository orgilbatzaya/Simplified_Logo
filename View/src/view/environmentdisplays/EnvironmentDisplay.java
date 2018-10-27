package view.environmentdisplays;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public interface EnvironmentDisplay {
    void addItem(String item);
    void removeItem(String item);
    void editItem(String item);
}
