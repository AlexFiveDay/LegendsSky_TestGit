package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.security.auth.x500.X500Principal;

/**
 * �����ӵ�
 * 
 * @author �����
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

	// �ӵ��ƶ�
	public void move() {
		x += xSpeed;
		y += ySpeed;
	}

	/*
	 * Ӣ�ۻ�-�л��ӵ���ײ���
	 */
	// thisΪ�����ӵ���otherΪӢ�ۻ�
	public boolean hit(Hero other) {
		int x1 = this.x - other.width; // ���˵�x�����ӵ��Ŀ��
		int x2 = this.x + this.width; // ���˵�x���ϵ��˵Ŀ�
		int y1 = this.y - other.height; // ���˵�y�����ӵ��ĸ�
		int y2 = this.y + this.height; // ���˵�y���ϵ��˵ĸ�
		int x = other.x; // �ӵ���x��
		int y = other.y; // �ӵ���y��
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}

	/*
	 * �ж��ӵ��Ƿ���
	 */
	public boolean isLife() {
		if (life > 0) {
			return true;
		}
		return false;
	}

	// boss�ӵ�Խ������ɾ��
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
	 * �л��ӵ�����
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
