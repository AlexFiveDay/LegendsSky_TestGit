package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 子弹超类
 * 
 * @author 杨大龙
 *
 */
public abstract class Bullet {
	protected double xSpeed;
	protected double ySpeed;
	protected int x;
	protected int y;
	protected int life;
	protected int width;
	protected int height;

	public Bullet(int x, int y,int width,int height) {
		this.xSpeed = 1;
		this.ySpeed = 1;
		this.width = width;
		this.height = height;
		life = 1;
		this.x = x;
		this.y = y;
	}
	public Bullet(int x, int y,int width,int height,int bosslife) {
		//this.ySpeed = 2;
		this.width = width;
		this.height = height;
		life = 1;
		this.x = x;
		this.y = y;
	}
	
	public Bullet(int x, int y,int hx,int hy,int width,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		life = 1;
		double z=Math.sqrt((this.x-hx)*(this.x-hx)+(this.y-hy)*(this.y-hy));
		ySpeed = 3*(hy-this.y)/z;
		xSpeed = 3*(hx-this.x)/z;
//		System.out.println(ySpeed+"x");
//		System.out.println(xSpeed+"y");
	}

	/*
	 * 子弹移动方式各不相同
	 */
	public void move() {
	};
	/*
	 * 子弹越界处理
	 */
	public boolean isOutOfBounds() {
		return this.y >= startWorld.HEIGHT || this.y+this.height <= 0 || this.x >= startWorld.WIDTH || this.x <= 0;
	}
	public abstract BufferedImage getImage();
	
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

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
}
