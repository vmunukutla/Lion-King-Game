import java.awt.Color;


public class towerbullet extends Universe{
	public int timer;
	MovingObjects target;
	public int damage;
	public towerbullet(int x, int y, MovingObjects a, int b){
		super(x,y,10,10,0,0,Color.black);
		int timer = 0;
		target = a;
		damage = b;
	}	
}
