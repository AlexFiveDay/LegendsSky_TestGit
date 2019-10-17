package Story;

import java.io.File;
import java.io.RandomAccessFile;

public class ScoreRead {
	public static int[] read() {
		int[] a = new int[10];
		try {
			RandomAccessFile raf = new RandomAccessFile(new File("./src/Score.dat"), "r");
			raf.seek(0);
			for (int i = 0; i < raf.length()/4-1; i++) {
				a[i] = raf.readInt();
			}
//				System.out.println(raf.readInt());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	public static void main(String[] args) {
		int[] b = read();
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
	}
}
