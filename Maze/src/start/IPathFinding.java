package start;

import java.util.*;

//Interface for path findings alghoritms
public interface IPathFinding {

	//This method must be implemented to find the path through a graph .
	//Parameters : - "nodes" is a list with all the graph nodes ... all nodes must have neighbors.
	//Return : This method must return a stack with all the nodes that form the path to the end. 
	//		   If no path is found an empty Stack object will be returned.
	public Stack<Node> findPath(List<Node> nodes);
}
