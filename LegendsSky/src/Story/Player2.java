package Story;

import java.awt.image.BufferedImage;

public class Player2 extends Hero {
	private static BufferedImage image;
	static {
		image = readImage("player1.png");
	}
	public Player2() {
		super(3,4,2,2);
	}

	public BufferedImage getImage() {
		return image;
	}
}
