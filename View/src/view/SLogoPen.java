package view;

import javafx.scene.paint.Color;

public class SLogoPen {
    private Color penColor;
    private boolean penVisible;

    public SLogoPen(Color c) {
        penColor = c;
        penVisible = true;
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color c){
        penColor = c;
    }

    public void changePenVisibilty(){
        penVisible = !penVisible;
    }

    public boolean isVisible() {
        return penVisible;
    }
}
