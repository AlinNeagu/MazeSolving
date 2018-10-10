package start;
import java.util.*;

public class Node {
	int x;  //Positia de x
	int y;	//Pozitia pe y
	public List<Node> neighbours=new ArrayList<Node>();  //Lista de vecini ai fiecarui nod
	
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
	
	// Calculeaza dinstanta dintre oricare 2 noduri(nodul curent si un alt nod dat ca paramentru pentru functie)
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
