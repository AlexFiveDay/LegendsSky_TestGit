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
	 * 敌机-玩家子弹碰撞检测
	 */
	public boolean hit(Enemy other) {
		int x1 = this.x - other.width;   //敌人的x减掉子弹的宽度
		int x2 = this.x + getWidth();    //敌人的x加上敌人的宽
		int y1 = this.y - other.height;  //敌人的y剪掉子弹的高
		int y2 = this.y + getHeight();   //敌人的y加上敌人的高
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
	// 子弹越界死亡删除
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
	 * 子弹死亡
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
