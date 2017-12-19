
public class Location {
	public int x;
	public int y;
	public boolean isTower1;
	public boolean isTower2;
	public boolean isTower3;
	public Location (int a, int b){
		x = a;
		y = b;
		isTower2 = false;
		isTower3 = false;
		isTower1 = false;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
