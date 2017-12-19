import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class bullet extends Universe{
	public bullet(hero paris){
		super((int)paris.getX() + paris.width/4, (int)paris.getY() , 10, 10, 0, -10, Color.YELLOW);
	}
	public void setImages(BufferedImage[] fun) {
		super.setImages(fun);
		int width = fun[0].getWidth();
		int height = fun[0].getHeight();
		this.setSize(width, height);
	}
	public void moveAndDraw(Graphics2D win) {
		super.moveAndDraw(win);
		BufferedImage[] slides = this.getImages();
		win.drawImage(slides[0], null, (int)this.getX(), (int)this.getY());
	}
}