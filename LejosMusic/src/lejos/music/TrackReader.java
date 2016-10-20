package lejos.music;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads a track file
 * @author Alexandre Lombard
 *
 */
public class TrackReader {
	/**
	 * Read the track file from the input stream
	 * @param is the input stream
	 * @return the track
	 * @throws IOException
	 */
	public Track read(InputStream is) throws IOException {
		final Track track = new Track();
		
		try(final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while((line = br.readLine()) != null) {
				if(line.isEmpty() || line.startsWith("#"))
					continue;
				
				final String[] elements = line.split(" ");
				
				final String noteStr = elements[0];
				final String octaveStr = elements[1];
				final String lengthStr = elements[2];
				
				final float length = Float.parseFloat(lengthStr);
				
				if(noteStr.equals("0")) {
					// Special case: silence
					track.getNotes().add(new Note(0, length));
				} else {
					final int octave = Integer.parseInt(octaveStr);
					float baseFrequency = 0;
					
					switch(noteStr) {
					case "C":
						baseFrequency = Note.C0;
						break;
					case "CS":
					case "DB":
						baseFrequency = Note.C0S;
						break;
					case "D":
						baseFrequency = Note.D0;
						break;
					case "DS":
					case "EB":
						baseFrequency = Note.D0S;
						break;
					case "E":
						baseFrequency = Note.E0;
						break;
					case "F":
						baseFrequency = Note.F0;
						break;
					case "FS":
					case "GB":
						baseFrequency = Note.F0S;
						break;
					case "G":
						baseFrequency = Note.G0;
						break;
					case "GS":
					case "AB":
						baseFrequency = Note.G0S;
						break;
					case "A":
						baseFrequency = Note.A0;
						break;
					case "AS":
					case "BB":
						baseFrequency = Note.A0S;
						break;
					case "B":
						baseFrequency = Note.B0;
						break;
					}
					
					track.getNotes().add(new Note(Note.getFrequency(baseFrequency, octave), length));
				}
			}
		}
		
		return track;
	}
}
