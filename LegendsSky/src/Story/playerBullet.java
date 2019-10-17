package Story;

/**
 * 英雄机子弹
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
//			System.out.println("设计状态1");
			y -= 3*ySpeed ;
		}
		if (moveState == Hero.SHOOTLEVE2) {
//			System.out.println("设计状态2");
			y -= 3*ySpeed ;
//			x+=10*(Math.sin((double)(System.currentTimeMillis()/80.0))+0.05);
//			x += 100*(Math.sin((double)(y/0.9)));
		}
		if (moveState == Hero.SHOOTLEVE3) {
//			System.out.println("设计状态2");
			y -= 3*ySpeed ;
			x+=10*(Math.sin((double)(System.currentTimeMillis()/80.0))+0.05);
//			x += 100*(Math.sin((double)(y/0.9)));
		}
	}
	
	/*
	 * 敌机-玩家子弹碰撞检测
	 */
	public boolean hit(Enemy other) {
		int x1 = this.x - other.width;   //敌人的x减掉子弹的宽度
		int x2 = this.x + this.width;    //敌人的x加上敌人的宽
		int y1 = this.y - other.height;  //敌人的y剪掉子弹的高
		int y2 = this.y + this.height;   //敌人的y加上敌人的高
		int x = other.x;                 //子弹的x；
		int y = other.y;                 //子弹的y；
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
	}
	/*
	 * 判断子弹是否存活
	 */
	public boolean isLife() {
		if (life>0) {
			return true;
		}
		return false;
	}
	// 英雄机子弹越界死亡删除
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
	 * 敌机子弹死亡
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
