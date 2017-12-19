import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class tucan extends MovingObjects{
	public tucan(){
		super(500, 92, 90);
	}
	public void moveAndDraw(Graphics2D win) {
		super.moveAndDraw(win);
		BufferedImage[] slides = this.getImages();
		win.drawImage(slides[0], null, (int)this.getX(), (int)this.getY());
	}
}
