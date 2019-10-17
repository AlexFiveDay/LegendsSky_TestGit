package Story;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.Thread.State;

import javax.imageio.ImageIO;

/**
 * ���Hero����������
 * 
 * @author �����
 *
 */
public abstract class Hero {
	public static final int SHOOTLEVE1 = 1;
	public static final int SHOOTLEVE2 = 2;
	public static final int SHOOTLEVE3 = 3;
	public static final int GENERAL = 4;
	public static final int INVINCEBLE = 8;
	public static final int INVINCEBLESTAR = 60;//�޵�40������
	public static final int UPHERO = 40;//��ǿӢ�ۻ� 
	public static final int LITTLEHERO = 80;//��ǿ�л�
	public static int shootState = SHOOTLEVE1;
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected int life;
	protected int fire;
	protected int xSpeed;
	protected int ySpeed;
	protected int score;
	protected int star;
	protected int state;//�ж��޵�״̬
//	boolean U, D, L, R;
	public Hero(int life, int fire, int xSpeed, int ySpeed) {
		width = 120;
		height = 80;
		x = 200;
		y = 500;
		this.life = life;
		this.fire = fire;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		score = 0;
		star = 0;
		state = GENERAL;
	}

	public int getLife() {
		return life;
	}

	/*
	 * Ӣ�ۻ����
	 */
	public Bullet[] shoot() {
		if (shootState == SHOOTLEVE1) {
			playerBullet[] pb = new playerBullet[2];
//			pb[0] = new playerBullet(this.x + width/2 - 6, this.y - 30, 13, 30);
			pb[0] = new playerBullet(this.x + width/4-6, this.y + height-90, 13, 30);/////////// �ӵ�����ʾλ��������
			pb[1] = new playerBullet(this.x + 3*width/4-6, this.y + height-90, 13, 30);
			return pb;
//			playerBullet pb = new playerBullet(this.x + this.width/2, this .y,13,30); 
//			return pb;
		} else if (shootState == SHOOTLEVE2) {
			playerBullet[] pb = new playerBullet[4];
			pb[0] = new playerBullet(this.x + width/4-6, this.y + height-90, 13, 30);/////////// �ӵ�����ʾλ��������
			pb[1] = new playerBullet(this.x + 3*width/4-6, this.y + height-90, 13, 30);
			pb[2] = new playerBullet(this.x -6, this.y + height, 13, 30);
			pb[3] = new playerBullet(this.x + width-6, this.y + height, 13, 30);
			return pb;
		} else if (shootState == SHOOTLEVE3) {
			playerBullet[] pb = new playerBullet[1];
			pb[0] = new playerBullet(this.x + this.width/2-6, this .y + height-90,13,30); 
			return pb;
		}
		return null;
	}
	
	/*
	 * Ӣ�ۻ��ӷ�
	 */
	public int score(int score) {
		return this.score += score;
	}
	/*
	 * Ӣ�ۻ��÷���ʾ
	 */
	public int getScore() {
		return score;
	}
	/*
	 * Ӣ�ۻ�������ʾ
	 */
	public int getStar() {
		return star;
	}
	public int setStar(int i) {
		return star -= i;
	}
	/*
	 * Ӣ�ۻ�����
	 */
	public int star() {
		return this.star++;
	}
	/*
	 * Ӣ�ۻ��ƶ�
	 */
	public void move() {
		if (startWorld.U && !startWorld.D && !startWorld.L && !startWorld.R) {
			this.y -= this.ySpeed;
		} else if (!startWorld.U && startWorld.D && !startWorld.L && !startWorld.R) {
			this.y += this.ySpeed;
		} else if (!startWorld.U && !startWorld.D && startWorld.L && !startWorld.R) {
			this.x -= this.xSpeed;
		} else if (!startWorld.U && !startWorld.D && !startWorld.L && startWorld.R) {
			this.x += this.xSpeed;
		} else if (startWorld.U && !startWorld.D && !startWorld.L && startWorld.R) {
			this.x += this.xSpeed;
			this.y -= this.ySpeed;
		} else if (!startWorld.U && startWorld.D && !startWorld.L && startWorld.R) {
			this.x += this.xSpeed;
			this.y += this.ySpeed;
		} else if (!startWorld.U && startWorld.D && startWorld.L && !startWorld.R) {
			this.x -= this.xSpeed;
			this.y += this.ySpeed;
		} else if (startWorld.U && !startWorld.D && startWorld.L && !startWorld.R) {
			this.x -= this.xSpeed;
			this.y -= this.ySpeed;
		}
		if(y<=0) {
			y=0;
		} else if (y>= startWorld.HEIGHT-this.height) {
			y=startWorld.HEIGHT-this.height;
		}
		if(x<=0) {
			x=0;
		} else if (x>=startWorld.WIDTH-this.width) {
			x = startWorld.WIDTH-this.width;
		}	
//		System.out.println(this.x+","+this.y);
	}
	
