SLogo Design Russell Llave (ral30), Allen Cao (ac497), Orgil Batzaya (ob29), Austin Kao (ak457)
==========================
## High-level Design Goals

The high-level design goals were to create an application that implements the SLogo programming language and to create a GUI capable of displaying the results of SLogo commands.  
For the backend, the goal would be to process the SLogo commands passed in the correct order. This involves keeping track of variables and functions defined as well as the arguments passed into functions in order and altering states of the turtles.

## Adding new features to the project
* Adding Back End Features:
    * Adding a new command
        * New commands that are not control commands and are not based on predefined commands
            * Create a new class that extends Command
            * Designate how many arguments need to be passed in the properties files for arguments
            * Define the execute function of the new class to perform whatever needs to be performed based on the given inputs
            * May need to create a new class that extends Action
            * Define the number of arguments needed for the Action subclass
            * Override the execute method in the Action subclass so that the correct turtle positions are displayed on screen and the correct animations execute
        * Control commands that control order of commands like For
            * Edit the commandStack execute function and add a new case for the command
            * In the new case, define what needs to be changed in the order of processing of commands

* Adding Front End Features:
    * Adding a new GUI component
        * Determine the type of GUI component desired (button, menu, color picker, etc.) first
        * Create a class for that type of GUI component. Be sure to extend the abstract class of the type of component.
        * For displays that, for example, show variables and past commands, try to implement the EnvironmentDisplay interface.
        * In the GUISetup class, call the constructor of the GUI component
        * If its a button, you can simply add the new button inside the VBox UserOptions
        * Can also make more VBoxes or HBoxes to house components 
        * Be sure to add the component to the Group called root so that it will appear on screen

## Major Design Choices, including trade-offs (i.e., pros and cons), made in your project
* Creating a separate class for almost every individual GUI element
    * GUISetup was intended to create and display GUI elements
    * Each element had its own kind of functionality, which was difficult to encapsulate in one class
    * Creating all the elements in GUISetup and defining their behavior there makes GUISetup less modular
    * However, references to the GUISetup that creates the elements are needed since information from other classes is often needed to achieve the desired functionality, which creates dependencies
* Using the Console class to run and process commands
    * GUISetup was intended to create GUI elements and display them
    * Console class had access to the String users input through the command line
    * Displays for variables and functions were also placed in the Console class
    * However, relied on the GUISetup class for certain information to send to the Model module
* Using a Stack to process commands instead of using a tree
    * Stack is easier to implement by just pushing and popping onto tree
    * Tree structure would be difficult to implement with order of nodes and bubling nodes down or up throughout the tree to adjust to new commands
    * Ultimately, we began with Stack and it would have been difficult and time consuming to learn and switch to a new data structure for processing commands in order
* Using Cases in CommandStack and Backmain to implement Control commands instead of Command Classes
    * Having the Control commands extending Command would have made for a cleaner hierarchy and made it easier to split commands up into different orders of execution and made the flow of execution cleaner and easier to understand
    * It would have been difficult to know which commands to use for each control command as they usually come first in order but last in the stack
    * We would have had to use some recursive logic to implement it so that the control commands could alter the flow of the commands before the control command was popped from the Stack
    * Ultimately, we chose to use cases in the checking of commands as a simpler way of parsing the control commands
* Use 2 modules (front end and back end)
    * Having 2 modules with similar implementations for Commands and Actions makes the code as a whole simpler to understand compared to having 3 modules
    * 2 modules also makes the differences between front end and back end more explicit.

## Assumptions and Decisions
* View module assumptions:
    * Assumed the turtle was on an infinite plane
    * Assumed Model would send maps of desired information that was entirely valid
    * Decided that users could simply use the course website to figure out what commands to use

* Backend Assumptions:
    * Assumed for some commands that inputs were only of a certain form
        * Ex: Repeat expr
            * Evaluates expr
            * Fd 50 still returns 50 so it could be used as expr, but sometimes the logic did not account for this type of expr
