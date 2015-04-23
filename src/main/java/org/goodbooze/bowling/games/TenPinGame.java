package org.goodbooze.bowling.games;

import java.util.ArrayList;
import java.util.List;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.frames.TenPinFrame;
import org.goodbooze.bowling.scoring.ScoringEngine;
import org.goodbooze.bowling.scoring.SimpleTenPinScoringEngine;

/**
 * Class to represent the "normal" game of bowling that is
 * sometimes refered to as ten pin bowling.
 * 
 * @author Jeff
 *
 */
public class TenPinGame implements Game {

	private List<Frame> frames = new ArrayList<Frame>(10);
	
	private int score = 0;
	private int scoreFrame = 1;
	
	private ScoringEngine scoringEngine;
	
	public TenPinGame() {
		for (int i=1; i<=10; i++) {
			Frame frame = new TenPinFrame();
			try {
				frame.setFrameNumber(i);
			} catch (InvalidFrameNumber e) {
				throw new RuntimeException(e);
			}
			frames.add(frame);
			
		}
	}
	
	public int getScore() { 
		if (this.getScoreFrame() != 10) {
			this.score = this.getScoringEngine().getScore(this.frames);
		}
		return this.score;
	}

	public int getScoreFrame() {
		return this.scoreFrame;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public ScoringEngine getScoringEngine() {
		if (this.scoringEngine == null) {
			this.scoringEngine = new SimpleTenPinScoringEngine();
		}
		return scoringEngine;
	}

	public void setScoringEngine(ScoringEngine scoringEngine) {
		this.scoringEngine = scoringEngine;
	}
	
	
	

}
