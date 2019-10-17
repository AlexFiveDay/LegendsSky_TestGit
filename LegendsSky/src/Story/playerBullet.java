package Story;

/**
 * Ӣ�ۻ��ӵ�
 */
import java.awt.image.BufferedImage;


public class playerBullet extends Bullet {
	public static BufferedImage[] img;
	static {
		img = new BufferedImage[2];
		img[0] = readImage("playerBullet0.png");
		img[1] = readImage("playerBullet1.png");
	}
	public playerBullet(int x, int y,int width,int height) {
		super(x, y,13,30);
	}
	public void move(int moveState) {
		//y -= 3*ySpeed ;
		if (moveState == Hero.SHOOTLEVE1) {
//			System.out.println("���״̬1");
			y -= 3*ySpeed ;
		}
		if (moveState == Hero.SHOOTLEVE2) {
//			System.out.println("���״̬2");
			y -= 3*ySpeed ;
//			x+=10*(Math.sin((double)(System.currentTimeMillis()/80.0))+0.05);
//			x += 100*(Math.sin((double)(y/0.9)));
		}
		if (moveState == Hero.SHOOTLEVE3) {
//			System.out.println("���״̬2");
			y -= 3*ySpeed ;
			x+=10*(Math.sin((double)(System.currentTimeMillis()/80.0))+0.05);
//			x += 100*(Math.sin((double)(y/0.9)));
		}
	}
	
	/*
	 * �л�-����ӵ���ײ���
	 */
	public boolean hit(Enemy other) {
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
	// Ӣ�ۻ��ӵ�Խ������ɾ��
	public static boolean isRemove(playerBullet[] bb) {
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
			return img[i++ % img.length];
		} else {
//			if (life <= 0 && i < img.length) {
//				return img[i++ % img.length];
//			}
			return null;
		}
	}
}
