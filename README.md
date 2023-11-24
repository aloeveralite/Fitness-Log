# My Personal Project

## Personal Gym Log

### What will the application do?

The application will allow users to record and keep track of various gym exercises containing
information on the target muscle they target, the type of exercise it is (e.g., weight or calisthenics), the amount of
weight (if type weight), and the number of sets and repetition the users prefer. Functionalities to the application
include being able to categorize groups of exercises based on the muscles they target and track progress on the 
increase of weights, sets, or repetitions.

### Who will use it?

The application is open to anyone who is looking to start getting fit in the gym, or even for experienced gym goers who
are looking to record their progress. It will be useful because it conveniently allows people who want to work out to
keep track of what exercises they should do and see their progress over time.

### Why is this project of interest to you?

This project is of interest to me because going to the gym and working out is a hobby of mine. Moreover, I believe
in personal fitness and health and how it improves quality of life both mentally and physically, allowing people to 
grow, improve, and participate in a challenging, yet rewarding endeavour. Due to the growing number of people who are
abstaining from personal fitness, I reckon that increasing the convenience with the process of working out will allow
that abstinence to change for the betterment of quality of life.


### User Stories:
- As a user, I want to be able to **add** an exercise to a _list_ of exercises.
- As a user, I want to be able to **remove** an exercise from a _list_ of exercises.
- As a user, I want to be able to **view** a _list_ of all the exercises.
- As a user, I want to be able to know how many exercises I have recorded.
- As a user, I want to be able to select an exercise and view it in detail.
- As a user, I want to be able to calculate my weight progress on exercises that have been recorded multiple times.
- As a user, I want to be able to make and **view** a _list_ of exercises tailoring to certain muscle groups.
- As a user, I want to be able to know which exercise I've recorded uses the heaviest weight.
- As a user, I want to be able to save all the gym exercises I've recorded
- As a user, I want to be able to reload all the gym exercises I've previously added before

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by first, in the 
'Main' tab of the window, write descriptions to the text fields of name and target (text fields for weight, sets, and
repetition are optional). Then, click the buttons beside each text field to enter the descriptions (e.g., the button for
name will read 'Enter' and have a label that reads 'Name of the exercise:'). Finally, click the last button that
reads 'Add new exercise'. This can be done an arbitrary number of times, but an exercise will not be added if there is
no description entered for the name and target.
- You can generate the second required action related to the user story "select and view details of an X from a Y" by
first, click the 'Exercises' tab of the window on the right hand side of the window. Then, click the button reading 
'All exercises'. Inside a box you will see all available exercises to select, and for this to work, there must be
at least one exercise added prior. Then click the button reading 'Select an exercise' and you will be prompted a
dialogue to enter the index of the exercise from which you saw in order after viewing all your exercises inside the 
box. After entering your choice, there is a label next to the button 'Select an exercise' that will read out and let 
you view the details of the exercise you selected.
- You can locate my visual component by clicking the button 'Add new exercise' (after successfully adding an exercise
with a name and target entered prior), or by clicking the button 'Save current exercise(s)', or by clicking the button 
'Load previous exercise(s)'. These buttons are all in the 'Main' tab.
- You can save the state of my application by clicking the button 'Save current exercise(s)' in the 'Main' tab.
- You can reload the state of my application by clicking the button 'Load previous exercise(s)' in the 'Main' tab.