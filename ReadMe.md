# Maze Solving application build instructions:  
  
Things you need to download and instal:  
-Clone or download the project.  
-Download and instal Java SE Runetime Environment - use this link: https://www.java.com/en/download/  
  
Running the application:  
-Open Command Prompt and go where download project is located.    
-Use "java" command followed by "-jar", the executable jar ("MazeSolving") and arguments to run the application.  

How to add arguments:  
-If you want to load your own maze image the application will solve you must insert the following arguments   
--The path of the maze image you want to solve  
--The path where the solved maze image will be placed  
Exemple: "java -jar MazeSolving.jar C:\\Users\\Desktop\\Maze.png C:\\Users\\Desktop"  
-If you want the aplication to generate a maze image for you and then solve it you must insert the following arguments  
--The path where the created maze image and solved maze image will be placed
--The Width of the image  
--The Height of the image	  
Exemple: "java -jar MazeSolving.jar C:\\Users\\Desktop 30 30"  
