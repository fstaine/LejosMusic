package lejos.music;

import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Note representation
 * @author Alexandre Lombard
 */
public class Note {
	
	public static float C0 = 16.35f;
	public static float C0S = 17.32f;
	public static float D0 = 18.35f;
	public static float D0S = 19.45f;
	public static float E0 = 20.60f;
	public static float F0 = 21.83f;
	public static float F0S = 23.12f;
	public static float G0 = 24.50f;
	public static float G0S = 25.96f;
	public static float A0 = 27.50f;
	public static float A0S = 29.14f;
	public static float B0 = 30.87f;
	
	private int[] inst = Sound.FLUTE;
	private float freq;
	private float len;
	
	/**
	 * Builds a note with a frequency and a length
	 * @param freq the frequency
	 * @param len the length (1 for a whole note, 0.25 for a quarter, etc.)
	 */
	public Note(float freq, float len) {
		super();
		this.freq = freq;
		this.len = len;
	}
	
	/**
	 * Builds a note with an instrument, a frequency and a length
	 * @param inst the instrument
	 * @param freq the frequency
	 * @param len the length
	 */
	public Note(int[] inst, float freq, float len) {
		super();
		this.inst = inst;
		this.freq = freq;
		this.len = len;
	}

	/**
	 * Gets the instrument (default is piano)
	 * @return the instrument
	 */
	public int[] getInst() {
		return inst;
	}
	
	/**
	 * Sets the instrument
	 * @param inst the instrument
	 */
	public void setInst(int[] inst) {
		this.inst = inst;
	}
	
	/**
	 * Gets the frequency
	 * @return the frequency
	 */
	public float getFreq() {
		return freq;
	}
	
	/**
	 * Sets the frequency
	 * @param freq the frequency
	 */
	public void setFreq(float freq) {
		this.freq = freq;
	}
	
	/**
	 * Gets the length
	 * @return the length
	 */
	public float getLen() {
		return len;
	}
	
	/**
	 * Sets the length
	 * @param len the length
	 */
	public void setLen(float len) {
		this.len = len;
	}
	
	/**
	 * Plays the note with the given BPM (in quarter per minutes)
	 * @param bpm the BPM
	 */
	public void play(float bpm) {
		this.play(bpm, 1f);
	}
	
	/**
	 * Plays the note with the given BPM (in quarter per minutes) and transposition (0.5f an octave below, 2.0f an octave higher)
	 * @param bpm the BPM
	 * @param transpose the transposition value
	 */
	public void play(float bpm, float transpose) {
		if(this.freq == 0) {
			Delay.msDelay(lenToMillis(this.len, bpm));
		} else {
			Sound.playTone(
					(int)Math.round(this.freq * transpose),
					lenToMillis(this.len, bpm));
		}
	}
	
	/**
	 * Computes a frequency from a given reference note and a given octave
	 * @param ref the reference
	 * @param octave the octave
	 * @return the frequency
	 */
	public static float getFrequency(float ref, float octave) {
		return ref * (float)Math.pow(2, octave);
	}
	
	public static int lenToMillis(float len, float bpm) {
		return (int)Math.round(len * 4f * 60f / bpm * 1000f);
	}
	
	public static float millisToLen(int millis, float bpm) {
		return millis / (4f * 60f / bpm * 1000f);
	}
}