	/*
	 * Ӣ�ۻ�-�л���ײ���
	 */
	//thisΪӢ�ۻ���otherΪ����
//		public boolean hit(Enemy other) {
//			int x1 = this.x;   //���˵��ӵ�x
//			int x2 = this.x + this.width + other.getWidth();    //�ӵ���x�����ӵ���width����Ӣ�ۻ���x
//			int y1 = this.y;  //�ӵ���y
//			int y2 = this.y + this.height + other.getHeight();   //���˵�y���ϵ��˵ĸ�
//			int x = this.x;                 //�ӵ���x��
//			int y = this.y;                  //�ӵ���y��
//			System.out.println(x+","+y+","+x1+","+y1+","+x2+","+y2);
//			System.out.println(this.x+","+this.y);
//			System.out.println(111);
//			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
//		}
		
		public boolean hit(Enemy other) {
			int x1 = this.x - other.width;   //���˵�x�����ӵ��Ŀ��
			int x2 = this.x + this.width;    //���˵�x���ϵ��˵Ŀ�
			int y1 = this.y - other.height;  //���˵�y�����ӵ��ĸ�
			int y2 = this.y + this.height;   //���˵�y���ϵ��˵ĸ�
			int x = other.x;                 //�ӵ���x��
			int y = other.y;                 //�ӵ���y��
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		}
		public boolean hit(Star other) {
			int x1 = this.x - other.width;   //���˵�x�����ӵ��Ŀ��
			int x2 = this.x + this.width;    //���˵�x���ϵ��˵Ŀ�
			int y1 = this.y - other.height;  //���˵�y�����ӵ��ĸ�
			int y2 = this.y + this.height;   //���˵�y���ϵ��˵ĸ�
			int x = other.x;                 //�ӵ���x��
			int y = other.y;                 //�ӵ���y��
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		}

	public int getxSpeed() {
		return xSpeed;
	}


	public int getySpeed() {
		return ySpeed;
	}
	/*
	 * �ж�Ӣ�ۻ��Ƿ���
	 */
	public boolean isLife() {
		if (getLife()>0) {
			return true;
		}
		return false;
	}
	/*
	 * Ӣ�ۻ��������޵�
	 */
	public void goDead() {
		if (state == INVINCEBLE) {
			life-=0;
		} else if (state == GENERAL) {
			if (life > 0) {
				life--;
			}
		}
	}
	/*
	 * ��ײ��л���λ
	 */
	public void restart() {
		x = 100;
		y = 600;
	}
/*
 * ��ȡӢ�ۻ�����
 */
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/*
	 * �������״̬
	 */
	public int setShootState() {
		if (shootState <= 3) {
			return shootState++;
		}
		return shootState;
	}
	/*
	 * �����޵�״̬
	 */
	public int setState(int i) {
		return state = i;
	}
	/*
	 * ��ȡ����Ӣ�ۻ���ͼƬ
	 */
	public abstract BufferedImage getImage();

	/*
	 * ��ͼ
	 */
	public void paintObject(Graphics g) {
		g.drawImage(getImage(), x, y, null);
	}

	/*
	 * ��ͼ
	 */
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
