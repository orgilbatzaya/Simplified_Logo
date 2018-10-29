package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Function {
    private StringProperty functionName;
    private StringProperty functionDefinition;

    public Function(String name, List<String> functionArgs) {
        functionName = new SimpleStringProperty(name);
        String value = String.join(" ", functionArgs);
        functionDefinition = new SimpleStringProperty(value);
    }

    public String getFunctionName() {
        return functionName.get();
    }

    public String getFunctionDefinition() {
        return functionDefinition.get();
    }
}