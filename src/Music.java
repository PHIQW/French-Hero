import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	
	public Music(){
		playSound();
	}
	
	private static Clip clip;

	public static void playSound() {

		// obtained from http://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
		// partially obtained from http://stackoverflow.com/questions/8979914/audio-clip-wont-loop-continuously
		try {

			if(OptionsGUI.getVolume()=="On"){
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav").getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
//				Thread.sleep(2000);
			}
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	public static void update(){
		
		if(OptionsGUI.getVolume() =="Off")
			clip.stop();
		else
			clip.start();
		
	}
	
}
