# Java Maze Game
Java maze game that uses client/server communication to solve any maze with an AI mouse.
Written by Richard Chase Godwin and Nick Wilson for CSE 1302J.

# Instructions:

1. Clone repository and open in NetBeans IDE.
2. Ensure a 'maze.txt' file exists in the project root directory. Custom mazes can be created as described below.
3. First run the 'Server.java' file to start the GUI and server.
4. When ready, run the 'Client.java' file to connect the mouse and begin solving the maze.

# Custom Maze Creation

Mazes must be created using lower case letters and saved in a 'maze.txt' file placed in project directory.
The following letters are used when creating a maze:
'r' = rat (there must be one of these outside of the entrance to the maze)
'p' = path (a pathway that the rat can follow, should connect to the exit in some manner)
'o' = outside (the area outside of the maze walls)
'w' = wall (the walls of the maze that surround the path)

# Logging

ClientLog.txt contains the commands the client sends to the server.
ServerLog.txt contains the commands sent/recieved to and from the client.
MouseLog.txt contains the directions that the mouse moves during play.