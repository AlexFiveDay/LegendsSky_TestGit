package Story;

import java.awt.image.BufferedImage;

public class littleHeroBullet extends Bullet{
	private static BufferedImage img;
	static {
		img = readImage("littleherobullet.png");
	}
	public littleHeroBullet(int x, int y, int width, int height) {
		super(x, y, 10, 20);
	}
	public void move() {
		y -= 3*ySpeed;
	}
	/*
	 * �л�-����ӵ���ײ���
	 */
	public boolean hit(Enemy other) {
		int x1 = this.x - other.width;   //���˵�x�����ӵ��Ŀ��
		int x2 = this.x + getWidth();    //���˵�x���ϵ��˵Ŀ�
		int y1 = this.y - other.height;  //���˵�y�����ӵ��ĸ�
		int y2 = this.y + getHeight();   //���˵�y���ϵ��˵ĸ�
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
	// �ӵ�Խ������ɾ��
	public static boolean isRemove(littleHeroBullet[] bb) {
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
	 * �ӵ�����
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
	public int getx() {
		return x;
	}
	public int gety() {
		return y;
	}
	public BufferedImage getImage() {
		if (isLife()) {
			return img;
		}else {
			return null;
		}
	}
	

}
