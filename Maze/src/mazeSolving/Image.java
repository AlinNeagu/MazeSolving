package mazeSolving;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//TODO : -create a Graph class to store list of graph.nodes and methodes. -done
//		-create an interface called IPathFinding with a method called findPath. -done 
//			-create a class named AStar to store AStar algorithm logic. -done
//			-this class implements the intrface.                       -done

//Delete where you instantiate an object but you dont need that     -done 
//Sort methods in diferent classes                                   -done
//Check what methods must be private and change them
public class Image {
	BufferedImage img = null;

	// citeste imaginea si o tine in memorie.
	public void ReadImage(String imgPath) {

		try {
			File f = new File(imgPath);
			img = ImageIO.read(f);
		}

		catch (IOException e) {
			System.out.println(e);
		}

	}

	public Image(int width, int height) {
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				img.setRGB(x, y, Color.Black().pixel);
			}
	}

	public Image(String imgPath) {
		ReadImage(imgPath);
	}

	// verifica daca pixelul de la coordonatele date este nod.
	public boolean checkIfItIsNode(int x, int y) {
		Color color = new Color();
		color.pixel = img.getRGB(x, y);
		color.setARGB(color.pixel);
		int left = 0;
		int right = 0;
		int up = 0;
		int down = 0;

		// daca pixelul e alb
		if ((color.alpha == 255) && (color.red == 255) && (color.green == 255) && (color.blue == 255)) {

			// daca nodul e pe prima sau ultima linie/coloana atunci e nod de start sau
			// finish.
			if (x == 0 || y == 0 || y == img.getHeight() - 1 || x == img.getWidth() - 1) {
				return true;
			}

			color.pixel = img.getRGB(x, y - 1);
			color.setARGB(color.pixel);

			// Dupa ce gasim un pixeil alb verificam daca si cel din stanga lui e alb
			if ((color.alpha == 255) && (color.red == 255) && (color.green == 255) && (color.blue == 255)) {
				left = 1;
			}

			color.pixel = img.getRGB(x, y + 1);
			color.setARGB(color.pixel);

			// Dupa ce gasim un pixeil alb verificam daca si cel din dreapta lui e alb
			if ((color.alpha == 255) && (color.red == 255) && (color.green == 255) && (color.blue == 255)) {
				right = 1;
			}

			color.pixel = img.getRGB(x - 1, y);
			color.setARGB(color.pixel);

			// Dupa ce gasim un pixeil alb verificam daca si cel de deasupra lui e alb
			if ((color.alpha == 255) && (color.red == 255) && (color.green == 255) && (color.blue == 255)) {
				up = 1;
			}

			color.pixel = img.getRGB(x + 1, y);
			color.setARGB(color.pixel);

			// Dupa ce gasim un pixeil alb verificam daca si cel de sub el e alb
			if ((color.alpha == 255) && (color.red == 255) && (color.green == 255) && (color.blue == 255)) {
				down = 1;
			}

			if (((left == 1) || (right == 1)) && ((up == 1) || (down == 1))) {
				return true;
			}
		}
		return false;
	}

	// Schinba culoarea nodurilor
	public void changeNodeColor(List<Node> nodes) {
		for (int i = 0; i < nodes.size(); i++) {
			img.setRGB(nodes.get(i).x, nodes.get(i).y, Color.Red().pixel);
		}
		// write image
		
	}

	public void saveImage(String path) {
		try {
			File f = new File(path);
			ImageIO.write(img, "png", f);
		}

		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	// In functie de nodurile din stack se traseaza calea labirintului
	public void colorPath(Stack<Node> pathNodes) {
		while (pathNodes.size() > 1) {
			Color color = new Color(255, 255, 0, 0);
			Node curentNode = pathNodes.pop();
			if (curentNode.x == pathNodes.peek().x) {
				if (curentNode.y < pathNodes.peek().y) {
					for (int i = curentNode.y; i <= pathNodes.peek().y; i++) {
						img.setRGB(curentNode.x, i, color.pixel);
					}
				} else {
					for (int i = pathNodes.peek().y; i <= curentNode.y; i++) {
						img.setRGB(curentNode.x, i, color.pixel);
					}
				}
			}
			if (curentNode.y == pathNodes.peek().y) {
				if (curentNode.x < pathNodes.peek().x) {
					for (int i = curentNode.x; i <= pathNodes.peek().x; i++) {
						img.setRGB(i, curentNode.y, color.pixel);
					}
				} else {
					for (int i = pathNodes.peek().x; i <= curentNode.x; i++) {
						img.setRGB(i, curentNode.y, color.pixel);
					}
				}
			}
		}
	}
}
