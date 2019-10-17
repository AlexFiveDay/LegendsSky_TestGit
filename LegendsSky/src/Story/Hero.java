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
 * 玩家Hero操作机超类
 * 
 * @author 杨大龙
 *
 */
public abstract class Hero {
	public static final int SHOOTLEVE1 = 1;
	public static final int SHOOTLEVE2 = 2;
	public static final int SHOOTLEVE3 = 3;
	public static final int GENERAL = 4;
	public static final int INVINCEBLE = 8;
	public static final int INVINCEBLESTAR = 60;//无敌40颗星星
	public static final int UPHERO = 40;//增强英雄机 
	public static final int LITTLEHERO = 80;//增强敌机
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
	protected int state;//判断无敌状态
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
	 * 英雄机射击
	 */
	public Bullet[] shoot() {
		if (shootState == SHOOTLEVE1) {
			playerBullet[] pb = new playerBullet[2];
//			pb[0] = new playerBullet(this.x + width/2 - 6, this.y - 30, 13, 30);
			pb[0] = new playerBullet(this.x + width/4-6, this.y + height-90, 13, 30);/////////// 子弹的显示位置有问题
			pb[1] = new playerBullet(this.x + 3*width/4-6, this.y + height-90, 13, 30);
			return pb;
//			playerBullet pb = new playerBullet(this.x + this.width/2, this .y,13,30); 
//			return pb;
		} else if (shootState == SHOOTLEVE2) {
			playerBullet[] pb = new playerBullet[4];
			pb[0] = new playerBullet(this.x + width/4-6, this.y + height-90, 13, 30);/////////// 子弹的显示位置有问题
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
	 * 英雄机加分
	 */
	public int score(int score) {
		return this.score += score;
	}
	/*
	 * 英雄机得分显示
	 */
	public int getScore() {
		return score;
	}
	/*
	 * 英雄机得星显示
	 */
	public int getStar() {
		return star;
	}
	public int setStar(int i) {
		return star -= i;
	}
	/*
	 * 英雄机加星
	 */
	public int star() {
		return this.star++;
	}
	/*
	 * 英雄机移动
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
	 * 英雄机-敌机碰撞检测
	 */
	//this为英雄机，other为敌人
//		public boolean hit(Enemy other) {
//			int x1 = this.x;   //敌人的子弹x
//			int x2 = this.x + this.width + other.getWidth();    //子弹的x加上子弹的width加上英雄机的x
//			int y1 = this.y;  //子弹的y
//			int y2 = this.y + this.height + other.getHeight();   //敌人的y加上敌人的高
//			int x = this.x;                 //子弹的x；
//			int y = this.y;                  //子弹的y；
//			System.out.println(x+","+y+","+x1+","+y1+","+x2+","+y2);
//			System.out.println(this.x+","+this.y);
//			System.out.println(111);
//			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
//		}
		
		public boolean hit(Enemy other) {
			int x1 = this.x - other.width;   //敌人的x减掉子弹的宽度
			int x2 = this.x + this.width;    //敌人的x加上敌人的宽
			int y1 = this.y - other.height;  //敌人的y剪掉子弹的高
			int y2 = this.y + this.height;   //敌人的y加上敌人的高
			int x = other.x;                 //子弹的x；
			int y = other.y;                 //子弹的y；
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		}
		public boolean hit(Star other) {
			int x1 = this.x - other.width;   //敌人的x减掉子弹的宽度
			int x2 = this.x + this.width;    //敌人的x加上敌人的宽
			int y1 = this.y - other.height;  //敌人的y剪掉子弹的高
			int y2 = this.y + this.height;   //敌人的y加上敌人的高
			int x = other.x;                 //子弹的x；
			int y = other.y;                 //子弹的y；
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		}

	public int getxSpeed() {
		return xSpeed;
	}


	public int getySpeed() {
		return ySpeed;
	}
	/*
	 * 判断英雄机是否存活
	 */
	public boolean isLife() {
		if (getLife()>0) {
			return true;
		}
		return false;
	}
	/*
	 * 英雄机减命和无敌
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
	 * 碰撞大敌机复位
	 */
	public void restart() {
		x = 100;
		y = 600;
	}
/*
 * 获取英雄机坐标
 */
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/*
	 * 设置射击状态
	 */
	public int setShootState() {
		if (shootState <= 3) {
			return shootState++;
		}
		return shootState;
	}
	/*
	 * 设置无敌状态
	 */
	public int setState(int i) {
		return state = i;
	}
	/*
	 * 获取各个英雄机的图片
	 */
	public abstract BufferedImage getImage();

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
			File f = new File("images/" + fileName);
			BufferedImage img = ImageIO.read(f); // 同包中读取图片
			return img;
		} catch (Exception e) {// catch文件异常处理。做文件操作容易出现异常。Java强制进行异常处理。
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
