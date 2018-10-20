package View;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

public interface FrontInternal {
    Scene createGUI(int width, int height, Paint background);

    Button createButton(String title);

    Label createLabel(String text);
}
