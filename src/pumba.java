import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class pumba extends MovingObjects{
	public pumba(){
		super(5000, 122, 114);
	}
	public void moveAndDraw(Graphics2D win) {
		super.moveAndDraw(win);
		BufferedImage[] slides = this.getImages();
		win.drawImage(slides[0], null, (int)this.getX(), (int)this.getY());
	}
}

