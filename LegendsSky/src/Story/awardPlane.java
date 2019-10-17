package Story;

import java.awt.image.BufferedImage;
import java.text.BreakIterator;

public class awardPlane extends Enemy {
	public static BufferedImage[] image;
	static {
		image = new BufferedImage[7];
		for (int i = 0; i < image.length; i++) {
			image[i] = readImage("awardPlane"+i+".png");
		}
	}
	public awardPlane() {
		super(1,1,2,3,60,40,3);
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

//	public BufferedImage getAwardType() {
//		if () {
//			
//		} else {
//
//		}
//		return null;
//	}

	@Override
	public int getScore() {
		return score;
	}


}
