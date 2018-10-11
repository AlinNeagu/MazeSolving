package mazeSolving;
import java.util.*;

//A nood can be :
//--- A white pixel which have more than one white pixel on left,right,up or down.
//This class store nodes in order to let you generate a graph .
//Each node have it's current position and a list of neighbors.
public class Node {
	int x;  //Positia  x
	int y;	//Pozitia  y
	public List<Node> neighbours=new ArrayList<Node>();  //Lista de vecini ai nodului.
	
	@Override
	public String toString() {
		String printNodeString="Node [x=" + x + ", y=" + y + ", neighbours=";
		for (Node neighbour : neighbours) {
			printNodeString+="{x="+neighbour.x + ",y="+neighbour.y+"}";
		}
		printNodeString+="]";
		
		return printNodeString;
	}
	
	//Seteaza posittia nodului in imagine 
	public void setPosition(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	//Adauga vecini in lista pentru nod
	public void addNeighbour(Node node) {
		neighbours.add(node);
	}
	
	//Scoate vecini din lista pentru nod
	public void removeNeighbour(Node node) {
		neighbours.remove(node);
	}
	
	// Calculeaza dinstanta dintre nodul curent si nodul dat ca parametru.
	public double getDistance(Node node)
	{
		int x = Math.abs(node.x - this.x);
		int y = Math.abs(node.y - this.y);
		if (x == 0) {
			return y;
		} else if (y == 0) {
			return x;
		} else {
			return Math.sqrt(x * x + y * y);
		}
	}
}
