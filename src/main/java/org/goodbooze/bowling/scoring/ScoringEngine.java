package org.goodbooze.bowling.scoring;

import java.util.List;

import org.goodbooze.bowling.Frame;

/**
 * Interface for a scoring engine.
 * 
 * @author Jeff
 *
 */
public interface ScoringEngine {
		
	public int getScore(List<Frame> frames);

}
