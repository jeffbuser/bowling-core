package org.goodbooze.bowling;

import java.util.List;

public interface Game {
	
	public int getScore();
	public int getScoreFrame();
	
	public List<Frame> getFrames();

}
