package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Star {
	protected static BufferedImage[] img;
	static {
		img = new BufferedImage[2];
		img[0] = readImage("star"+0+".png");
		img[1] = readImage("star"+1+".png");
	}
	protected int x;
	protected int y;
	private int life;
	protected int width;
	protected int height;
	protected double xSpeed;
	protected double ySpeed;
	public Star(int x, int y,int width,int height) {
		super();
		this.x = x;
		this.y = y;
		life = 1;
		this.width = width;
		this.height = height;
		ySpeed = 1;
//		width = 40;
//		height = 42;
	}
	public void move() {
		y += (int) (ySpeed);
	}
	/*
	 * 判断小星星是否存活
	 */
	public boolean isLife() {
		if (life>0) {
			return true;
		}
		return false;
	}
	public void goDead() {
		if (life > 0) {
			life--;
		}
	}
	/*
	 * 获取星星图片
	 */
	int index = 0;
	public BufferedImage getImage() {
		if (isLife()) {
			return img[index++%2];
		}
		return null;
	}
	/*
	 * 判断删除状态
	 */
		public static boolean isRemove(Star[] s) {
			boolean[] boo = new boolean[s.length];
				for (int i = 0; i < s.length; i++) {
					boo[i] = !s[i].isLife() || s[i].isOutOfBounds();
				}
			boolean res = true;
			for (boolean b : boo) {
				res &= b;
			}
			return res;
		}
	/*
	 * 小星星越界判断
	 */
	public boolean isOutOfBounds() {
		return this.y >= startWorld.HEIGHT || this.x >= startWorld.WIDTH || this.x <= 0;
	}
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
