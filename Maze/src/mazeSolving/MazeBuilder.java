package mazeSolving;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//This class store methods to randomly build a maze.
public class MazeBuilder {

	private Image image; //image where the maze will be stored.
	
	private int left=0;
	private int right=0;
	private int down=0;
	private int up=0;
	
	private int x; 
	private int y;
	
	private int xFinish;  //X-ul punctului de sfarsit al labirintului
	private List <Pixel> pixels=new ArrayList<Pixel>();
	
	// Alege un numar cumprins intre un minim si un maxim(inclusiv) si il returneaza
	private int randomInt(int minValue,int maxValue)
	{
	    Random random=new Random();
		return random.nextInt((maxValue-minValue) + 1) + minValue;
	}
	
	//Metoda folosita pentru a alege in ce direntie va merge calea labirintului 
	private void setRandomDirection(int minValue,int maxValue,Image image)
	{
		int randomInt=randomInt(minValue,maxValue);
		if((randomInt>0)&&(randomInt<=40))
		{
			x--;
			image.img.setRGB(x,y, Color.White().pixel);
		}
		if((randomInt>40)&&(randomInt<=60))
		{
			y++;
			image.img.setRGB(x,y, Color.White().pixel);
		}
		if((randomInt>60)&&(randomInt<=100))
		{
			x++;
			image.img.setRGB(x,y, Color.White().pixel);
		}
		if((randomInt>100)&&(randomInt<=120))
		{
			y--;
			image.img.setRGB(x,y, Color.White().pixel);
		}
		
	}
	
	
	//Seteaza primele 2 puncte de inceput al labirintului
	private void firstTwoPoints(Image image)
	{
		int xStart=randomInt(1, image.img.getWidth()-2);
		image.img.setRGB(xStart,0, Color.White().pixel);
		addPixels(xStart,0);
		image.img.setRGB(xStart,1, Color.White().pixel);
		addPixels(xStart,1);
	}
	
	//Seteaza punctul in care se trmina labirintul
	private void finishPoint(Image image)
	{
		xFinish=randomInt(1, image.img.getWidth()-2);
		image.img.setRGB(xFinish, image.img.getHeight()-1, Color.White().pixel);
	}
	
	//Seteaza pozitiile pixelilor si ii adauga in lista
	private void addPixels(int x, int y)
	{
		Pixel pixel=new Pixel();
		pixel.setPosition(x, y);
		pixels.add(pixel);
	}
	
	//Se verifica in ce directii se pot merge
	private void checkDirections(int x,int y,Image image)
	{
		up=0;
		down=0;
		left=0;
		right=0;
		//se verifica daca se poate merge in stanga
		if(new Color(image.img.getRGB(x-1, y)).Equals(Color.Black())&&(new Color(image.img.getRGB(x-1, y-1)).Equals(Color.Black()))&&(new Color(image.img.getRGB(x-1, y+1)).Equals(Color.Black())))
		{
			left=1;
		}
		//se verifica daca se poate merge in dreapta
		if(new Color(image.img.getRGB(x+1, y)).Equals(Color.Black())&&(new Color(image.img.getRGB(x+1, y-1)).Equals(Color.Black()))&&(new Color(image.img.getRGB(x+1, y+1)).Equals(Color.Black())))
		{
			right=1;
		}
		//se verifica daca se poate merge in sus
		if(new Color(image.img.getRGB(x, y-1)).Equals(Color.Black())&&(new Color(image.img.getRGB(x-1, y-1)).Equals(Color.Black()))&&(new Color(image.img.getRGB(x+1, y-1)).Equals(Color.Black())))
		{
			up=1;
		}
		//se verifica daca se poate merge in jos
		if(new Color(image.img.getRGB(x, y+1)).Equals(Color.Black())&&(new Color(image.img.getRGB(x-1, y+1)).Equals(Color.Black()))&&(new Color(image.img.getRGB(x+1, y+1)).Equals(Color.Black())))
		{
			down=1;
		}
	}
	
