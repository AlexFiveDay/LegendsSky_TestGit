package Story;

import java.awt.image.BufferedImage;
import java.util.Random;

public class bossPlane extends Enemy {
	public static  int bossIndex;
	public static BufferedImage[] image;
	static {
		image = new BufferedImage[5];
		image[0] = readImage("bossPlane" + bossIndex + ".png");
		for (int i = 1; i < image.length; i++) {
			image[i] = readImage("bossPlane" + (i+4) + ".png");
		}
	}

	public bossPlane(int bossIndex) {
		super(350, 5, 2, 1, 300, 225, 100, (startWorld.WIDTH - 150) / 2, -225);
		image[0] = readImage("bossPlane" + bossIndex + ".png");
	}
	/*
	 * 重写敌机射击shoot
	 */

	public bossBullet[] bossShoot(int bossIndex) {
		// enemyBullet eb = new enemyBullet(this.x + this.width/2, this .y +
		// this.height,hx,hy,20,20);
		// return eb;
//		System.out.println(bossIndex);
		if (bossIndex == 0) {
			if (this.life >= 250) {
				bossBullet[] bos = new bossBullet[2];
				bos[0] = new bossBullet(this.x + width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				bos[1] = new bossBullet(this.x + 5*width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				return bos;
			} else if (this.life >= 150) {
				bossBullet[] bos = new bossBullet[5];
				bos[0] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,1);/////////// 子弹的显示位置有问题
				bos[1] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,2);
				bos[2] = new bossBullet(this.x + width / 2, this.y + height+20, 28, 28, this.life,0,2);
				bos[3] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,2);
				bos[4] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,1);
				return bos;
			}else{
					bossBullet[] bos = new bossBullet[10];
					bos[0] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,1);/////////// 子弹的显示位置有问题
					bos[1] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,1);
					bos[2] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,0,1);
					bos[3] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,1);
					bos[4] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,1);
					bos[5] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,1);/////////// 子弹的显示位置有问题
					bos[6] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,2);
					bos[7] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,0,3);
					bos[8] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,2);
					bos[9] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,1);
					return bos;
				}
		} else if (bossIndex == 1) {
			if (this.life >= 150) {
				bossBullet[] bos = new bossBullet[3];
				bos[0] = new bossBullet(this.x + width/6 - 9, this.y + height-30, 18, 65, this.life,0,2);
				bos[1] = new bossBullet(this.x + 5*width/6 - 9, this.y + height-30, 18, 65, this.life,0,2);
				bos[2] = new bossBullet(this.x + 3*width/6 - 9, this.y + height, 18, 65, this.life,0,2);
				return bos;
			} else {
				bossBullet[] bos = new bossBullet[5];
				bos[0] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,2);/////////// 子弹的显示位置有问题
				bos[1] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,2);
				bos[2] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,0,2);
				bos[3] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,2);
				bos[4] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,2);
				return bos;
			}
		} else if (bossIndex == 2) {
			if (this.life >= 150) {
				bossBullet[] bos = new bossBullet[2];
				bos[0] = new bossBullet(this.x + width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				bos[1] = new bossBullet(this.x + 5*width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				return bos;
			} else {
				bossBullet[] bos = new bossBullet[5];
				bos[0] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,2);/////////// 子弹的显示位置有问题
				bos[1] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,2);
				bos[2] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,0,2);
				bos[3] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,2);
				bos[4] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,2);
				return bos;
			}
		} else if (bossIndex == 3) {
			if (this.life >= 150) {
				bossBullet[] bos = new bossBullet[2];
				bos[0] = new bossBullet(this.x + width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				bos[1] = new bossBullet(this.x + 5*width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				return bos;
			} else {
				bossBullet[] bos = new bossBullet[5];
				bos[0] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,2);/////////// 子弹的显示位置有问题
				bos[1] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,2);
				bos[2] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,0,2);
				bos[3] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,2);
				bos[4] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,2);
				return bos;
			}
		} else {
			if (this.life >= 150) {
				bossBullet[] bos = new bossBullet[2];
				bos[0] = new bossBullet(this.x + width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				bos[1] = new bossBullet(this.x + 5*width/6 - 22, this.y + height, 45, 44, this.life,0,2);
				return bos;
			} else {
				bossBullet[] bos = new bossBullet[5];
				bos[0] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-2,2);/////////// 子弹的显示位置有问题
				bos[1] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,-1,2);
				bos[2] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,0,2);
				bos[3] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,1,2);
				bos[4] = new bossBullet(this.x + width / 2, this.y + height, 28, 28, this.life,2,2);
				return bos;
			}
		}
	}

	/*
	 * 重写敌机移动
	 */
	public void move() {
		while (this.y < 0) {
			this.y += ySpeed;
		}
		this.x += xSpeed;
		this.y += ySpeed;
		if (x + this.width > startWorld.WIDTH || x < 0) {
			xSpeed *= -1;
		}
		if (y < 0 || y + this.height >= startWorld.HEIGHT / 2) {
			ySpeed *= -1;
		}
	}

	/*
	 * 敌机死亡掉落小星星
	 */
	public Star[] fallingStar() {
		Random ran = new Random();
		int i = ran.nextInt(20) + 20;
		Star[] star = new Star[i];
		for (int k = 0; k < star.length; k++) {
			star[k] = new Star(this.x + ran.nextInt(width), this.y + ran.nextInt(height), 40, 42);
		}
		return star;
	}

	int index = 0;

	public BufferedImage getImage() {
		if (isLife()) {
			return image[0];
		} else if (life <= 0 && index < image.length) {
			BufferedImage img = image[index++];
			if (index == image.length) {
				life = -1;
			}
			return img;
		}
		return null;

	}

	public void enterWorld() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getScore() {

		return score;
	}
}
