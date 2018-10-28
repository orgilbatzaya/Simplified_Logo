package view;

import java.util.ResourceBundle;

public interface ViewResourceBundles {
    ResourceBundle myImages = ResourceBundle.getBundle("/resources/TurtleImage");
    ResourceBundle myColors = ResourceBundle.getBundle("/resources/Color");
    ResourceBundle myLanguages = ResourceBundle.getBundle("/resources/LanguageList");
    ResourceBundle myDefaults = ResourceBundle.getBundle("/resources/ViewDefaults");
}
