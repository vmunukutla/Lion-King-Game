import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public abstract class Universe extends Rectangle{
	BufferedImage[] img;
	public double dx, dy;
	private Color col;
	public Universe(int x, int y, int width, int height, double dx, double dy, Color c){
		super(x,y,width,height);
		this.dx = dx;
		this.dy = dy;
		col = c;
	}
	public void setImages(BufferedImage[]add){
		img = add;
	}
	public BufferedImage[] getImages(){
		return img;
	}
	public void setDx(double value){
		this.dx = value;
	}
	public void setDy(double value){
		this.dy = value;
	}
	public double getDx(){
		return dx;
	}
	public double getDy(){
		return dy;
	}
	public double getX(){
		return x;
	}
	public void setX(int newX){
		x = newX;
	}
	public double getY(){
		return y;
	}
	public void moveAndDraw(Graphics2D win){
		this.translate((int)dx, (int)dy);
		if(img == null){
			win.setColor(col);
			win.fill(this);
		}	
	}
}

