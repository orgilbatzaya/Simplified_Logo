README
==================
* names of all people who worked on the project
    * Austin Kao, Orgil Batzaya, Russell Llave, Allen Cao
* date you started, date you finished, and an estimate of the number of hours worked on the project
    * Date started: 10/16/18
    * Date finished: 10/29/18
    * Hours worked: 120
* each person's role in developing the project
    * Austin - Front End (creating buttons, dropdowns, fields, displays for past commands, variables, functions, turtles)
    * Allen - backend (execution and processing order of commands)
    * Russell - Front End (create and run actions), Backend (create and run commands), communicate between backend and frontend
    * Orgil - Frontend(TurtleDisplay, TurtleView, Animation, multiple tabs and turtles)
* any books, papers, online, or human resources that you used in developing the project
    ** https://stackoverflow.com/questions/35585035/what-the-easiest-way-to-animate-a-path-as-an-object-traverses-it


* files used to start the project (the class(es) containing main)
    * SLogoMain (main class) in View module
* files used to test the project and errors you expect your program to handle without crashing
    * data/examples (files used to test)
    * error checks: 
        * checked the class name to be valid
        * checked the grammar to be accurate
* any data or resource files required by the project (including format of non-standard files)
    * Model
        * CommandType.Properties
        * NumArgsCommand.Properties
    * View
        * Resources Folder
    * data/images
* any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
    * Click on past command list to run past commands
    * Double click on variable list to edit variables
    * Press ESCAPE to exit the program
* any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements
    * We assumed that the user wants an infinite plane, so the turtle can go off the canvas.
* any known bugs, crashes, or problems with the project's functionality
    * DoTimes does not work, indexing of commands is off
    * Some nested commands don't work
    * not all errors are caught
    * buttons to move turtle only work on one turtle
    
* any extra features included in the project
    * Animation of line drawn (speed, start, pause)
* your impressions of the assignment to help improve it in the future
    * teach reflection earlier in the class so that we know about it when the project starts