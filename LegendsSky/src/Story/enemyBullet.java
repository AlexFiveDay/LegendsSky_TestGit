package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;

import javax.imageio.ImageIO;

/**
 * �����ӵ�
 * 
 * @author �����
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

	//�ӵ��ƶ�
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
	 * Ӣ�ۻ�-�л��ӵ���ײ���
	 */
	//thisΪ�����ӵ���otherΪӢ�ۻ�
			public boolean hit(Hero other) {
				int x1 = this.x - other.width;   //���˵�x�����ӵ��Ŀ��
				int x2 = this.x + this.width;    //���˵�x���ϵ��˵Ŀ�
				int y1 = this.y - other.height;  //���˵�y�����ӵ��ĸ�
				int y2 = this.y + this.height;   //���˵�y���ϵ��˵ĸ�
				int x = other.x;                 //�ӵ���x��
				int y = other.y;                 //�ӵ���y��
				return x >= x1 && x <= x2 && y >= y1 && y <= y2;
			}
	
	/*
	 * �ж��ӵ��Ƿ���
	 */
	public boolean isLife() {
		if (life>0) {
			return true;
		}
		return false;
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
			return img[i++ % img.length];
		} 
			return null;
	}
}
