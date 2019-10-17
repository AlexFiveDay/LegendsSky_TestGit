package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.security.auth.x500.X500Principal;

/**
 * 敌人子弹
 * 
 * @author 杨大龙
 *
 */
public class bossBullet extends Bullet {
	public static BufferedImage[] img;
	static {
		img = new BufferedImage[3];
		
		img[2] = readImage("boss2.png");
		img[1] = readImage("bossbullet0.png");
		img[0] = readImage("boss0.png");
	}
	private int bosslife;
	private int width;
	private int height;

	public bossBullet(int x, int y, int width, int height, int bosslife,int xSpeed,int ySpeed) {
		super(x, y, width, height, bosslife);
		this.bosslife = bosslife;
		this.width = width;
		this.height = height;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}

	// 子弹移动
	public void move() {
		x += xSpeed;
		y += ySpeed;
	}

	/*
	 * 英雄机-敌机子弹碰撞检测
	 */
	// this为敌人子弹，other为英雄机
	public boolean hit(Hero other) {
		int x1 = this.x - other.width; // 敌人的x减掉子弹的宽度
		int x2 = this.x + this.width; // 敌人的x加上敌人的宽
		int y1 = this.y - other.height; // 敌人的y剪掉子弹的高
		int y2 = this.y + this.height; // 敌人的y加上敌人的高
		int x = other.x; // 子弹的x；
		int y = other.y; // 子弹的y；
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	/*
	 * 判断子弹是否存活
	 */
	public boolean isLife() {
		if (life > 0) {
			return true;
		}
		return false;
	}

	// boss子弹越界死亡删除
	public static boolean isRemove(bossBullet[] bb) {
		boolean[] boo = new boolean[bb.length];
			for (int i = 0; i < bb.length; i++) {
				boo[i] = !bb[i].isLife() || bb[i].isOutOfBounds();
			}
		boolean res = true;
		for (boolean b : boo) {
			res &= b;
		}
		return res;
	}

	/*
	 * 敌机子弹死亡
	 */
	public void goDead() {
		life = 0;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	int i = 0;

	public BufferedImage getImage() {
		if (isLife()) {
			switch (startWorld.bossIndex) {
			case 0:
				if (bosslife >= 150) {
					return img[1];
				} else {
					return img[0];
				}
			case 1:
				if (bosslife >= 150) {
					return img[2];
				} else {
					return img[1];
				}
			case 2:
				if (bosslife >= 150) {
					return img[0];
				} else {
					return img[1];
				}
			case 3:
				if (bosslife >= 150) {
					return img[0];
				} else {
					return img[1];
				}
			case 4:
				if (bosslife >= 150) {
					return img[0];
				} else {
					return img[1];
				}
			default:
				break;
			}
			
		}
		return null;
	}
}
