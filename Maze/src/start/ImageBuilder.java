package start;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageBuilder {
	
	public void createImage(int width,int height)
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		File f = null;
		 for(int y = 0; y < height; y++)
		       for(int x = 0; x < width; x++) {	    	     
		             img.setRGB(x, y, Color.Black().pixel);
		           }
		         //write image
		         try{
		           f = new File("C:\\\\Users\\\\Soul.Soul-PC\\\\Desktop\\\\Output2.png");
		           ImageIO.write(img, "png", f);
		         }catch(IOException e){
		           System.out.println("Error: " + e);
		         }
		       
	}
	public void PrintMazeInImage(Image image)
	{
		MazeBuilder mazeBuilder=new MazeBuilder();
		mazeBuilder.makePath(image);
		mazeBuilder.createMaze(image);
		try{
	           image.f = new File("C:\\\\Users\\\\Soul.Soul-PC\\\\Desktop\\\\Output2.png");
	           ImageIO.write(image.img, "png", image.f);
	         }catch(IOException e){
	           System.out.println("Error: " + e);
	         }
	}
}
