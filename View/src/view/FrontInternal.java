package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public interface FrontInternal {
    Scene createGUI(int width, int height, Paint background);

    VBox getDisplay();
}
