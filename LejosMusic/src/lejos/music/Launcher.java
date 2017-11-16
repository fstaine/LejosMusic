package lejos.music;

import java.io.IOException;
import java.nio.ByteBuffer;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.network.BroadcastManager;
import lejos.network.BroadcastReceiver;

public class Launcher {
	public static final float DIVERGENCE = 0.05f; // 50 ms
	public static final float ERREUR_TOLERE = 1f; // 1s
	public static final float Dt = DIVERGENCE / (2 * ERREUR_TOLERE);
	private static boolean isConductor = true; 
	private static float prevTimeSent = 0;
	
	/**
	 * Starts a robot, according to the pressed button, a different track will be played
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		final TrackReader trackReader = new TrackReader();
		final BroadcastReceiver br = BroadcastReceiver.getInstance();
		
//		final Track violin1 = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score01/violin1.txt"));
//		final Track violin2 = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score01/violin2.txt"));
//		final Track violoncello = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score01/violoncello.txt"));
//		final Track contrabass = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score01/contrabass.txt"));
//	
//		violin1.setBpm(90);
//		violin2.setBpm(90);
//		violoncello.setBpm(90);
//		contrabass.setBpm(90);
//
//		br.addListener(violin1);
//		br.addListener(violin2);
//		br.addListener(violoncello);
//		br.addListener(contrabass);
//		
//		final int button = Button.waitForAnyPress();
//		
//		if(button == Button.ID_UP) {
//			playTrack(violin1);
//		} else if(button == Button.ID_RIGHT) {
//			playTrack(violin2);
//		} else if(button == Button.ID_LEFT) {
//			playTrack(violoncello);
//		} else if(button == Button.ID_DOWN) {
//			playTrack(contrabass);
//		}
		
		final Track t1 = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score02/track01.txt"));
		final Track t2 = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score02/track02.txt"));
		final Track t3 = trackReader.read(Launcher.class.getResourceAsStream("/lejos/music/samples/score02/track03.txt"));
		
		t1.setBpm(90);
		t2.setBpm(90);
		t3.setBpm(90);

		br.addListener(t1);
		br.addListener(t2);
		br.addListener(t3);
		
		final int button = Button.waitForAnyPress();
		
		if(button == Button.ID_UP) {
			playTrack(t1);
		} else if(button == Button.ID_RIGHT) {
			playTrack(t2);
		} else if(button == Button.ID_LEFT) {
			playTrack(t3);
		}
	}
	
	private static void playTrack(Track track) {	
		LCD.clear();
		LCD.drawString("Playing...", 0, 2);
		
		while(!track.isOver()) {
			LCD.drawString(String.format("%.4f", track.getTime()), 0, 3);
			if (isConductor) {
				maybeSendBoadcast(track);
			}
			track.play();
		}
	}
	
	private static void maybeSendBoadcast(Track track) {
		float time = track.getTime();
		if(prevTimeSent + Dt < time) {
			prevTimeSent = time;
			ByteBuffer message = ByteBuffer.allocate(4).putFloat(time);
			try {
				BroadcastManager.getInstance().broadcast(message.array());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
