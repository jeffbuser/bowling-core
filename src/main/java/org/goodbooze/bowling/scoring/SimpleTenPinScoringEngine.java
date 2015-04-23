package org.goodbooze.bowling.scoring;

import java.util.List;

import org.goodbooze.bowling.Frame;

public class SimpleTenPinScoringEngine implements ScoringEngine {

	public int getScore(List<Frame> frames) {
		return calculateScore(frames);
	}
	
	private int calculateScore(List<Frame> frames) {
		int score = 0;
		
		for(Frame currentFrame: frames) {
			
			if(currentFrame.getFrameNumber() == 10) {
				score = score + currentFrame.getFirstBall() + currentFrame.getSecondBall();
			
				if (currentFrame.isStrike() || currentFrame.isSpare()) {
					score = score + currentFrame.getFillBall();
				}
			} else {
				if (currentFrame.isStrike()) {
					score = score + 10 + getNextTwoShots(currentFrame.getFrameNumber(), frames);
				} else if (currentFrame.isSpare()) {
					score = score + 10 + getNextOneShot(currentFrame.getFrameNumber(), frames);
				} else {
					score = score + currentFrame.getFirstBall() + currentFrame.getSecondBall();
				}
			}
			
		}
		
		return score;
	}
	
	private int getNextOneShot(int startingAtFrame, List<Frame> frames) {
		return frames.get(startingAtFrame).getFirstBall();
	}
	
	private int getNextTwoShots(int startingAtFrame, List<Frame> frames) {
		Frame currentFrame = frames.get(startingAtFrame);
		if (currentFrame.isStrike() && !currentFrame.isTenthFrame()) {
			return 10 + getNextOneShot(startingAtFrame + 1, frames);
		}
		return currentFrame.getFirstBall() + currentFrame.getSecondBall();
	}

}
