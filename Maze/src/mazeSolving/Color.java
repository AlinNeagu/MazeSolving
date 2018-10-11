package mazeSolving;

public class Color {
	 int pixel;
	 int alpha;
	 int red;
	 int green;
	 int blue;

	 //Seteaza alfa(transparenta), red(noanta de rosu), green(noanta de verde), blue(noanta de albastru)
	 public void setARGB(int pixel)
	 {
		 alpha=(pixel>>24) & 0xff;
		 red=(pixel>>16) & 0xff;
		 green=(pixel>>8) & 0xff;
		 blue=pixel & 0xff; 
	 }
	 
	 public Color(int alpha,int red,int green,int blue)
	 {
		 this.alpha=alpha;
		 this.blue=blue;
		 this.red=red;
		 this.green=green;
		 this.pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
	 }
	 public Color(int pixel)
	 {
		 this.pixel=pixel;
		 setARGB(pixel);
	 }
	 public Color()
	 {
		 
	 }
	 public boolean Equals(Color color)
	 {
		 if((color.alpha == this.alpha) && (color.red == this.red) && (color.green == this.green) && (color.blue == this.blue))
		 {
			 return true;
		 }
		 return false;
	 }
	 public static Color Black()
	 {
		 Color color=new Color(255,0,0,0);
		 return color;
	 }
	 
	 public static Color White()
	 {
		 Color color=new Color(255,255,255,255);
		 return color;
	 }
	 public static Color Red()
	 {
		 Color color=new Color(255,255,0,0);
		 return color;
	 }
	 
}


