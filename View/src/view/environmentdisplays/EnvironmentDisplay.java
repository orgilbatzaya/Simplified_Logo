package view.environmentdisplays;

/**
 * This interface is meant for the different environment displays in SLogo.
 * These include the ones for displaying past commands the user has typed, current variables in the environment, and current user-defined commands in the environment.
 * @author Austin Kao
 */

public interface EnvironmentDisplay {
    void addItem(String item);
    void removeItem(String item);
    void editItem(String item);
}
