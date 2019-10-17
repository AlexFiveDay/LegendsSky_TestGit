package Story;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 测试git的使用，更新代码
 * @author 杨大龙
 *
 */
public class startWorld extends JPanel implements KeyListener {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 900;

	public static final int START = 0;
	public static final int GAME_OVER = 3;
	public static final int PAUSE = 2;
	public static final int RUNNING = 1;
	public static final int READING = 4;// 读取排行版
	// public static final int SELECT = 5;// 选择英雄机
	public static final int UPHERO = 6;// 增强子弹
	public static final int LITTLEHERO = 7;// 加僚机
	public static final int INVINCIBLE = 8;// 无敌状态
	public static final int NOSTAR = 9;// 星星不够，升级失败
	
	public static int bossIndex = 0;//boss关卡控制
	public static boolean bossAppear = false;//boss关卡控制
	private static BufferedImage start;
	private static BufferedImage pause;
	private static BufferedImage gameover;
	private static BufferedImage sort;// 排行榜
	// private static BufferedImage select;// 英雄机2
	private static PlayAudio bz;
	private static PlayAudio shoot;
	private static PlayAudio hit;
	private static PlayAudio bgm;
	static {
		start = BackGround.readImage("start.jpg");
		pause = BackGround.readImage("pause.png");
		gameover = BackGround.readImage("gameover.jpg");
		sort = BackGround.readImage("sort.jpg");
		
		bz = new PlayAudio("bigexplosion.wav");
		shoot = new PlayAudio("shoot.wav");
		hit = new PlayAudio("button.wav");
		bgm = new PlayAudio("backgroundMusic.wav");
		
	}
	private int state = START;
	static boolean U;
	static boolean D;
	static boolean L;
	static boolean R;
	static boolean INVINCIBLETIME = false;//无敌时间判定
	Hero player = new Player1();

	List<Enemy> enemy = new ArrayList<Enemy>();
	List<Enemy> bossenemy = new ArrayList<Enemy>();
	BackGround background = new BackGround();
	// Bullet[] enemybullet = new enemyBullet[0];
	List<enemyBullet> enemybullet = new ArrayList<enemyBullet>();
	List<playerBullet[]> playerbullet = new ArrayList<playerBullet[]>();
	List<littleHeroBullet[]> littlebullet = new ArrayList<littleHeroBullet[]>();
	List<bossBullet[]> bossbullet = new ArrayList<bossBullet[]>();
	List<Star[]> star = new ArrayList<Star[]>();
	littleHero little = null;
	int index = 0;
	/*
	 * 敌机进场
	 */
	int enemyEnterIndex = 0;

	public void enterEnemy() {
		enemyEnterIndex++;
		if (enemyEnterIndex % 40 == 0) {
			if (bossenemy.size() == 0) {
				enemy.add(Enemy.nextOne());
			}
		}
	}
	//boss进场
	int enemybossEnterIndex = 0;

	public void enterbossEnemy() {
		enemybossEnterIndex++;
//		System.out.println(enemybossEnterIndex);
		if (enemybossEnterIndex % 1000 == 0) {
			if (bossenemy.size() == 0) {
				bossenemy.add(new bossPlane(bossIndex));
				bossAppear = true;
//				System.out.println(bossIndex);
			}
		}
	}

	/*
	 * 敌机射击（返回子弹数组）
	 */
	int enemyBulletEnterIndex = 0;

	public void enemyShootAction() {
		enemyBulletEnterIndex++;
		if (enemyBulletEnterIndex % 100 == 0) {
			for (Enemy enemy2 : enemy) {
				if (enemy.indexOf(enemy2)%4 == 0) {
					enemyBullet eb = enemy2.shoot(player.getX() + player.width, player.getY());
					enemybullet.add(eb);
				}
			}
		}
	}
	int bossBulletEnterIndex = 0;

	public void bossShootAction() {
		bossBulletEnterIndex++;
		if (bossBulletEnterIndex % 200 == 0) {
			for (Enemy enemy3 : bossenemy) {
				bossBullet[] bb = enemy3.bossShoot(bossIndex);
				bossbullet.add(bb);
			}
		}
	}

	/*
	 * 敌机移动
	 */
	public void enemyMoveAction() {
		for (Enemy enemy2 : enemy) {
			enemy2.move();
		}
		for (Enemy enemy3 : bossenemy) {
			enemy3.move();
		}
	}

