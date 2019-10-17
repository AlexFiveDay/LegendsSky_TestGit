package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

import javax.imageio.ImageIO;

/**
 * 敌人子弹
 * 
 * @author 杨大龙
 *
 */
public class enemyBullet extends Bullet {
	public static BufferedImage[] img;
	static {
		img = new BufferedImage[2];
		img[0] = readImage("bullet0.png");
		img[1] = readImage("bullet1.png");
	}

	public enemyBullet(int x, int y, int hx, int hy,int width,int height) {
		super(x, y, hx, hy,20,20);
	}

	//子弹移动
	public void move() {
//		if (xSpeed < 1.0 && xSpeed > 0.0) {
//			x = x + 1;
//		} else {
//			x = (int) (xSpeed + x);
//		}
//		if (ySpeed < 1.0 && ySpeed > 0.0) {
//			y = y + 1;
//		} else {
//			y = (int) (ySpeed + y);
//		}
		x = (int) (xSpeed + x);
		y = (int) (ySpeed + y);
//		 System.out.println(x+"x");
//		 System.out.println(y+"y");

	}
	/*
	 * 英雄机-敌机子弹碰撞检测
	 */
	//this为敌人子弹，other为英雄机
			public boolean hit(Hero other) {
				int x1 = this.x - other.width;   //敌人的x减掉子弹的宽度
				int x2 = this.x + this.width;    //敌人的x加上敌人的宽
				int y1 = this.y - other.height;  //敌人的y剪掉子弹的高
				int y2 = this.y + this.height;   //敌人的y加上敌人的高
				int x = other.x;                 //子弹的x；
				int y = other.y;                 //子弹的y；
				return x >= x1 && x <= x2 && y >= y1 && y <= y2;
			}
	
	/*
	 * 判断子弹是否存活
	 */
	public boolean isLife() {
		if (life>0) {
			return true;
		}
		return false;
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
			return img[i++ % img.length];
		} 
			return null;
	}
}
