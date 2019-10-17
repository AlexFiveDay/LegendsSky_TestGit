package Story;

import java.awt.image.BufferedImage;

public class Player3 extends Hero {
	private static BufferedImage image;
	static {
		image = readImage("player2.png");
	}
	public Player3() {
		super(3,2,4,4);
	}

	public BufferedImage getImage() {
		return image;
	}
}
