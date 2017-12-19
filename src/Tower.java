import java.awt.Color;
import java.awt.Graphics2D;


public class Tower extends Universe{
	int fireRate;
	int radius;
	int damage;
	int shootTimer;
	int cost;
	public String towerNumber;
	public Tower(int x, int y, int z, int a, String b, int xLocation, int yLocation){
		super(xLocation, yLocation, 15, 15, 0, 0, Color.black);
		fireRate = x;
		radius = y;
		damage = z;
		shootTimer = 0;
		cost = a;
		towerNumber = b;
	}
	public int getDamage(){
		return damage;
	}
	public int getCost(){
		return cost;
	}
}