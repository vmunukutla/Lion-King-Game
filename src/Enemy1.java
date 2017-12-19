import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Enemy1 extends MovingObjects{
	public Enemy1(){
		super(130, 44, 38);
	}
	public void moveAndDraw(Graphics2D win) {
		super.moveAndDraw(win);
		BufferedImage[] slides = this.getImages();
		win.drawImage(slides[0], null, (int)this.getX(), (int)this.getY());
	}
}
