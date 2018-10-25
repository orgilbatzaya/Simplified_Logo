Refactoring Discussion (ral30,ob29,ac497,ak457)
====

## Duplication Refactoring

* We removed some unnecessary classes and methods since we did not delete them after moving their functionality elsewhere. We also refactored the changed() method in the Animate class, where two variables were set equal to the same exact line of code, so that the second variable pointed to the first variable instead.
* In BackMain, there was duplicated code used in multiple cases of if statements that can be simplified into one method that is called
* In CommandStack, there was similar duplicated code that was being called in multiple cases of if statements that was simplified into one method to be called

## Checklist Refactoring

* We simplified some aspects of the stack command processing by using a class StackCommand that processes a smaller portion of commands at a time (mainly calculations and turtle commands) while letting outer control operations like For and If be processed at a higher level when reading in commands.

* We created more ResourceBundle files so that the code is more modular since the code will be taking constant values from the ResourceBundles.

## General Refactoring
* The TurtleView Display method had many magic values within it. During refactoring, all of the magic values were defined on the beginning of the scipt to allow for those values to be easily found and modified.

## Long Methods
* The longest method of our script had was 25 lines long. We could not find a way to shorten this method because it controls animation, nor any other methods that were over 20 lines.