	/*
	 * 敌机子弹移动
	 */
	public void enemyBulletMoveAction() {
		// for (int i = 0; i < enemybullet.length; i++) {
		// enemybullet[i].move();
		// }
		for (enemyBullet enemybullet2 : enemybullet) {
			enemybullet2.move();
		}
		for (bossBullet[] bossBullet2 : bossbullet) {
			for (int i = 0; i < bossBullet2.length; i++) {
				bossBullet2[i].move();
			}
		}
	}
	
	/*
	 * 小行星移动List<Star[]> star = new ArrayList<Star[]>();
	 */
	public void starMoveAction() {
		for (Star[] star2 : star) {
			for (int i = 0; i < star2.length; i++) {
				star2[i].move();
			}
		}
	}
	/*
	 * 英雄机射击
	 * 
	 */
	int playerBulletEnterIndex = 0;

	public void playerShootAction() {
		playerBulletEnterIndex++;
		if (playerBulletEnterIndex % 20 == 0) {
			playerBullet[] pb = (playerBullet[]) player.shoot();
			playerbullet.add(pb);
			
		}
	}
	//僚机射击
	int littlebulletEnterIndex = 0;
	public void littleHeroShootAction() {
		littlebulletEnterIndex++;
		if (littlebulletEnterIndex % 50 == 0) {
			if (little != null) {
				littleHeroBullet[] lb = (littleHeroBullet[]) little.shoot();
				littlebullet.add(lb);
			}
			
		}
	}
	/*僚机子弹移动List<littleHeroBullet[]> littlebullet = new ArrayList<littleHeroBullet[]>();*/
	public void littleBulletMoveAction() {
		for (littleHeroBullet[] littleBullet2 : littlebullet) {
			for (int i = 0; i < littleBullet2.length; i++) {
				littleBullet2[i].move();
			}
		}
	}

	/*
	 * 英雄机子弹移动
	 */
	public void playerBulletMoveAction() {
		for (playerBullet[] playerbullet : playerbullet) {
//			 System.out.println(player.shootState);
			// playerbullet.move(player.SHOOTLEVE2);
			switch (player.shootState) {
			case 1:
				for (playerBullet playerBullet2 : playerbullet) {
					playerBullet2.move(player.SHOOTLEVE1);
				}
				break;
			case 2:
//				System.out.println(player.shootState);
//				System.out.println(5555);
				for (playerBullet playerBullet2 : playerbullet) {
					playerBullet2.move(player.SHOOTLEVE2);
				}
				break;
			case 3:
				for (playerBullet playerBullet2 : playerbullet) {
					playerBullet2.move(player.SHOOTLEVE3);
				}
				break;
			}
			// if (player.shootState == 1) {
			// playerbullet.move(player.SHOOTLEVE1);
			// } else if (player.shootState == 2) {
			// playerbullet.move(player.SHOOTLEVE2);
			// }
		}
	}

	/*
	 * 英雄机移动
	 */
	public void heroMoveAction() {
		// System.out.println("开始动了");
		player.move();
	}

	/*
	 * 敌机子弹-玩家碰撞测试
	 */
	public void enemybulletBangAction() {
		for (enemyBullet eb : enemybullet) {
			if (eb.isLife() && player.isLife() && eb.hit(player)) {
				player.goDead();
				eb.goDead();
			}
		}
		for (bossBullet[] bossBullet2 : bossbullet) {
			for (int i = 0; i < bossBullet2.length; i++) {
				if (bossBullet2[i].isLife() && player.isLife() && bossBullet2[i].hit(player)) {
					player.goDead();
					bossBullet2[i].goDead();

				}
			}
		}
		// for (int i = 0; i < enemybullet.size(); i++) {
		// enemyBullet b = enemybullet.get(i);
		// if(player.isLife() && b.isLife() && b.hit(player)) {
		// player.goDead();
		// b.goDead();
		// }
		// }
	}

