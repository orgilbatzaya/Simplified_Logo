package View;

import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public interface FrontInternal {
    public default Scene createGUI(int width, int height, Paint background) {return null;}
}
