package mazeSolving;

import java.util.*;

public class AStar implements IPathFinding{
	List<NodeHelper> toVisit = new ArrayList<NodeHelper>(); // nodurile ce trebuie vizitate
	List<NodeHelper> visited = new ArrayList<NodeHelper>(); // nodurile vizitate
	Stack<Node> pathNodes = new Stack<Node>();

	
	//Returneaza calea intr-un stack in ordinea care trebuie parcursa.
	public Stack<Node> findPath(List<Node> nodes) {
		findPathInGraph(nodes);
		nodesFromPath();
		return pathNodes;
	}

	// Pune primul nod in lista de noduri ce urmeaza sa fie vizitate
	public void startNode(List<Node> nodes) {
		NodeHelper startNode = new NodeHelper(); // Nodul de start al labiritului
		startNode.curentNode = nodes.get(0);
		startNode.traveled = 0;
		startNode.distance = nodes.get(0).getDistance(nodes.get(nodes.size() - 1));
		startNode.previousNode = null;
		toVisit.add(startNode);
	}

	// Cauta urmatorul obiect care trebuie vizitat in functie de distanta
	public NodeHelper elementToVisit(List<NodeHelper> list) {
		double minimum = list.get(0).distance; // Minimum este valoarea cea mai mica dintre toate distantele pana la
												// ultimul nod
		int index = 0;
		
		//cauta nodul cu distanta cea mai mica.
		for (int i = 1; i < list.size(); i++) {
			// Cauta care nod are distanta cea mai mica
			if (list.get(i).distance < minimum) {
				minimum = list.get(i).distance;
				index = i;
			}
		}
		
		// Returneaza obiectul din lista care area distanta cea mai mica
		return list.get(index);

	}

	// Verifica daca doua noduri sunt la fel . (pe aceeasi pozitie)
	public boolean checkIfIsSameNode(Node node1, Node node2) {
		if ((node1.x == node2.x) && (node1.y == node2.y)) {
			return true;
		} else {
			return false;
		}
	}

	// Gaseste ce-a mai rapida cale de a rezolva labirintul folosind algoritmul A*.
	public void findPathInGraph(List<Node> nodes) {
		startNode(nodes);
		while (toVisit.size() > 0) {
			// Verificam daca nodul care urmeaza sa fie vizitat este sfarsitul labirintului
			if (checkIfIsSameNode(elementToVisit(toVisit).curentNode, nodes.get(nodes.size() - 1))) {
				visited.add(elementToVisit(toVisit));
				break;
			}

			NodeHelper curentVisitedNode = elementToVisit(toVisit);

			for (int i = 0; i < curentVisitedNode.curentNode.neighbours.size(); i++) // Parcurg vecini elementului ce
																						// urmeaza sa fie vizitat
			{
				boolean exists = false;
				for (int j = 0; j < toVisit.size(); j++) // Parcurg lista de elemente ce urmeaza sa fie vizitate
				{
					// Verific daca elementul care urmeaza sa fie adaugat in lista de elemente ce trebuie vizitate exista deja
					if (checkIfIsSameNode(curentVisitedNode.curentNode.neighbours.get(i), toVisit.get(j).curentNode)) {
						exists = true;
					}
				}
				for (int k = 0; k < visited.size(); k++) {
					// Verific daca elementul care urmeaza sa fie adaugat in lista de elemente vizitate exista deja
					if (checkIfIsSameNode(curentVisitedNode.curentNode.neighbours.get(i), visited.get(k).curentNode)) {
						exists = true;
					}
				}
				if (exists == false) {
					NodeHelper nodeNeighbour = new NodeHelper();
					nodeNeighbour.curentNode = curentVisitedNode.curentNode.neighbours.get(i);
					nodeNeighbour.previousNode = curentVisitedNode.curentNode;
					nodeNeighbour.traveled = curentVisitedNode.traveled
							+ nodeNeighbour.previousNode.getDistance(nodeNeighbour.curentNode);
					nodeNeighbour.distance = nodeNeighbour.traveled
							+ nodeNeighbour.curentNode.getDistance(nodes.get(nodes.size() - 1));
					toVisit.add(nodeNeighbour);
				}
			}
			toVisit.remove(curentVisitedNode);
			visited.add(curentVisitedNode);
		}
	}

	//afiseaza nodurile din lista de noduri
	public void printNodes(List<Node> nodes) {
		for (int i = 0; i < nodes.size(); i++)
			System.out.println(nodes.get(i));
	}

	// Adauga nodurile care definesc calea labirintului in stack
	public void nodesFromPath() {

		NodeHelper curentNodeHelper = visited.get(visited.size() - 1);
		while (curentNodeHelper.previousNode != null) {
			for (int i = 0; i <= visited.size() - 1; i++) {
				NodeHelper previousNodeHelper = visited.get(i);
				if (curentNodeHelper.previousNode == previousNodeHelper.curentNode) {
					pathNodes.push(curentNodeHelper.curentNode);
					curentNodeHelper = previousNodeHelper;
					break;
				}
			}
		}

		// Adauga si nodul de unde incepe labirintul in stack-ul pathNodes
		if (curentNodeHelper.previousNode == null) {
			pathNodes.push(curentNodeHelper.curentNode);
		}
	}
	
	private class NodeHelper {
		Node curentNode=new Node();
		double traveled;  // Distanta parcursa
		double distance;	// Suma dintre distanta de la nodul curent la ultimul nod si distanta parcursa
		Node previousNode=new Node();
	}

}


