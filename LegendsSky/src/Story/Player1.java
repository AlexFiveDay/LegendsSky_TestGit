package Story;

import java.awt.image.BufferedImage;

public class Player1 extends Hero{
	private static BufferedImage[] image;
	static {
		image = new BufferedImage[3];
		image[2] = readImage("player0.png");
		image[0] = readImage("hero0 (2).png");
		image[1] = readImage("hero0 (3).png");
	}
	public Player1() {
		super(20,3,3,3);
	}
//	public Bullet shoot() {
////		System.out.println(life);
//		playerBullet pb = new playerBullet(this.x + this.width/2, this .y,13,30); 
//		return pb;
//	}
	int i = 0;
	public BufferedImage getImage() {
		
		if (startWorld.INVINCIBLETIME) {
			return image[i++%3];
		}else {
			return image[2];
		}
	}
}
