#### Estimation: before looking at the old code:
1. how long do you think it will take you to complete this new feature?
   * I think it will take 1- 1.5 hours to implement the new interactive view.
2. how many files will you need to add or update? Why?
    * I think I will only need to update GUISetup to add the frontend view because
    the method for changing the turtle's image is already implemented in TurtleView.
    I also might make a class for the view itself. I would also add a few more image files.
#### Review: after completing the feature:
1. how long did it take you to complete this new feature?
   * Half an hour
2. how many files did you need to add or update? Why?
    * I updated GUISetup because that is where all the GUI elements are 
    initialized and placed, and I made one new class to house it set on click logic
    and connection with the current turtleDisplay.
3. did you get it completely right on the first try?
    * Yes
4. Analysis: what do you feel this exercise reveals about your project's design and documentation?
   was it as good (or bad) as you remembered?
   what could be improved?
   what would it have been like if you were not familiar with the code at all?
   * This reveals that the project's front end was very easy to use and had 
   aptly named and sized methods that made reintegrating simple. It was better than I had
   remembered because I forgot how much refactoring was done in GUISetup to make it 
   simpler to read and use. Methods were quite compartmentalized and though
   it was a manager class, there were no issues with adding this new feature
   as each Tab had its own turtleDisplay, with its own set of Turtles.
   
   * To improve, I could make a ButtonManager class that would encapsulate
   button creation and connection to each TurtleDisplay. It would only be dependent
   on one TurtleDisplay and would remove dependencies from GUISetup.
   
   * It would not have been too had I not been familiar with the code because
   the front end is centralized in one class and the finding the right pieces,
   the current TurtleDisplay, would be no problem. 