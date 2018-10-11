package mazeSolving;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		
		Image img; //load maze image
		IPathFinding astar=new AStar();
		Graph graph=new Graph(); 
		MazeBuilder mazeBuilder=new MazeBuilder();		
		 
		img= mazeBuilder.createMaze(6,200);	 //create a maze image with a given size.
		img.saveImage("C:\\Users\\Soul.Soul-PC\\Desktop\\Maze.png");	
		
		List<Node> nodes=graph.CreateGraph(img);			 //Generate a list of nodes with neighbors from the image.
		Stack<Node> pathNodes=astar.findPath(nodes); 		 //Find the path to finish .
		img.colorPath(pathNodes);							 //change color of the pixels that are part of the path.
		img.saveImage("C:\\Users\\Soul.Soul-PC\\Desktop\\SolvedMaze.png");
		
		long stoptime = System.currentTimeMillis();
		System.out.println(stoptime-startTime);
	}
	
}
