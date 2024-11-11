# SwiftKeys
## By: Mukund Patil

This application will be a game that can be played by anyone who wants to practice 
their typing skills. The game will provide a sentence, and then will tell the user to retype
 the sentence. Based on the accuracy of the input, the user will gain some amount of points. After time is up, 
the user will have x amount of points, which will be a highscore if it is their best attempt. 
In the graphical interface, I want to create a visual placeholder for the points 
like an object moving closer to the finish, or a bar that shows your progress. 
Currently, the only game mode I have in mind is simply seeing how many points you can get
within the time limit. This idea appealed to me because as a kid, I would be in awe of people that 
could type *incredibly* fast and create **whole essays** in only a few minutes.
So, what better way to test myself then to make my own app to help me improve.


# User stories
- As a user, I want to be able to see the leaderboard.
- As a user, I want to be able to view the players I can choose from.
- As a user, I want to be able to type "done" and end the program.
- As a user, I want to be able to add a player to the roster.

- As a user, I would be able to save the roster after I add players if I want.
- As a user, I would like to be load the roster instead if making a new one if I want.

# Instructions 
- You can click the add player button and input your name in the text field
- You can click remove player button and inputting your name in the text field
- You can save the state of my application by clicking the save button
- You can reload the state of my application by clicking the load button


One big thing I would have liked to refactor is the window names EditLbGUI function. Right now, it
has the app and control instance passed into it as parameters, but ideally it should not be able to see these.
Instead, it should be made with a view model control functionality, like the main body of the program. I would
also not mind refactoring the sentences class as it could be put in the UI class. The advantage of this is it would 
make it both easier to read and easier to understand. Lastly, I wouldn't mind splitting
the Roster class into 2 classes to represent the leaderboard and roster respectively, with leaderboard extending roster. 
The advantage of this is that leaderboard could have its own functionality that isn't needed in roster.


CITATIONS: Data persistence usage and implementation is based of CPSC 210 Workroom example given found at:
github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