	/*
	 * 英雄机子弹-敌机碰撞测试
	 */
	public void playerBulletBangAction() {
		for (playerBullet[] pb : playerbullet) {
			for (playerBullet playerBullet : pb) {
				for (Enemy enemy : enemy) {
					if (playerBullet.isLife() && enemy.isLife() && playerBullet.hit(enemy)) {
						enemy.hurt();
						playerBullet.goDead();
//						 hit.playStart();// 小敌机击中爆炸
						if (!enemy.isLife()) {
							bz.playStop();
							bz.playStart();////////////////////////////
							player.score(enemy.getScore());
						}
					}
				}
			}
		}
		for (playerBullet[] pb : playerbullet) {
			for (playerBullet playerBullet : pb) {
				for (Enemy enemy : bossenemy) {
					if (playerBullet.isLife() && enemy.isLife() && playerBullet.hit(enemy)) {
						enemy.hurt();
						hit.playStop();
						hit.playStart();/////////////////////////////
						playerBullet.goDead();
						// hit.playStart();// boss击中音效
						if (!enemy.isLife()) {
							player.score(enemy.getScore());
							enemybossEnterIndex = 0;
							if (bossIndex < 4) {
//								System.out.println("boss死了，bossIndex+1");
								bossIndex++;
							} else {
								bossIndex = 0;
							}
						}
					}
				}
			}
		}
	}
	/*
	 * 僚机子弹-敌机碰撞测试List<littleHeroBullet[]> littlebullet = new ArrayList<littleHeroBullet[]>();
	 */
	public void littleHeroBulletBangAction() {
		for (littleHeroBullet[] lb : littlebullet) {
			for (littleHeroBullet littleBullet : lb) {
				
				for (Enemy enemy : enemy) {
					//System.out.println(littleBullet.isLife() && enemy.isLife());
					//System.out.println(littleBullet.hit(enemy));
					//System.out.println(littleBullet.getHeight());
					//System.out.println(littleBullet.getWidth());
					//System.out.println(littleBullet.getx());
					//System.out.println(littleBullet.gety());
					if (littleBullet.isLife() && enemy.isLife() && littleBullet.hit(enemy)) {
						enemy.hurt();
						littleBullet.goDead();
						 //hit.playStart();// 小敌机击中爆炸
						if (!enemy.isLife()) {
							bz.playStop();
							bz.playStart();
							player.score(enemy.getScore());
						}
					}
				}
			}
		}
		for (littleHeroBullet[] lb : littlebullet) {
			for (littleHeroBullet littleBullet : lb) {
				for (Enemy enemy : bossenemy) {
					if (littleBullet.isLife() && enemy.isLife() && littleBullet.hit(enemy)) {
						enemy.hurt();
						littleBullet.goDead();
						// hit.playStart();// boss击中音效
						if (!enemy.isLife()) {
							player.score(enemy.getScore());
							enemybossEnterIndex = 0;
							if (bossIndex < 4) {
//								System.out.println("boss死了，bossIndex+1");
								bossIndex++;
							} else {
								bossIndex = 0;
							}
						}
					}
				}
			}
		}
	}
	/*
	 * 英雄机-敌机碰撞测试
	 */
	public void playerEnemyBangAction() {
		for (Enemy enemy2 : enemy) {
			// System.out.println(player.getLife());
			//
			// System.out.print(player.hit(enemy2));
			// System.out.print(enemy2.isLife());
			// System.out.print(player.isLife());
			if (enemy2.isLife() && player.isLife() && player.hit(enemy2)) {
				player.goDead();
				enemy2.goDead();
				bz.playStop();
				bz.playStart();// 英雄机和敌机碰撞音效
				if (!enemy2.isLife()) {
					player.score(enemy2.getScore());
				}
			}
		}
		for (Enemy enemy3 : bossenemy) {
			if (enemy3.isLife() && player.isLife() && player.hit(enemy3)) {
				player.goDead();
				enemy3.hurt();
				player.restart();
//				 bz.playStart();// 英雄机爆炸音效
				if (!enemy3.isLife()) {
					player.score(enemy3.getScore());
					enemybossEnterIndex = 0;
					if (bossIndex < 4) {
						bossIndex++;
					} else {
						bossIndex = 0;
					}
				}
			}
		}
	}

	/*
	 * 英雄机和道具碰撞
	 */
	public void playerStarBangAction() {
		for (Star[] stars : star) {
			for (int i = 0; i < stars.length; i++) {
				if (stars[i].isLife() && player.isLife() && player.hit(stars[i])) {
					hit.playStop();
					hit.playStart();////////////////////////////////
					stars[i].goDead();
					player.star();
				}
			}
		}
	}

