package model;

import javafx.scene.control.Alert;

/**
 * This class is used to throw errors and display the error as an alert for the user.
 * @author Austin Kao
 */

public class ErrorAlert {
    private static final String ERROR_STRING = "Error";
    private static final String ERROR_HEADER = "The following error occurred:";

    private Alert myAlert;
    public ErrorAlert(Exception ex) {
        myAlert = new Alert(Alert.AlertType.ERROR);
        myAlert.setTitle(ERROR_STRING);
        myAlert.setHeaderText(ERROR_HEADER);
        myAlert.setContentText(ex.getMessage());
        myAlert.showAndWait();
    }
}
