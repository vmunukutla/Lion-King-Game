import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class MovingObjects extends Universe {
	public int health;	
	public int randomLocation = (int)(Math.random()*61) - 30;
	public boolean hasTurned1 = false;
	public boolean hasTurned2 = false;
	public boolean hasTurned3 = false;
	public boolean hasTurned4 = false;
	public boolean hasTurned5 = false;
	public boolean hasTurned6 = false;
	public boolean hasTurned7 = false;
	public int moveSpeed = 5;
	public MovingObjects(int x,int a, int b){
		super(25,0,a,b,0,5,Color.black);
		health = x;
		super.setX(25+this.randomLocation);
	}
	public void setMoveSpeed(int newSpeed){
		moveSpeed = newSpeed;
	}
	public void moveNorth(){
		dy = moveSpeed * -1;
		dx =0;
	}
	public void moveSouth(){
		dy = moveSpeed;
		dx = 0;
	}
	public void moveEast(){
		dy = 0;
		dx = moveSpeed;
	}
	public void moveWest(){
		dy = 0;
		dx = moveSpeed * -1;
	}
	public void decreaseHealth(int x){
			health -= x;
	}
	public void setHealth(){
		health = 90;
	}
	//returns true when object is dead
	public boolean checkHealth(){
		return health <= 0;
	}
}
