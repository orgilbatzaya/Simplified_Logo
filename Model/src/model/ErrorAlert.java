package model;

import javafx.scene.control.Alert;

/**
 * This class is used to throw errors and display the error as an alert for the user.
 * @author Austin Kao
 */

public class ErrorAlert {
    private static final String ERROR_STRING = "Error";
    private static final String ERROR_HEADER = "The following error occurred:";

    public ErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ERROR_STRING);
        alert.setHeaderText(ERROR_HEADER);
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}
