package lejos.music;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of tracks
 * @author Alexandre Lombard
 */
public class Score {
	private List<Track> tracks = new ArrayList<>();

	/**
	 * Gets the tracks of the score
	 * @return the tracks
	 */
	public List<Track> getTracks() {
		return tracks;
	}

	/**
	 * Set the tracks of this score
	 * @param tracks the list of tracks
	 */
	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	/**
	 * Sets the BPM for all tracks
	 * @param bpm the BPM
	 */
	public void setBpm(int bpm) {
		for(Track track : this.tracks) {
			track.setBpm(bpm);
		}
	}
}
