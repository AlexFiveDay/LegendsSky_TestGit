package Story;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BackGround {
	private static BufferedImage image;
	static {
		image = readImage("background0.png");
	}
	private int width;
	private int height;
	private int x;
	private int y;
	private int speed;
	public BackGround() {
		width = 600;
		height = 900;
		x = 0;
		y = 0;
		speed = 1;
	}
	public void move() {
		System.out.println("���������ƶ�"+speed);
	}

	public void paintObject(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	public static  BufferedImage readImage(String fileName) {
		try {
			File f = new File("images/"+fileName); 
			BufferedImage img = ImageIO.read(f); //ͬ���ж�ȡͼƬ        
			return img;
		}catch(Exception e) {//catch�ļ��쳣�������ļ��������׳����쳣��Javaǿ�ƽ����쳣����
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
