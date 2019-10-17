package Story;

import java.awt.image.BufferedImage;
import java.util.Random;

public class specialPlane extends Enemy {
	public static BufferedImage[] image;
	static {
		image = new BufferedImage[7];
		for (int i = 0; i < image.length; i++) {
			image[i] = readImage("specialPlane"+i+".png");
		}
	}
	public specialPlane() {
		super(3,0,3,2,128,71,6);
	}
	
	public void move() {
			y += ySpeed;
		}
	int index = 0;
	public BufferedImage getImage() {
		if (isLife()) {
			return image[0];
		} else if (life<=0 && index < image.length) {
			BufferedImage img = image[index++];
			if(index == image.length) {
				life = -1;
			}
			return img;
		}
		return null;
	}
	public void enterWorld() {
		
		
	}

	@Override
	public int getScore() {
		return score;
	}
}
