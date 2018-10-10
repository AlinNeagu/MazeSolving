package start;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Image img =new Image("C:\\Users\\Soul.Soul-PC\\Desktop\\Desktop\\Work\\LabirintAI\\Output2.png");
		Graph graph=new Graph();
		List<Node> nodes=graph.CreateGraph(img);
		//img.changeNodeColor(nodes);
		
		IPathFinding astar=new AStar();
		Stack<Node> pathNodes=astar.findPath(nodes);
		img.colorPath(pathNodes);
		
		ImageBuilder imageBuilder=new ImageBuilder();
		imageBuilder.createImage(80, 80);
		img=new Image("C:\\\\Users\\\\Soul.Soul-PC\\\\Desktop\\\\Output2.png");
		imageBuilder.PrintMazeInImage(img);
	}

}
