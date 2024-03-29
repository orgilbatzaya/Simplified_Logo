package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a variable object to be displayed by the VariableDisplay
 * @author Austin Kao
 */
public class Variable {
    private StringProperty variableName;
    private StringProperty variableValue;

    public Variable(String name, String value) {
        variableName = new SimpleStringProperty(name);
        variableValue = new SimpleStringProperty(value);
    }

    public String getVariableName() {
        return variableName.get();
    }

    public String getVariableValue() {
        return variableValue.get();
    }

    public void setVariableName(String newName) {
        variableName.setValue(newName);
    }

    public void setVariableValue(String newValue) {
        variableValue.setValue(newValue);
    }
}
