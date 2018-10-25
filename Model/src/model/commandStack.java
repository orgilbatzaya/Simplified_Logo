package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class commandStack {

    private final Set<String> BOOLEAN_OPS = new HashSet<>(Arrays.asList("And", "Equal", "GreaterThan", "LessThan", "Not", "NotEqual", "Or"));
    private final Set<String> MATH_OPS = new HashSet<>(Arrays.asList("ArcTangent", "Cosine", "Difference", "Minus", "NaturalLog", "Pi", "Power", "Product", "Quotient", "Random", "Remainder", "Sine", "Sum", "Tangent"));
    private final Set<String> CONTROL_OPS = new HashSet<>(Arrays.asList("DoTimes", "For", "If", "IfElse", "MakeUserInstruction", "MakeVariable", "Repeat"));
    private final Set<String> TURTLE_COMMANDS = new HashSet<>(Arrays.asList("Backward", "ClearScreen", "Forward", "HideTurtle", "Home", "Left", "PenDown", "PenUp", "Right", "SetHeading", "SetPosition", "SetTowards", "Showturtle"));

    private Stack<Command> commands = new Stack<>();

    public commandStack(String[] text) {
        for()
    }
}