	/*
	 * 删除处理
	 */
	public void outOfBoundsAction() {
		// 敌机越界死亡删除
		for (Iterator e = enemy.iterator(); e.hasNext();) {
			Enemy enemy2 = (Enemy) e.next();
			if (enemy2.isOutOfBounds() || enemy2.isRemove()) {
				if (enemy2.isRemove()) {
					star.add(enemy2.fallingStar());
				}
				e.remove();
			}
		}
//		 System.out.println(enemy.size());
		// boss越界死亡删除
		for (Iterator e = bossenemy.iterator(); e.hasNext();) {
			Enemy enemy3 = (Enemy) e.next();
			if (enemy3.isRemove()) {
				star.add(enemy3.fallingStar()); // 死亡掉落小星星
				bossAppear = false;
				e.remove();
			}
		}
		// 敌机子弹越界死亡删除
		for (Iterator eb = enemybullet.iterator(); eb.hasNext();) {
			enemyBullet enemybullet = (enemyBullet) eb.next();
			if (enemybullet.isOutOfBounds() || !enemybullet.isLife()) {
				eb.remove();
			}
		}
//		System.out.println(enemybullet.size());
		// boss子弹越界死亡删除
		for (Iterator bb = bossbullet.iterator(); bb.hasNext();) {
			bossBullet[] bossBullet2 = (bossBullet[]) bb.next();
			if (bossBullet.isRemove(bossBullet2)) {
				bb.remove();
			}
//			 System.out.println(bossbullet.size());
		}

		// 英雄机子弹越界死亡删除
		for (Iterator pb = playerbullet.iterator(); pb.hasNext();) {
			playerBullet[] playerbullet = (playerBullet[]) pb.next();
			if (playerBullet.isRemove(playerbullet)) {
				pb.remove();
			}
		}
//		System.out.println(playerbullet.size());
		// 小星星删除
		for (Iterator s = star.iterator(); s.hasNext();) {
			Star[] st = (Star[]) s.next();
			if (Star.isRemove(st)) {
				s.remove();
			}
			// System.out.println(star.size());
		}

	}
	/*
	 * 无敌时间控制
	 */
	int wudiindex = 1; 
	public void wudiaction() {
//		System.out.println("无敌"+INVINCIBLETIME);
		if (INVINCIBLETIME) {
			wudiindex++;
//			System.out.println(wudiindex);
			if (wudiindex % 500 == 0) {
//				player.setIndex();
				INVINCIBLETIME = false;
				player.setState(player.GENERAL);
				wudiindex = 1;
			}
		}
	}
	/*
	 * 僚机运动和射击
	 */
	private void little() {
		if (little != null) {
			little.move(player.x+player.width/2, player.y+player.height/2);
			little.shoot();
		}
		}
	/*
	 * 判断英雄机是否死亡，结束游戏
	 */
	public void chackGameOver() {
		if (player.getLife() <= 0) {
			bgm.playStop();
			shoot.playStop();
			state = GAME_OVER;
			ScoreWrite.write(player.getScore());
		}
	}

	/*
	 * 播放背景音乐控制
	 */
/*	public void playMusic() {
		if (state == START) {
			
		}
		if (state == RUNNING) {
			//bgm.playSloop();
			bgm.playSloop();
		}
		if (state == PAUSE) {
		}
		if (state == GAME_OVER) {
			bgm.playStart();
		}
	}*/

