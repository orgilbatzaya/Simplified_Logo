SLogo API Russell Llave (ral30), Allen Cao (ac497), Orgil Batzaya (ob29), Austin Kao (ak457)
==========================
# Slogo Architecture Design
* What is the result of parsing and who receives it?
    *  Parsing should result in a method and the parameters associated with that method
    *  The backend receives and uses the information to tell the front end how to move the turtle onscreen.
* When does parsing need to take place and what does it need to start properly?
    * Parsing needs to take place at the very beginning of a command's execution, when the user hits the "Enter" button
    * To start properly, it needs a name that specifies a method and the parameters associated with the method.
* When are errors detected and how are they reported?
    * When a command with the wrong syntax or impossible parameters is typed in, the system should display an error message
    * When the turtle moves off the grid, the system should display an error message
* What do commands know, when do they know it, and how do they get it?
    * Commands know the position of the turtle and the layout of the grid from the main class
    * Commands also know the parameters associated with a particular method
    * They know the location of the turtle at the beginning of the command
    * They get it from the front end
* How is the GUI updated after a command has completed execution?
    * The turtle will move accordingly based on a correct command.
    

# What actions is this class responsible for?
What other classes are required to help this class fulfill those responsibilities?  
These other classes will either be passed to this object's constructor or methods, or returned from a method, or created by this object internally.

# Create API
* External: between the view (front end) and model (back end) modules
    * How you plan to separate the graphical interface from the interpreter and how you plan to let them communicate when necessary.
        * The interpreter will return the commands entered into the program. Those commands will be transfered to a simulation type class where the turtle and the grids properties will be udpated. Then those updated states will be sent to the graphical interface class which updates what is displayed.
            * readLine()
            
    * What objects will be used for communication: making it clear how needed information will get where it is needed, what will be available and what will be encapsulated, what things will be immutable, and what errors may be thrown. Note, all of these methods will need to be public.
        * Command class that takes in user input from the interpreter and passes instructions to the Model/backend
            * getNextSteps() returns an ordered Collection of (x,y) positions
            * getCurrentPos()
            * Errors about invalid commands may be thrown here

        * GraphicalManager class which takes in user input and alters the visualization
            * updateGUI
        
* Internal: within each module (i.e., for its future programmers/maintainers)
    * How you plan to provide paths for extension through interfaces, inheritance, and design patterns for new features you might reasonably expect to be added to the program.
        * A generic object class can be made for what moves that can be extended to different types of objects that might have different properties when added
        * A generic interpreter class can be made for the commands passed in and be extended to different interpretations for different commands in the future
    * What subclasses or implementing classes will be used to extend your part to add new features: making it clear what kind of code someone will be expected to write, what parts of your code you expect to be closed to modification, and what errors may be thrown.
        * A subclass of interpreter may have to write new cases to interpret different commands
    * Note, while some of these methods may be public, many may be protected or package friendly.

# Use Cases
* The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
    * The interpreter interprets the command
    * The position of the turtle is updated to move forward 50 steps
    * The grid is updated with marks on where the turtle traveled
    * The graphical interface is updated with the new marks and the turtle's location
* The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.
    * The interpreter interprets the command
    * The interpreter checks against cases of possible commands and does not find the command
    * The interpreter sends to the graphical interface that there is a wrong command
    * The graphical interface displays the error message
* The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once without a trail and once with a trail).
    * The interpreter sees the pu option
    * The interpreter knows not to mark any grid
    * The interpreter sees the command
    * The grid is updated with the turtle's movement
    * The interpreter reads the command
    * The interpreter tells the grid to mark itself
    * The interpreter sees the command
    * The grid is updated with the turtle's location and marks the grid
* The user changes the color of the environment's background.
    * The graphical interface will process the color change and set it onscreen.



