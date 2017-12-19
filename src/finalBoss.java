import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class finalBoss extends MovingObjects{
	public finalBoss(){
		super(5000, 93, 80);
	}
	public void moveAndDraw(Graphics2D win) {
		super.moveAndDraw(win);
		BufferedImage[] slides = this.getImages();
		win.drawImage(slides[0], null, (int)this.getX(), (int)this.getY());
	}
}
