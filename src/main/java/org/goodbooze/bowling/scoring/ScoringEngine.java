package org.goodbooze.bowling.scoring;

import java.util.List;

import org.goodbooze.bowling.Frame;

public interface ScoringEngine {
		
	public int getScore(List<Frame> frames);

}
