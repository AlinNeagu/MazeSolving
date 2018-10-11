package mazeSolving;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		Image img =new Image("E:\\GITRepos\\MazeSolving\\Maze\\Images\\SimpleMaze.png"); //load maze image
		IPathFinding astar=new AStar();
		Graph graph=new Graph(); 
		MazeBuilder mazeBuilder=new MazeBuilder();	
		
		List<Node> nodes=graph.CreateGraph(img);			 //Generate a list of nodes with neighbors from the image.
		Stack<Node> pathNodes=astar.findPath(nodes); 		 //Find the path to finish .
		img.colorPath(pathNodes);							 //change color of the pixels that are part of the path.
		img.saveImage("E:\\SolvedMaze.png");
		
		 
		Image mazeImage= mazeBuilder.createMaze(10, 10);	 //create a maze image with a given size.
		mazeImage.saveImage("E:\\Maze.png");				
		
		long stoptime = System.currentTimeMillis();
		System.out.println(stoptime-startTime);
	}
	
}
