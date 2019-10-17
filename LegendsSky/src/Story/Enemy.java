package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * 敌人战机的超类，存有共同属性
 * 
 * @author 杨大龙
 *
 */
public abstract class Enemy implements Award{
	
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int life;
	protected int fire;
	protected int xSpeed;
	protected int ySpeed;
	protected int enemyType;
	protected int score;

	public Enemy(int life, int fire, int xSpeed, int ySpeed, int width,int height,int score) {
		Random ran = new Random();
		this.width = width;
		this.height = height;
		 x = ran.nextInt(600-this.width);
		 y = -this.height;
		this.life = life;
		this.fire = fire;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.score = score;
//		enemyType = ran.nextInt(3);
//		switch (enemyType) {
//		case 0://awardPlane,从左往右
//			x = -this.width;
//			y = ran.nextInt(World.HEIGHT);
//			break;
//		case 1://generalPlane,从上往下
//			x = ran.nextInt(World.WIDTH-this.width);
//			y = -this.height;
//			break;
//		case 2://specialPlane,从右往左
//			x = World.WIDTH;
//			y = ran.nextInt(World.HEIGHT); 
//			break;
//		default:
//			break;
//		}
	}
	public Enemy(int life, int fire, int xSpeed, int ySpeed, int width,int height,int score,int x,int y) {
		Random ran = new Random();
		this.width = width;
		this.height = height;
//		 x = ran.nextInt(600-this.width);
//		 y = -this.height;
		this.life = life;
		this.fire = fire;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.score = score;
		this.x = x;
		this.y = y;
	}

	public abstract void enterWorld();

	/*
	 * 敌机射击shoot
	 */

	public enemyBullet shoot(int hx,int hy) {
		enemyBullet eb = new enemyBullet(this.x + this.width/2, this .y + this.height,hx,hy,20,20); 
		return eb;
	}
	public bossBullet[] bossShoot(int bossIndex) {
		return null;
	}
	
	/*
	 * 敌机移动方法
	 */
	public abstract void move();

	/*
	 * 获取敌机图片
	 */
	public abstract BufferedImage getImage();
	/*
	 * 敌机死亡掉落小星星
	 */
	public Star[] fallingStar() {
		Random ran = new Random();
		int i = ran.nextInt(20);
		int j = ran.nextInt(6);
		if (i <= 15) {
			Star[] star = new Star [j];
			for (int k = 0; k < star.length; k++) {
				star[k] = new Star(this.x+ran.nextInt(width), this.y+ran.nextInt(height),40,42);
			}
				
			return star;
		} else {
			Star[] star = new Star [0];
			return star;
		}
	}
	
	
	/*
	 * 随机生成敌机,不含boss机。 奖励机概率10%，普通敌机60%，特殊敌机30%
	 */
	public static Enemy nextOne() {
		Random ran = new Random();
		int i = ran.nextInt(20);
		if (i <= 2) {
			return new awardPlane();
		} else if (i <= 14) {
			return new generalPlane();
		} else {
			return new specialPlane();
		}
	}
	/*
	 * 获取敌机生命值
	 */
	public int getLife() {
		return life;
	}
	/*
	 * 敌机越界判断
	 */
	public boolean isOutOfBounds() {
			return this.y >= startWorld.HEIGHT || this.y+this.height <= 0 || this.x >= startWorld.WIDTH || this.x <= 0;
	}
	/*
	 * 判断敌机是否存活
	 */
	public boolean isLife() {
		if (life>0) {
			return true;
		}
		return false;
	}
	public boolean isRemove() {
		if (life == -1) {
			return true;
		}
		return false;
	}
	
	/*
	 * 画图片
	 */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	/*
	 * 读取图片的方法，并进行异常处理
	 * 
	 * @param fileName
	 * 
	 * @return 所需敌机的图片
	 */
	public static BufferedImage readImage(String fileName) {
		try {
			File f = new File("images/" + fileName);
			BufferedImage img = ImageIO.read(f); // 同包中读取图片
			return img;
		} catch (Exception e) {// catch文件异常处理。做文件操作容易出现异常。Java强制进行异常处理。
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void hurt() {
		if (life > 0) {
			life --;
		}
	}
	public void goDead() {
		life = 0;
	}

	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}

	
}
