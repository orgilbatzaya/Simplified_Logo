package view;

import javafx.scene.control.Alert;

/**
 * This class is used to throw errors and display the error as an alert for the user.
 * @author Austin Kao
 */

public class ErrorAlert {
    public ErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("The following error occurred:");
        alert.setContentText(ex.getMessage());
        alert.showAndWait();
    }
}
