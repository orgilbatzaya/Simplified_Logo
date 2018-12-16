package view.flowpane;

import javafx.scene.layout.FlowPane;

/**
 * A flow pane in the SLogo GUI
 * @author ak457
 */
public abstract class SLogoFlowPane extends FlowPane {
    public SLogoFlowPane(double hGap, double vGap, double x, double y) {
        super(hGap, vGap);
        setLayoutX(x);
        setLayoutY(y);
    }

    public abstract void update();
}
