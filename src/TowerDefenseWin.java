import javax.swing.JFrame;


public class TowerDefenseWin {
	public static void main(String[]args){
		JFrame j1 = new JFrame();
		j1.setSize(800, 730);
		j1.setTitle("Lion King The Game");
		j1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j1.add(new TowerDefense());
		j1.setVisible(true);
	}
}
