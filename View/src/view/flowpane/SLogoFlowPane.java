package view.flowpane;

import javafx.scene.layout.FlowPane;

public abstract class SLogoFlowPane extends FlowPane {
    public SLogoFlowPane(double hGap, double vGap, double x, double y) {
        super(hGap, vGap);
        setLayoutX(x);
        setLayoutY(y);
    }

    public abstract void update();
}