	/*
	 * (non-Javadoc)窗口图片绘制
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {

		background.paintObject(g);

		player.paintObject(g);
		
		if (little != null) {
			little.paintObject(g);
		}
		
		
		for (Enemy enemy2 : enemy) {
			enemy2.paintObject(g);
		}
		for (Enemy enemy3 : bossenemy) {
			enemy3.paintObject(g);
		}
		for (Star[] stars : star) {
			for (int i = 0; i < stars.length; i++) {
				// System.out.println(stars[i]);
				if (stars[i] != null) {
					stars[i].paintObject(g);
				}
			}
		}

		
		
		for (littleHeroBullet[] littlebullet1 : littlebullet) {
			for (littleHeroBullet littlebullet : littlebullet1) {
				littlebullet.paintObject(g);
			}
		}
		
		for (enemyBullet enemybullet2 : enemybullet) {
			enemybullet2.paintObject(g);
		}
		for (bossBullet[] enemybullet2 : bossbullet) {
			for (int i = 0; i < enemybullet2.length; i++) {
				enemybullet2[i].paintObject(g);
			}
		}
		for (playerBullet[] playerbullet2 : playerbullet) {
			for (playerBullet playerBullet : playerbullet2) {
				playerBullet.paintObject(g);
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.BOLD, 24));
		//g.drawString("Life  " + player.getLife(), 10, 60);
		g.drawString(player.getScore()+" ", 100, 99);
		g.drawString(player.getStar()+" ", 100, 148);

		switch (state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		/*
		 * 写排行榜
		 */
		case READING:
			g.drawImage(sort, 0, 0, null);
			int[] sort = ScoreRead.read();
			/*for (int i = 0; i < sort.length; i++) {
				g.drawString(sort[i] + "",300, 200 + i * 60);
			}*/
			g.drawString(sort[0] + "",430, 228);
			g.drawString(sort[1] + "",430, 298);
			g.drawString(sort[2] + "",438, 361);
			g.drawString(sort[3] + "",438, 483);
			g.drawString(sort[4] + "",438, 543);
			g.drawString(sort[5] + "",438, 604);
			g.drawString(sort[6] + "",444, 668);
			g.drawString(sort[7] + "",448, 733);
			g.drawString(sort[8] + "",448, 802);
			g.drawString(sort[9] + "",448, 865);
			break;
		/*
		 * 画血条
		 */
		case RUNNING:
			int HeroLife = 20;// 英雄机血条
			if (player.getLife() > HeroLife * 0.8) {
				g.setColor(Color.GREEN);
				g.fillRect(69, 19, player.getLife()*9, 25);
			} else if (player.getLife() > HeroLife * 0.4) {
				g.setColor(Color.YELLOW);
				g.fillRect(69, 19, player.getLife()*9, 25);
			} else {
				g.setColor(Color.RED);
				g.fillRect(69, 19, player.getLife()*9, 25);
			}
			//System.out.println(bossAppear);
			
			if (bossAppear) {
				//System.out.println(bossenemy.get(0).getLife());
				int bossiii = bossenemy.get(0).getLife();
				int BossLife = 350;// boss血条
			/*	System.out.println(bossA);
				System.out.println(bossB);
				g.setColor(Color.GREEN);
				g.fillRect(bossA, 19,bossB , 21);*/
				if (bossiii > BossLife * 0.8) {
					g.setColor(Color.GREEN);
					g.fillRect(525-bossiii/2, 19, bossiii/2, 21);
				} else if (bossiii > BossLife * 0.4) {
					g.setColor(Color.YELLOW);
					g.fillRect(525-bossiii/2, 19, bossiii/2, 21);
				} else {
					g.setColor(Color.RED);
					g.fillRect(525-bossiii/2, 19, bossiii/2, 21);
				}
				/*g.setColor(Color.RED);
				g.fillRect(525-bossiii/2, 19, bossiii/2, 21);*/
			}
			
