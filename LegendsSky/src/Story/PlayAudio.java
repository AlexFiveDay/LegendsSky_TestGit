package Story;
/**
 * “Ù–ß≤•∑≈
 */

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

public class PlayAudio {
	private AudioClip ac = null;
	
	public PlayAudio(String path) {
		try {
			URI uri = new File("./music/"+path).toURI();
			ac = Applet.newAudioClip(uri.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void playStart() {
		ac.play();
	}
	
	public void playSloop() {
		ac.loop();
	}
	
	public void playStop() {
		ac.stop();
	}
}
