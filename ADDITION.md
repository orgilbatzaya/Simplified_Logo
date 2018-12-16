**How long do you think it will take you to complete this new feature?**

I believe it will take me one or two hours to complete this new feature.

**How many files will you need to add or update? Why?**

I believe I will need to add two files and modify two or three other files to implement this feature.
This is because I will be using a flow pane, which none of our previous code has, and since we had been creating abstract classes for each GUI element type, I plan on following that design.
The modifications to the other files will be needed to properly display the flow pane as well as the right turtles, but most of the support needed for this feature already exists.

**How long did it take you to complete this new feature?**

I took about an hour to complete this new feature.

**How many files did you need to add or update? Why?**

I needed to add and update 4 files. The two files I added defined the flow pane and what functionality it contains.
The two files I modified were the GUISetup class, which constructs the flow pane and displays it, and the TurtleView class, which needed another method in order to implement the feature correctly.

**Did you get it completely right on the first try?**

I did not get it completely right on the first try because my program would not display the same ImageView in two different places.

**What do you feel this exercise reveals about your project's design and documentation?**

This exercise reveals that my project's design is encapsulated well, but the documentation is lacking.
Most classes only have a few comments about the purpose of the class.
However, given that I only needed to modify two existing classes to get the feature working, the classes are probably well encapsulated and are flexible enough to allow for additional extensions.

**Was it as good (or bad) as you remembered?**

It was worse than as I remembered because as I did the refactoring, I realized there were better ways to implement the code in certain classes.
For example, the TurtleView class did not extend ImageView even though it represented the image of a turtle.
I could have finished in less than an hour if our code had been better implemented.

**What could be improved?**

There could be improvements in the abstraction of some methods, since I found myself writing similar code in some classes, mostly with changing the image in an ImageView.

**What would it have been like if you were not familiar with the code at all?**

If I was not familiar with the code at all, trying to implement this feature would be slightly time consuming.
There is little documentation, so I would have to read a lot of code to figure out what is going on.
However, because our design already implements multiple turtles and multiple panes, it is not hard to extract the information needed to implement this feature correctly.