			break;
		case UPHERO:
			g.drawString("子弹升级成功", 250, 450);
			break;
		case LITTLEHERO:
			g.drawString("僚机生成成功", 250, 450);
			break;
		case INVINCIBLE:
			g.drawString("无敌状态开启成功", 250, 450);
			break;
		case NOSTAR:
			g.drawString("小星星不够，继续打吧", 250, 450);
			break;
		}
	}

	/*
	 * 键盘监听 (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			U = false;
			break;
		case KeyEvent.VK_DOWN:
			D = false;
			break;
		case KeyEvent.VK_LEFT:
			L = false;
			break;
		case KeyEvent.VK_RIGHT:
			R = false;
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (player.getY() > 0) {
				U = true;
			} else {
				U = false;
			}

			// System.out.println(U);
			break;
		case KeyEvent.VK_DOWN:
			D = true;
			break;
		case KeyEvent.VK_LEFT:
			L = true;
			break;
		case KeyEvent.VK_RIGHT:
			R = true;
			break;
		case KeyEvent.VK_SPACE:
			if (state == RUNNING) {
				state = PAUSE;
				bgm.playStop();///////////////////////////////////////////////////
				shoot.playStop();
			} else if (state == PAUSE) {
				state = RUNNING;
				bgm.playSloop();/////////////////////////////////////////////////////
				shoot.playSloop();
			} else if (state == GAME_OVER) {
				state = START;
			} else if (state == INVINCIBLE) {
				state = RUNNING;
			}
			break;
		}
		if (player.getX() < 0) {
			L = false;
		} else if (player.getX() > (startWorld.WIDTH - player.width)) {
			R = false;
		}
		if (player.getY() < 0) {
			U = false;
		} else if (player.getY() > (startWorld.HEIGHT - player.height)) {
			D = false;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			U = false;
			break;
		case KeyEvent.VK_DOWN:
			D = false;
			break;
		case KeyEvent.VK_LEFT:
			L = false;
			break;
		case KeyEvent.VK_RIGHT:
			R = false;
			break;
		}
	}

	/*
	 * 功能汇聚区
	 */
	public void action() {
		MouseAdapter l = new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case START:
					if (e.getX() > 170 && e.getX() < 430 && e.getY() > 725 && e.getY() < 830) {
						bgm.playSloop();////////////////////////////////////////////////////////////////
						state = RUNNING;
						shoot.playSloop();// 英雄机射击音效
					}
					if (e.getX() > 208 && e.getX() < 400 && e.getY() > 620 && e.getY() < 710) {
						state = READING;
					}
					break;
				case GAME_OVER:
					enemy.clear();
					background = new BackGround();
					enemybullet.clear();
					playerbullet.clear();
					bossbullet.clear();
					bossenemy.clear();
					player = new Player1();
					star.clear();
					little = null;
					littlebullet.clear();
					enemyEnterIndex = 0;//boss进场时间重置
					bossIndex = 0;//boss关卡重置
					bossAppear = false;//boss出现状态重置
					state = START;
					break;
				case READING:
					state = START;
					break;
				case PAUSE:
					if (e.getX() > 80 && e.getX() < 200 && e.getY() > 690 && e.getY() < 870) {
						// 子弹增强
//						if ( (player.getStar() >= player.UPHERO) && (Hero.shootState < 3) ) {
							state = UPHERO;
							player.setShootState();
							player.setStar(player.UPHERO);
//						} else {
//							state = NOSTAR;
//						}
					} else if (e.getX() > 230 && e.getX() < 350 && e.getY() > 690 && e.getY() < 870) {
						// 加僚机
//						if (player.getStar() >= player.LITTLEHERO) {
							state = LITTLEHERO;
							little = new littleHero(player.getX()-littleHero.getWidth()/2, player.getY()-littleHero.getHeight()/2);
							player.setStar(player.LITTLEHERO);
//						} else {
//							state = NOSTAR;
//						}
//						state = LITTLEHERO;
					}else if (e.getX() > 390 && e.getX() < 510 && e.getY() > 670 && e.getY() < 870) {
						// 无敌状态
//						if (player.getStar() >= player.INVINCEBLESTAR) {
							state = INVINCIBLE;
							player.setState(INVINCIBLE);
							player.setStar(player.INVINCEBLESTAR);
//						} else {
//							state = NOSTAR;
//						}
					}else {
						bgm.playSloop();
						shoot.playSloop();
						state = RUNNING;
					}
					break;
				case UPHERO:
					state = PAUSE;
					break;
				case LITTLEHERO:
					state = PAUSE;
					break;
				case INVINCIBLE:
					bgm.playSloop();
					shoot.playSloop();
					state = RUNNING;
					INVINCIBLETIME = true;
					break;
				case NOSTAR:
					state = PAUSE;
					break;
				}
			}

			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}

			public void mouseEntered(MouseEvent e) {
				// if (state == PAUSE) {
				// state = RUNNING;
				// }
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if (state == RUNNING) {
					enterEnemy();// 敌人进场
					enterbossEnemy();//boss进场
					bossShootAction();//boss子弹移动
					enemyMoveAction();// 敌人移动
					heroMoveAction();// 英雄机移动
					 enemyShootAction();//敌人射击
					enemyBulletMoveAction();// 敌人子弹移动
					playerShootAction();// 英雄机射击
					playerBulletMoveAction();// 英雄机子弹移动
					enemybulletBangAction();// 敌人子弹碰撞
					playerBulletBangAction();// 英雄机子弹碰撞
					playerEnemyBangAction();// 敌人英雄机碰撞
					playerStarBangAction();// 英雄机道具碰撞
					starMoveAction();//小行星移动
					outOfBoundsAction();// 越界处理
					wudiaction();//无敌状态控制
					little();//僚机运动
					littleHeroShootAction();//僚机射击
					littleBulletMoveAction();//僚机子弹移动
					littleHeroBulletBangAction();//僚机子弹碰撞
					//playMusic();//游戏音效
					chackGameOver();// 判断游戏结束
				}
				repaint();
			}
		}, 0, 10);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		startWorld world = new startWorld();
		frame.add(world);
		frame.addKeyListener(world);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);

		world.action();
		// world.playMusic();
		frame.setVisible(true); // 1)设置窗口可见 2)尽快调用paint()方法
	}

}
