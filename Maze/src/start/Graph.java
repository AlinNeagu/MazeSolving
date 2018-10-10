package start;

import java.util.*;

public class Graph {
	List<Node> nodes = new ArrayList<Node>(); // toate nodurile din imagine

	//This method make from a black and white image a graph .
	//The graph is generated only from the white pixels.
	//Nodes are connected with each other only if they have a straight path through them.
	//
	//Parameters : image - a black and white image (this can be a maze)
	//Return : return a list of nodes . (nodes have set their neighbors)
	public List<Node> CreateGraph(Image image) {
		findNodes(image);
		nodeNeighbours(image);
		return nodes;
	}

	// gaseste nodurile din imagine si le adauga in lista de noduri
	private void findNodes(Image image) {
		startPoint(image); //adauga nodul de start.
		// parcurg imaginea pixel cu pixel
		for (int y = 1; y < image.img.getHeight() - 1; y++)
			for (int x = 1; x < image.img.getWidth() - 1; x++) {
				// adauga nodul gasit in lista
				if (image.checkIfItIsNode(x, y)) {
					Node foundNode = new Node();
					foundNode.setPosition(x, y);
					nodes.add(foundNode);
				}
			}
		finishPoint(image); //adauga nodul de finish.
	}

	// Gaseste punctul de inceput al labirintului si il introduce in lista de noduri
	private  void startPoint(Image image) {
		for (int i = 0; i < image.img.getHeight(); i++) {
			Color color = new Color();
			color.pixel = image.img.getRGB(i, 0);
			color.setARGB(color.pixel);

			if (((color.red == 255) && (color.green == 255) && (color.blue == 255))) {
				Node foundNode = new Node();
				foundNode.setPosition(i, 0);
				nodes.add(foundNode);
			}
		}
	}

	// Gaseste punctul de sfarsit al labirintului si il introduce in lista de noduri
	private  void finishPoint(Image image) {
		for (int i = 0; i < image.img.getHeight(); i++) {
			Color color = new Color();
			color.pixel = image.img.getRGB(i, image.img.getWidth() - 1);
			color.setARGB(color.pixel);

			if ((color.red == 255) && (color.green == 255) && (color.blue == 255)) {
				Node foundNode = new Node();
				foundNode.setPosition(i, image.img.getWidth() - 1);
				nodes.add(foundNode);
			}
		}
	}

	// Gaseste un nod care este in lista si il returneaza ca referinta.
	private Node findNode(int x, int y) {

		for (Node node : nodes) {
			if (node.x == x && node.y == y) {
				return node;
			}
		}
		return null;
	}

	// Verifica daca nodul este vecin si il adauga in lista de vecini
	private boolean checkAndAddIfNeighbour(int x, int y, int i, Image image) {
		Color color = new Color();
		color.pixel = image.img.getRGB(x, y);
		color.setARGB(color.pixel);
		if ((color.alpha == 255) && (color.red == 0) && (color.green == 0) && (color.blue == 0)) {
			return true;
		} else {
			// verificam daca este cumva un nod si daca este il adaugam la vecini
			if (image.checkIfItIsNode(x, y)) {
				Node nodeRefference = findNode(x, y);
				nodes.get(i).addNeighbour(nodeRefference);
				return true;
			}
		}
		return false;
	}

	// Completam lista de vecini a nodurilor
	private void nodeNeighbours(Image image) {
		// parcurgem lista de noduri gasite
		for (int i = 0; i < nodes.size(); i++) {
			int x = nodes.get(i).x;
			int y = nodes.get(i).y;

			// Verificam sa nu iesim din limitele imagini
			while (x > 0) {
				x--;
				if (checkAndAddIfNeighbour(x, y, i, image)) {
					break;
				}
			}
			x = nodes.get(i).x;

			// Verificam sa nu iesim din limitele imagini
			while (x < image.img.getWidth() - 1) {
				x++;
				if (checkAndAddIfNeighbour(x, y, i, image)) {
					break;
				}
			}
			x = nodes.get(i).x;

			// Verificam sa nu iesim din limitele imagini
			while (y > 0) {
				y--;
				if (checkAndAddIfNeighbour(x, y, i, image)) {
					break;
				}
			}
			y = nodes.get(i).y;

			// Verificam sa nu iesim din limitele imagini
			while (y < image.img.getHeight() - 1) {
				y++;
				if (checkAndAddIfNeighbour(x, y, i, image)) {
					break;
				}
			}

		}
	}
}
