package Story;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ScoreWrite {
	private static RandomAccessFile raf;
	public static void write(int score) {
		try {
			raf = new RandomAccessFile(new File("./src/Score.dat"), "rw");
			int[] a = new int[11];
			raf.seek(40);
			raf.writeInt(score);
//			System.out.println("–¥»ÎÕÍ±œ"+raf.read());
			raf.seek(0);
			for (int j = 0; j < a.length; j++) {
				a[j] = raf.readInt();
			}
			Arrays.sort(a);
			raf.seek(0);
			for (int i = a.length-1; i > 0 ; i--) {
					raf.writeInt(a[i]);
			}
//			for (int j = 0; j < a.length; j++) {
//				 raf.writeInt(a[j]);
//			}
//			raf.seek(0);
//			for (int i = 0; i < raf.length()/4; i++) {
//				System.out.println(raf.readInt());
//			}
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		write(9);
	}
}
	
