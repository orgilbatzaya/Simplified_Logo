package view.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * This class represents the field where users can specify the duration of the animations running onscreen.
 * @author Austin Kao
 */
public class DurationField extends Field{
    private static final double DEFAULT_DURATION = 1;

    public DurationField(String label) {
        super(label);
        getField().setText(Double.toString(DEFAULT_DURATION));
    }

    public double getDuration() {
        return Double.parseDouble(getField().getText());
    }
}
