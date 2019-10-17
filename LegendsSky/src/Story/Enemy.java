package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * ����ս���ĳ��࣬���й�ͬ����
 * 
 * @author �����
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
//		case 0://awardPlane,��������
//			x = -this.width;
//			y = ran.nextInt(World.HEIGHT);
//			break;
//		case 1://generalPlane,��������
//			x = ran.nextInt(World.WIDTH-this.width);
//			y = -this.height;
//			break;
//		case 2://specialPlane,��������
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
	 * �л����shoot
	 */

	public enemyBullet shoot(int hx,int hy) {
		enemyBullet eb = new enemyBullet(this.x + this.width/2, this .y + this.height,hx,hy,20,20); 
		return eb;
	}
	public bossBullet[] bossShoot(int bossIndex) {
		return null;
	}
	
	/*
	 * �л��ƶ�����
	 */
	public abstract void move();

	/*
	 * ��ȡ�л�ͼƬ
	 */
	public abstract BufferedImage getImage();
	/*
	 * �л���������С����
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
	 * ������ɵл�,����boss���� ����������10%����ͨ�л�60%������л�30%
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
	 * ��ȡ�л�����ֵ
	 */
	public int getLife() {
		return life;
	}
	/*
	 * �л�Խ���ж�
	 */
	public boolean isOutOfBounds() {
			return this.y >= startWorld.HEIGHT || this.y+this.height <= 0 || this.x >= startWorld.WIDTH || this.x <= 0;
	}
	/*
	 * �жϵл��Ƿ���
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
	 * ��ͼƬ
	 */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	/*
	 * ��ȡͼƬ�ķ������������쳣����
	 * 
	 * @param fileName
	 * 
	 * @return ����л���ͼƬ
	 */
	public static BufferedImage readImage(String fileName) {
		try {
			File f = new File("images/" + fileName);
			BufferedImage img = ImageIO.read(f); // ͬ���ж�ȡͼƬ
			return img;
		} catch (Exception e) {// catch�ļ��쳣�������ļ��������׳����쳣��Javaǿ�ƽ����쳣����
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
