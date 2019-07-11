package mazeSolving;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		
		if(args.length<2)
		{
			System.out.println("### You need to enter at least 2 arguments! ###");
			arguments();
			return;
		}
		
		if(args.length==2)
		{
			
			Image img=new Image(args[0]);
			IPathFinding astar=new AStar();
			Graph graph=new Graph(); 
			List<Node> nodes=null;
			
			try {
				nodes=graph.CreateGraph(img);
			}
			catch(Exception e){
				System.out.println("### The first path you entered is not valid or there is no image at that path ###");
				arguments();
				return;
			}
			
			Stack<Node> pathNodes=astar.findPath(nodes);
			img.colorPath(pathNodes);
			
			try {
				img.saveImage(args[1]+"\\SolvedMaze.png");
			}	
			catch(Exception e){
				System.out.println("### The second path you entered is not valid ###");
				arguments();
				return;
			}
		}
		
		if(args.length==3)
		{

			Image img=null; //load maze image
			IPathFinding astar=new AStar();
			Graph graph=new Graph(); 
			MazeBuilder mazeBuilder=new MazeBuilder();	
			try {
				img= mazeBuilder.createMaze(Integer.parseInt(args[1]),Integer.parseInt(args[2]));	
			}
			catch(Exception e) {
				System.out.println("### The width and the height of the image but be integer numbers ###");
				arguments();
				return;
			}
			
			try {
				img.saveImage(args[0]+"\\Maze.png");
			}
			catch(Exception e) {
				System.out.println("### The path you entered is not valid ###");
				arguments();
				return;
			}
			
			List<Node> nodes=graph.CreateGraph(img);
			Stack<Node> pathNodes=astar.findPath(nodes);
			img.colorPath(pathNodes);
			
			try {
				img.saveImage(args[0]+"\\SolvedMaze.png");
			}
			catch(Exception e) {
				System.out.println("### The path you entered is not valid ###");
				arguments();
				return;
			}
			
		}
		
	/*	long startTime = System.currentTimeMillis();

		Image img; //load maze image
		IPathFinding astar=new AStar();
		Graph graph=new Graph(); 
		MazeBuilder mazeBuilder=new MazeBuilder();		
		 
		img= mazeBuilder.createMaze(23,23);	 //create a maze image with a given size.
		img.saveImage("C:\\Users\\alin\\Desktop\\Maze.png");	
		
		List<Node> nodes=graph.CreateGraph(img);			 //Generate a list of nodes with neighbors from the image.
		Stack<Node> pathNodes=astar.findPath(nodes); 		 //Find the path to finish .
		img.colorPath(pathNodes);							 //change color of the pixels that are part of the path.
		img.saveImage("C:\\Users\\alin\\Desktop\\SolvedMaze.png");
		
		long stoptime = System.currentTimeMillis();
		System.out.println(stoptime-startTime);*/
	}
	
	public static void arguments(){
		System.out.println("# If you want to load your own maze image the application will solve you must insert the following arguments"); 
		System.out.println("## The path of the maze image you want to solve");
		System.out.println("## The path where the solved maze image will be placed");
		System.out.println("### Exemple:java mazeSolving.Main C:\\Users\\Desktop\\Maze.png C:\\Users\\Desktop");
		System.out.println("# If you want the aplication to generate a maze image for you and then solve it you must insert the following arguments");
		System.out.println("## The path where the created maze image and solved maze image will be placed");
		System.out.println("## The Width of the image");
		System.out.println("## The Height of the image");	
		System.out.println("### Exemple:java mazeSolving.Main C:\\Users\\Desktop 30 30");
	}
	
}
