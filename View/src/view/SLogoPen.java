package view;

import javafx.scene.paint.Color;

public class SLogoPen {
    private static final double PEN_WIDTH = 3;
    private Color penColor;
    private boolean penVisible;
    private double width;

    public SLogoPen(Color c) {
        penColor = c;
        penVisible = true;
        width = PEN_WIDTH;
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color c){
        penColor = c;
    }

    public void setPenWidth(double x){
        width = x;
    }

    public double getPenWidth(){
        return width;
    }

    public void changePenVisibilty(){
        penVisible = !penVisible;
    }

    public boolean isVisible() {
        return penVisible;
    }

    public double getWidth() {
        return width;
    }
}
