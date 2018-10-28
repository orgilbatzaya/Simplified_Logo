package view;

import java.util.ResourceBundle;

/**
 * Interface that defines resource bundles used by different View classes. DO NOT CHANGE Strings that specify paths unless the path also changes.
 * @author Austin Kao
 */
public interface ViewResourceBundles {
    ResourceBundle myImages = ResourceBundle.getBundle("/resources/TurtleImage");
    ResourceBundle myColors = ResourceBundle.getBundle("/resources/Color");
    ResourceBundle myLanguages = ResourceBundle.getBundle("/resources/LanguageList");
    ResourceBundle myDefaults = ResourceBundle.getBundle("/resources/ViewDefaults");
    ResourceBundle myArgs = ResourceBundle.getBundle("/resources/NumArgsActions");
    ResourceBundle myDirections = ResourceBundle.getBundle("/resources/Directions");
}
