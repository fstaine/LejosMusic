package lejos.network;

import java.util.Collection;

/**
 * Broadcast listener interface
 * @author Alexandre Lombard
 */
public interface BroadcastListener {
	/**
	 * Triggered on broadcast received
	 * @param message the raw message
	 */
	public void onBroadcastReceived(Collection<Float> clocks);
}
	