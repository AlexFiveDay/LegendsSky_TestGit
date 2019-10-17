package Story;

import java.awt.image.BufferedImage;

public class generalPlane extends Enemy{
	public static BufferedImage[] image;
	static {
		image = new BufferedImage[7];
		for (int i = 0; i < image.length; i++) {
			image[i] = readImage("generalPlane"+i+".png");
		}
	}
	public generalPlane() {
		super(2,2,2,2,90,120,2);
	}

	public void move() {
		y += ySpeed;
//		x+=10*(Math.sin((double)(y/7.0))+0.05);
		
	}
	/*
	 * 获取普通敌机图片(non-Javadoc)
	 * @see Story.Enemy#getImage()
	 * @return 图片
	 */
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getScore() {
		
		return score;
	}


}