	//Creaza path-ul de la inceput la sfarsit al labirintului
	private void createPath()
	{
		firstTwoPoints(image);
		finishPoint(image);
		y=1;
		for(int i=1;i<image.img.getWidth()-1;i++)
		{
			
			if (new Color(image.img.getRGB(i, y)).Equals(Color.White()))
			{
				x=i;
				break;
			}
		}
		
		while(y<image.img.getHeight()-3)
		{
			checkDirections(x, y, image);
			//daca se ajunge in limita din stanga
			if(x==1)
			{
				//daca se poate merge in dreapta
				if (right==1)
				{
					setRandomDirection(41, 100,image); //merge in dreapta sau jos
					addPixels(x, y);
				}
				else
				{
					setRandomDirection(41, 60,image);  //merge in dreapta sau jos
					addPixels(x, y);
				}
				
			}
			//daca se ajunge la limita din dreapta
			else if(x==image.img.getWidth()-2)
			{
				//daca pixelul din stanga e alb
				if (left==1)
				{
					setRandomDirection(1, 60,image);  //merge in stanga sau in jos
					addPixels(x, y);
				}
				else
				{
					setRandomDirection(41, 60,image);  //merge in jos
					addPixels(x, y);
					
				}
			}
			else
			{
				if((left==1)&&(right==1))
				{
					setRandomDirection(1,100,image);  //merge in toate directiile
					addPixels(x, y);
				}
				if((left==1)&&(right==0))
				{
					setRandomDirection(1,60,image);  //merge in stanga sau jos
					addPixels(x, y);
				}
				if((left==0)&&(right==1))
				{
					setRandomDirection(41,100,image); //merge in dreapta sau jos
					addPixels(x, y);
				}
				if((left==0)&&(right==0))
				{
					setRandomDirection(41,60,image); //merge in jos
					addPixels(x, y);
				}

			}
		}
		
		if(y==image.img.getHeight()-3)
		{
			setRandomDirection(41, 60, image); //merge in jos
			addPixels(x, y);
		}
		
		//Verificam daca suntem deaspupta punctului de finish cu un pixel si daca suntem ne indreptam catre acesta
		if(y==image.img.getHeight()-2)
		{
			while(x!=xFinish)
			{
				if(x<xFinish)
				{
					setRandomDirection(61,100,image);
					addPixels(x, y);
				}
				if(x>xFinish)
				{
					setRandomDirection(1,40,image);
					addPixels(x, y);
				}
			}
		}
		addPixels(xFinish, image.img.getHeight()-1);	 //Adauga si ultimul pixel in lista			
	}
	
	//Create a maze with a certain width and height.
	//Parameters : width - maze width
	//			   height - maze height
	//Return : Image of the maze .
	public Image createMaze(int width,int height) {
		image=new Image(width, height);
		createPath();
		createMazeBranches();	
		return image;
	}
	
	//Create maze branches that derive from the path that exists betwen start and finish point.
	private void createMazeBranches()
	{
		int randomInt;
		for(int i=1;i<pixels.size()-1;i=i+1)
		{
			x=pixels.get(i).x;
			y=pixels.get(i).y;
			
					
			while((x>1)&&(x<image.img.getWidth()-2)&&(y>1)&&(y<image.img.getHeight()-2))
			{
				randomInt=randomInt(1,130);
				checkDirections(x, y, image);
				
				//daca se poate merge in stanga si daca numarul este intre 0 si 40 
				if((left==1)&&(randomInt>0)&&(randomInt<=40))
				{
					setRandomDirection(randomInt, randomInt, image);
				}
				//daca se poate merge in jos si daca numarul este intre 41 si 60 
				else if((down==1)&&(randomInt>40)&&(randomInt<=60))
				{
					setRandomDirection(randomInt, randomInt, image);
				}
				//daca se poate merge in dreapta si daca numarul este intre 61 si 100 
				else if((right==1)&&(randomInt>60)&&(randomInt<=100))
				{
					setRandomDirection(randomInt, randomInt, image);
				}
				//daca se poate merge in sus si daca numarul este intre 101 si 120 
				else if((up==1)&&(randomInt>100)&&(randomInt<=120))
				{
					setRandomDirection(randomInt, randomInt, image);
				}
				else if(right==0 && left ==0 && up==0 && down==0 || randomInt>120)
				{
					break;
				}
			}
		}
	}
}

