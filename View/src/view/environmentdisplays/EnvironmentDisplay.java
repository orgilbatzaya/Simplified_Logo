package view.environmentdisplays;

import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;

/**
 * This interface is meant for the different environment displays in SLogo.
 * These include the ones for displaying past commands the user has typed, current variables in the environment, and current user-defined commands in the environment.
 * @author Austin Kao
 */

public interface EnvironmentDisplay {
    TableColumn createTableColumn(String title, String property);
    VBox getDisplay();
}
