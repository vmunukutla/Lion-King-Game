import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class hero extends Universe{
	private double speed = 15;
	int lives;
	public hero(){
		super(350,400,50,50,0,0,Color.green);
	}
	public double getSpeed(){
		return speed;
	}
	public void moveAndDraw(Graphics2D win) {
		super.moveAndDraw(win);
		BufferedImage[] slides = this.getImages();
		win.drawImage(slides[0], null, (int)this.getX(), (int)this.getY());
	}
	public boolean checkHealth(){
		return lives <= 0;
	}
	public void loseHealth(){
		lives -= 10;
	}
	public int getHealth(){
		return lives;
	}	
}