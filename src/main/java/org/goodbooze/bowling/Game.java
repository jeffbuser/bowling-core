package org.goodbooze.bowling;

import java.util.List;

/**
 * Interface for a bowling game.
 * 
 * @author Jeff
 *
 */
public interface Game {
	
	public int getScore();
	public int getScoreFrame();
	
	public List<Frame> getFrames();

}
