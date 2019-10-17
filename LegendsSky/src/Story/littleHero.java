package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class littleHero {
	private static BufferedImage image;
	static {
		image = readImage("littlehero.png");
	}
	private static int width = 60;
	private static int height = 40;
	private int x;
	private int y;
	private double R = 120;
	private double angle;

	public littleHero(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		angle = 0;
	}

	public void move(int x,int y) {
		this.x = (int) (R * Math.cos(angle) + x);
		this.y = (int) (R * Math.sin(angle) + y);
		angle += 0.04;
	}
	
	public littleHeroBullet[] shoot() {
		littleHeroBullet[] lb = new littleHeroBullet[1];
		lb[0] = new littleHeroBullet(this.x + width/2 - 5, this.y - 30, 10, 20);
		return lb;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	/*
	 * 获取各个英雄机的图片
	 */
	public BufferedImage getImage() {
		return image;
	}

	/*
	 * 画图
	 */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	/*
	 * 读图
	 */
	public static BufferedImage readImage(String fileName) {
		try {
			File f = new File("C:\\Users\\杨大龙\\eclipse-workspace\\LegendsSky\\image\\" + fileName);
			BufferedImage img = ImageIO.read(f); // 同包中读取图片
			return img;
		} catch (Exception e) {// catch文件异常处理。做文件操作容易出现异常。Java强制进行异常处理。
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	
}
