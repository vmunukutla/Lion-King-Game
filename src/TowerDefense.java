import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class TowerDefense extends GameDriver {
	Rectangle territory;
	hero kevin;
	int score;
	int highScore;
	int numEnemies = 8;
	int d = 55;
	Rectangle r1;
	Font scoreFont;
	boolean onCoolDown = false;
	int eggDelay = 8;
	int eggTimer = eggDelay;
	int money;
	ArrayList<MovingObjects> enemies = new ArrayList<MovingObjects>();
	public Tower[] defenses = new Tower[6];
	ArrayList<towerbullet> tbullet = new ArrayList<towerbullet>();
	Location[] towerSpaces;
	ArrayList<bullet> basket;
	BufferedImage[] imgHyena;
	BufferedImage[] imgSimba;
	BufferedImage[] imgTimon;
	BufferedImage[] imgScar;
	BufferedImage[] imgPumba;
	BufferedImage[] imgTucan;
	String[] soundList = {"circleoflife.wav", "todiefor.wav", "hakunamatata.wav"};
	SoundDriver soundDriver = new SoundDriver(soundList);
	int timer;
	int waveNumber;
	int lives;
	int gameState;
	int enemyTimer = 10;
	int enemySent = 0;
	int towerSelected = 0;
	int currentLocation = 0;
	boolean towerPurchaseCheck = false;
	boolean locationChange = true;
	BufferedImage back = this.addImage("priderock.png");
	boolean bossSent = false;
	boolean tucanSent = false;
	boolean pumbaSent = false;

	public TowerDefense() {
		this.addKeyListener(this);
		r1 = new Rectangle(0, 0, -500, 600);
		scoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 42);
		soundDriver.loop(0);
		waveNumber = 1;
		kevin = new hero();
		lives = 3;
		gameState = 2;
		this.FramesPerSecond = 35;
		timer = 10;
		towerSpaces = new Location[6];
		towerSpaces[0] = new Location(100, 100);
		towerSpaces[1] = new Location(500, 200);
		towerSpaces[2] = new Location(300, 400);
		towerSpaces[3] = new Location(100, 500);
		towerSpaces[4] = new Location(100, 300);
		towerSpaces[5] = new Location(500, 500);
		reset();
	}

	public void reset() {
		score = 0;
		money = 50;
		enemySent = 0;
		timer = 10;
		kevin = new hero();
		enemies = new ArrayList<MovingObjects>();
		basket = new ArrayList<bullet>();
		this.loadImages();
		lives = 3;
		waveNumber = 1;
		for (int i = 0; i < 6; i++) {
			towerSpaces[i].isTower1 = false;
		}
		currentLocation = 0;
		defenses = new Tower[6];
		towerSelected = 0;
		towerPurchaseCheck = false;
		bossSent = false;
		pumbaSent = false;
		tucanSent = false;
	}

	public void loadImages() {
		imgHyena = new BufferedImage[1];
		imgSimba = new BufferedImage[1];
		imgTimon = new BufferedImage[1];
		imgScar = new BufferedImage[1];
		imgPumba = new BufferedImage[1];
		imgTucan = new BufferedImage[1];
		imgHyena[0] = this.addImage("hyena.png");
		imgSimba[0] = this.addImage("simba.png");
		kevin.setImages(imgSimba);
		imgTimon[0] = this.addImage("imageedit_2_3785184279.png");
		imgScar[0] = this.addImage("imageedit_1_6514816015.png");
		imgPumba[0] = this.addImage("imageedit_5_9776841554.png");
		imgTucan[0] = this.addImage("imageedit_5_5400907214.png");
	}

	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		if (waveNumber < 16 && lives == 0) {
			gameState = 0;
		}
		if(waveNumber == 16){
			gameState = 7;
		}
		// start page
		if (gameState == 2) {
			win.drawImage(this.addImage("priderock.png"), null, 0, 0);
			win.setColor(Color.orange);
			win.setFont(scoreFont);
			String open = "Lion King The Game";
			win.drawString(open, 185, 60);
			win.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
			String names = "Vikas Munukutla and Kevin Tsao (Team 214)";
			win.drawString(names, 280, 680);
			String directions = "Objective (Press 1)";
			win.setColor(Color.LIGHT_GRAY);
			win.setFont(scoreFont);
			win.drawString(directions, 203, 230 + d);
			String howtoplay = "How to Play (Press 2)";
			win.drawString(howtoplay, 180, 285 + d);
			String play = "Press Enter to Play!!";
			win.drawString(play, 185, 340 + d);
		}
		// instructions page
		if (gameState == 5) {
			win.drawImage(this.addImage("jungle.png"), null, 0, 0);
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 24));
			win.setColor(Color.white);
			String objective7 = "Press J, K, I, or L to repeatedly throw bugs at the hyenas.";
			win.drawString(objective7, 25, 100);
			String objective10 = "If needed the assistance of Rafiki, simply press 1 and select";
			String objective11 = "a location to place him.";
			String objective12 = "Press 1, 2,  or 3 with a sufficient amount of hakunas to summon";
			String objective13 = "Rafiki's hidden energy and obliterate the puny hyenas.";
			String objective8 = "Don't let 3 or more hyenas escape into Pride Rock or else";
			win.drawString(objective10, 25, 300);
			String objective9 = "Mufasa shall reign and you will lose!!!";
			String objective14 = "Gain hakunas by killing hyenas. Hyenas have 4 lives each.";
			win.drawString(objective14, 25, 200);
			win.drawString(objective11, 25, 355);
			win.drawString(objective12, 25, 455);
			win.drawString(objective13, 25, 510);
			win.drawString(objective8, 25, 610);
			win.drawString(objective9, 25, 665);
			String back = "Press Z to go back";
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
			win.drawString(back, 550, 680);
		}
		// instructions page (continued)
		if (gameState == 6) {
			win.drawImage(this.addImage("back.png"), null, 0, 0);
			win.drawImage(this.addImage("rafikiyeas.png"), null, 100, 400);
			win.drawImage(this.addImage("rafikiyeas.png"), null, 350, 400);
			win.drawImage(this.addImage("rafikiyeas.png"), null, 600, 400);
			win.setColor(Color.black);
			win.drawString("1", 100, 420);
			win.drawString("2", 350, 420);
			win.drawString("3", 600, 420);
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
			win.drawString("Base tower", 100, 530);
			win.drawString("Tier 2 tower", 350, 530);
			win.drawString("Tier 3 tower", 600, 530);
			win.drawString(
					"***Reminder: A base tower must be purchased before upgrading.",
					50, 130);
			win.drawString(
					"- A base tower has a medium fire rate, medium fire radius, and medium damage.",
					50, 200);
			win.drawString(
					"- A Tier 2 tower has a high fire rate, medium fire radius, and medium damage.",
					50, 250);
			win.drawString(
					"- A Tier 3 tower has a lower fire rate, large fire radius, and high damage.",
					50, 300);
			win.drawString(
					"- Each tower tier costs 50 hakunas. Distribute and upgrade towers wisely!",
					50, 350);
			win.setFont(scoreFont);
			win.drawString("Rafiki Towers", 280, 70);
			String back = "Press Z to go back";
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
			win.drawString(back, 25, 680);
		}
		// winning page
		if (gameState == 7) {
			win.drawImage(this.addImage("imageedit_2_8167387105.png"), null, 0,
					0);
			win.setFont(scoreFont);
			win.setColor(Color.black);
			win.drawString("You won!!!", 250, 175);
			win.drawString("Wave Number: " + waveNumber, 250, 250);
			win.drawString("Score: " + score, 250, 325);
			win.drawString("Press ENTER!", 250, 400);
		}
		// objective page
		if (gameState == 3) {
			win.drawImage(this.addImage("rafiki.png"), null, 0, 0);
			win.setColor(Color.white);
			win.setFont(scoreFont);
			String title = "Objective";
			win.drawString(title, 300, 60);
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 38));
			String objective1 = "Simba must protect Pride Rock from";
			win.drawString(objective1, 25, 120 + 2 * d);
			String objective2 = "Scar's hyenas and save the kingdom.";
			win.drawString(objective2, 25, 160 + 2 * d);
			String objective3 = "Lost in the elephant graveyard, Simba";
			win.drawString(objective3, 25, 200 + 2 * d);
			String objective4 = "must fight to save himself and prevent";
			win.drawString(objective4, 25, 240 + 2 * d);
			String objective5 = "the hyenas from entering Pride Rock. Can";
			win.drawString(objective5, 25, 280 + 2 * d);
			String objective6 = "you help him save Pride Rock?";
			win.drawString(objective6, 25, 320 + 2 * d);
			String back = "Press right arrow key to continue";
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
			win.drawString(back, 450, 680);
		}
		// how-to-play page
		if (gameState == 4) {
			win.drawImage(this.addImage("imageedit_5_9727774567.png"), null, 0,
					0);
			win.setColor(Color.black);
			win.setFont(scoreFont);
			String title = "Key Mapping";
			win.drawString(title, 280, 60);
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 38));
			String objective1 = "A   -   Move Left              D   -   Move Right";
			win.drawString(objective1, 25, 120);
			String objective2 = "S   -   Move Down           W   -   Move Up";
			win.drawString(objective2, 25, 180 + 10);
			String objective3 = "J   -   Shoot Right            L   -   Shoot Left";
			win.drawString(objective3, 25, 240 + 10 + 10);
			String objective4 = "K   -   Shoot Down           I   -   Shoot Up";
			win.drawString(objective4, 25, 300 + 30);
			win.drawString("Up arrow   -   Move tower location up", 25,
					360 + 40);
			win.drawString("Down arrow   -   Move tower location down", 25,
					420 + 50);
			win.drawString("1, 2, 3  -   Select Rafiki Tower", 25, 480 + 60);
			win.drawString("Enter   -   Place Rafiki Tower", 25, 540 + 70);
			String back = "Press right arrow key to continue";
			win.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
			win.drawString(back, 25, 680);
		}
		// in-game state
		if (gameState == 1) {
			win.drawImage(this.addImage("desert.png"), null, 0, 0);
			win.setColor(Color.black);
			this.sendEnemy(win, waveNumber);
			kevin.moveAndDraw(win);
			dontGoDown();
			dontGoOut();
			for (int i = 0; i < basket.size(); i++) {
				bullet egg = basket.get(i);
				if (egg.getY() < -30) {
					basket.remove(i);
					i--;
				} else {
					egg.moveAndDraw(win);
				}
			}
			for (int i = 0; i < towerSpaces.length; i++) {
				if (towerSpaces[i].isTower1) {
					win.drawImage(this.addImage("rafikiyeas.png"), null,
							towerSpaces[i].getX(), towerSpaces[i].getY());
					win.setFont(new Font(Font.SANS_SERIF,
							Font.LAYOUT_LEFT_TO_RIGHT, 20));
					win.drawString(defenses[i].towerNumber,
							towerSpaces[i].getX() + 15,
							towerSpaces[i].getY() + 25);
				}
			}
			this.sendBullet();
			for (int i = 0; i < tbullet.size(); i++) {
				int x1 = (int) tbullet.get(i).getX();
				int x2 = (int) tbullet.get(i).target.getX();
				int y1 = (int) tbullet.get(i).getY();
				int y2 = (int) tbullet.get(i).target.getY();
				int time = tbullet.get(i).timer;
				win.drawImage(this.addImage("imageedit_1_4414988692.png"),
						null, x1 + time * (x2 - x1) / 5, y1 + time * (y2 - y1)
								/ 5);
				tbullet.get(i).timer++;
				if (tbullet.get(i).timer >= 6) {
					tbullet.get(i).target.decreaseHealth(tbullet.get(i).damage);
					tbullet.remove(i);
					i--;
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).checkHealth()) {
					enemies.remove(i);
					score++;
					money += 2;
					i--;
				}
			}
			eggTimer++;
			if (eggTimer >= eggDelay) {
				eggTimer = 0;
				onCoolDown = false;
			}
			win.drawImage(this.addImage("line.png"), null,
					towerSpaces[currentLocation].getX(),
					towerSpaces[currentLocation].getY() + 101);
			win.setColor(Color.BLACK);
			win.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
			String result = "Score: " + score + "   Hakunas: " + money
					+ "   Wave: " + waveNumber;
			win.drawString(result, 150, 50);
			win.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
			String menu = "Main Menu (Backspace)";
			win.drawString(menu, 630, 40);
			if (towerPurchaseCheck) {
				win.setColor(Color.white);
				win.drawString("are you sure you want to buy this tower (Y/N)",
						300, 500);
			}
			win.setColor(Color.white);
			win.drawString("Press 1 and Enter to buy Rafiki Tower.", 100, 600);
			win.drawString("Press 2 or 3 and Enter to upgrade.", 100, 630);
			win.drawString("Towers and upgrades cost 50.", 100, 660);
		// losing page
		} else if (gameState == 0) {
			win.drawImage(this.addImage("imageedit_6_8659685134.png"), null, 0,
					0);

			win.setColor(Color.BLACK);
			win.setFont(scoreFont);
			win.drawString("You lost!!!", 250, 175);
			win.drawString("Wave Number: " + waveNumber, 250, 250);
			win.drawString("Score: " + score, 250, 325);
			win.drawString("Press ENTER!", 250, 400);
			win.drawString("Main Menu (Backspace)", 250, 475);
		}
	}

	public void sendBullet() {
		for (int i = 0; i < defenses.length; i++) {
			if (defenses[i] != null) {
				if (defenses[i].shootTimer > 0) {
					defenses[i].shootTimer--;
				} else {
					boolean targetAcquired = false;
					for (int j = 0; j < enemies.size(); j++) {
						if (!targetAcquired) {
							double a = Math.sqrt(Math.pow((enemies.get(j).getX() - defenses[i].getX()), 2)
								+ Math.pow((enemies.get(j).getY() - defenses[i].getY()), 2));
							if (a < defenses[i].radius) {
								tbullet.add(new towerbullet((int) defenses[i].getX(), (int) defenses[i].getY(),
										enemies.get(j), defenses[i].damage));
								targetAcquired = true;
								defenses[i].shootTimer += defenses[i].fireRate;
							}
						}
					}
				}
			}
		}
	}

	public void sendEnemy(Graphics2D win, int wave) {
		timer++;
		int numEnemies = 8 * wave;
		if (timer > enemyTimer) {
			if (enemySent < numEnemies) {
				Enemy1 enemy = new Enemy1();
				enemy.setImages(imgHyena);
				enemies.add(enemy);
				timer -= enemyTimer;
				enemySent++;
			} else if (wave == 15 && !bossSent) {
				finalBoss x = new finalBoss();
				x.setImages(imgScar);
				enemies.add(x);
				bossSent = true;
			}else if(wave == 10 && !pumbaSent){
				pumba y = new pumba();
				y.setImages(imgPumba);
				enemies.add(y);
				pumbaSent = true;
			}else if(wave == 5 && !tucanSent){
				tucan z = new tucan();
				z.setImages(imgTucan);
				enemies.add(z);
				tucanSent = true;
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			MovingObjects enemy = enemies.get(i);
			enemy.moveAndDraw(win);
			if (!enemy.hasTurned1) {
				if (enemy.getY() >= 100 - enemies.get(i).randomLocation) {
					enemy.moveEast();
					enemy.hasTurned1 = true;
				}
			} else if (!enemy.hasTurned2) {
				if (enemy.getX() >= 700 + enemies.get(i).randomLocation) {
					enemy.moveSouth();
					enemy.hasTurned2 = true;
				}
			} else if (!enemy.hasTurned3) {
				if (enemy.getY() >= 400 + enemies.get(i).randomLocation) {
					enemy.moveWest();
					enemy.hasTurned3 = true;
				}
			} else if (!enemy.hasTurned4) {
				if (enemy.getX() <= 350 - enemies.get(i).randomLocation) {
					enemy.moveNorth();
					enemy.hasTurned4 = true;
				}
			} else if (!enemy.hasTurned5) {
				if (enemy.getY() <= 200 + enemies.get(i).randomLocation) {
					enemy.moveWest();
					enemy.hasTurned5 = true;
				}
			} else if (!enemy.hasTurned6) {
				if (enemy.getX() <= 100 + enemies.get(i).randomLocation) {
					enemy.moveSouth();
					enemy.hasTurned6 = true;
				}
			} else if (!enemy.hasTurned7) {
				if (enemy.getY() >= 500 - enemies.get(i).randomLocation) {
					enemy.moveEast();
					enemy.hasTurned7 = true;
				}
			} else if (enemies.get(i).getX() > 800) {
				lives--;
				if (wave == 15 && i == enemies.size() - 1) {
					lives -= 3;
				}
				if(wave == 10 && i == enemies.size() - 1){
					lives -= 3;
				}
				if(wave == 5 && i == enemies.size()-1){
					lives -= 3;
				}
				enemies.remove(i);
				 if (lives<=0)
				this.checkGameState();
			}
			for (int j = 0; j < basket.size(); j++) {
				bullet x = basket.get(j);
				if (x.intersects(enemies.get(i))) {
					basket.remove(x);
					j++;
					enemies.get(i).decreaseHealth(30);
					if (enemies.get(i).checkHealth()) {
						score++;
						money += 2;
						enemies.remove(i);
						i--;
						basket.remove(j);
						j = basket.size();
					}
				}
			}
		}
		if (enemies.size() == 0 && enemySent == numEnemies) {
			waveNumber++;
			enemySent = 0;
			timer = 0;
		}
	}

	public void keyPressed(KeyEvent e) {
		checkGameState();
		if (e.getKeyCode() == KeyEvent.VK_Z
				&& (gameState == 5 || gameState == 6)) {
			gameState = 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_2 && gameState == 2) {
			gameState = 4;
		}
		if (gameState == 3 && e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameState = 5;
		}
		if (gameState == 4 && e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameState = 6;
		}
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
				&& (gameState == 0 || gameState == 1)) {
			gameState = 2;
			lives = 3;
			soundDriver.stop(1);
			soundDriver.stop(2);
			soundDriver.loop(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_1 && gameState == 2) {
			gameState = 3;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& (gameState == 0 || gameState == 7 || gameState == 2)) {
			gameState = 1;
			soundDriver.stop(0);
			soundDriver.stop(2);
			soundDriver.loop(1);
			this.reset();
		} else {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				kevin.setDx(-kevin.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				kevin.setDx(kevin.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				kevin.setDy(-kevin.getSpeed());
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				kevin.setDy(kevin.getSpeed());
			}
			if (kevin.intersects(r1)) {
				kevin.setDx(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_1) {
				towerSelected = 1;
			}
			if (e.getKeyCode() == KeyEvent.VK_2) {
				towerSelected = 2;
			}
			if (e.getKeyCode() == KeyEvent.VK_3) {
				towerSelected = 3;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER && towerSelected != 0) {
				towerPurchaseCheck = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_Y && towerPurchaseCheck) {
				int x = towerSpaces[currentLocation].getX();
				int y = towerSpaces[currentLocation].getY();
				if (towerSelected == 1) {
					towerPurchaseCheck = false;
					if (money >= 50 && defenses[currentLocation] == null) {
						defenses[currentLocation] = new Tower1(x, y);
						towerSelected = 0;
						money -= defenses[currentLocation].getCost();
						towerSpaces[currentLocation].isTower1 = true;
					}
				} else if (towerSelected == 2
						&& towerSpaces[currentLocation].isTower1) {
					towerPurchaseCheck = false;
					if (towerSpaces[currentLocation].isTower2) {
					} else if (towerSpaces[currentLocation].isTower3) {
						if (money >= 25) {
							money -= 25;
							defenses[currentLocation] = new Tower2(x, y);
							towerSelected = 0;
							towerSpaces[currentLocation].isTower2 = true;
							towerSpaces[currentLocation].isTower3 = false;
						}
					} else if (money >= 50) {
						defenses[currentLocation] = new Tower2(x, y);
						towerSelected = 0;
						money -= defenses[currentLocation].getCost();
						towerSpaces[currentLocation].isTower3 = true;
					}
				} else if (towerSelected == 3
						&& towerSpaces[currentLocation].isTower1) {
					towerPurchaseCheck = false;
					if (towerSpaces[currentLocation].isTower3) {
					} else if (towerSpaces[currentLocation].isTower2) {
						if (money >= 25) {
							money -= 25;
							defenses[currentLocation] = new Tower3(x, y);
							towerSelected = 0;
							towerSpaces[currentLocation].isTower3 = true;
							towerSpaces[currentLocation].isTower2 = false;
						}
					} else if (money >= 50) {
						defenses[currentLocation] = new Tower3(x, y);
						towerSelected = 0;
						money -= defenses[currentLocation].getCost();
						towerSpaces[currentLocation].isTower3 = true;
					}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_N && towerPurchaseCheck) {
				towerPurchaseCheck = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP && locationChange) {
				if (!towerPurchaseCheck) {
					currentLocation += 5;
					currentLocation %= 6;
					locationChange = false;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && locationChange) {
				if (!towerPurchaseCheck) {
					currentLocation++;
					currentLocation %= 6;
					locationChange = false;
				}
			}

		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN && !locationChange) {
			locationChange = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && !locationChange) {
			locationChange = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			kevin.setDx(0);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			kevin.setDx(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			kevin.setDy(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			kevin.setDy(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_J && !onCoolDown) {
			bullet fun = new bullet(kevin);
			fun.setImages(imgTimon);
			fun.setDy(0);
			fun.setDx(-5);
			this.basket.add(fun);
			onCoolDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_K && !onCoolDown) {
			bullet fun = new bullet(kevin);
			fun.setImages(imgTimon);
			fun.setDy(5);
			fun.setDx(0);
			this.basket.add(fun);
			onCoolDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_L && !onCoolDown) {
			bullet fun = new bullet(kevin);
			fun.setImages(imgTimon);
			fun.setDy(0);
			fun.setDx(5);
			this.basket.add(fun);
			onCoolDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_I && !onCoolDown) {
			bullet fun = new bullet(kevin);
			fun.setImages(imgTimon);
			fun.setDy(-5);
			fun.setDx(0);
			this.basket.add(fun);
			onCoolDown = true;
		}
	}

	public void checkGameState() {
		for (int i = 0; i < basket.size(); i++) {
			if (basket.get(i).getX() == 800) {
				lives--;
			}
		}
		if (lives <= 0 && gameState != 0) {
			gameState = 0;
			soundDriver.stop(1);
			soundDriver.stop(0);
			soundDriver.loop(2);
		}
		if(gameState != 7 && waveNumber == 16){
			gameState = 7;
			lives = 0;
		}
	}

	public void dontGoDown() {
		Rectangle a = (Rectangle) kevin.clone();
		a.translate((int) kevin.getDx(), (int) (kevin.getDy()));
		if (a.getY() < 20) {
			kevin.translate(0, -(int) kevin.getDy());
		}
		if (a.getY() > 660) {
			kevin.translate(0, -(int) kevin.getDy());
		}
	}

	public void dontGoOut() {
		Rectangle a = (Rectangle) kevin.clone();
		a.translate((int) kevin.getDx(), (int) kevin.getDy());
		if (a.getX() < 0) {
			kevin.translate(-(int) kevin.getDx(), 0);
		}
		if (a.getX() > 750) {
			kevin.translate(-(int) kevin.getDx(), 0);
		}
	}
}
