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
	 * �ж�С�����Ƿ���
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
	 * ��ȡ����ͼƬ
	 */
	int index = 0;
	public BufferedImage getImage() {
		if (isLife()) {
			return img[index++%2];
		}
		return null;
	}
	/*
	 * �ж�ɾ��״̬
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
	 * С����Խ���ж�
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
			BufferedImage img = ImageIO.read(f); // ͬ���ж�ȡͼƬ
			return img;
		} catch (Exception e) {// catch�ļ��쳣�������ļ��������׳����쳣��Javaǿ�ƽ����쳣����
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
