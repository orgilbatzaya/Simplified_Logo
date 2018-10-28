package view;

import javafx.scene.paint.Color;

public class SLogoPen {
    private Color penColor;
    private boolean penVisible;
    private double penWidth;
    private static double DEFAULT_PEN_WIDTH = 3;

    public SLogoPen(Color c) {
        penColor = c;
        penVisible = true;
        penWidth = DEFAULT_PEN_WIDTH;
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color c){
        penColor = c;
    }

    public void setPenWidth(double x){
        penWidth = x;
    }

    public double getPenWidth(){
        return penWidth;
    }

    public void changePenVisibilty(){
        penVisible = !penVisible;
        System.out.println(penVisible);
    }

    public boolean isVisible() {
        return penVisible;
    }
}
