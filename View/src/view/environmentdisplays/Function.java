package view.environmentdisplays;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Function {
    private StringProperty functionName;
    private StringProperty functionDefinition;

    public Function(String name, String[] valueArray) {
        functionName = new SimpleStringProperty(name);
        String value = String.join(" ", valueArray);
        functionDefinition = new SimpleStringProperty(value);
    }

    public String getFunctionName() {
        return functionName.get();
    }

    public String getFunctionDefinition() {
        return functionDefinition.get();
    }
}
