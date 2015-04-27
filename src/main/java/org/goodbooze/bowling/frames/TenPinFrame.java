package org.goodbooze.bowling.frames;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;

/**
 * Frame class to be used for classic Ten Pin Game.
 * 
 * @author Jeff
 *
 */
public class TenPinFrame implements Frame {

	private int firstBall;
	private int secondBall;
	private int fillBall;
	
	private int frameNumber;
	private int score;
	private boolean hasCompleted;
	
	public void setFrameNumber(int frameNumber) throws InvalidFrameNumber {
		if (frameNumber <= 0 || frameNumber > 10) {
			throw new InvalidFrameNumber("Frame number " + frameNumber + "not a valid frame index.");
		}
		this.frameNumber = frameNumber;
	}
	
	public void setFirstBall(int numberOfPins) throws TooManyPinsException {
		this.firstBall = numberOfPins;
		
		if (this.isStrike() && !this.isTenthFrame()) {
			this.hasCompleted = true;
		}
	}

	public void setSecondBall(int numberOfPins) throws TooManyPinsException {
		this.secondBall = numberOfPins;
		
		if (this.isSpare() && !this.isTenthFrame()) {
			this.hasCompleted = true;
		}
	}

	public void setFillBall(int numberOfPins) throws TooManyPinsException,
			NotTenthFrameException {
		// fill ball is only valid for the 10th frame.
		if (!this.isTenthFrame()) {
			throw new NotTenthFrameException();
		}
		
		this.fillBall = numberOfPins;
		this.hasCompleted = true;
	}

	public int getFirstBall() {
		return this.firstBall;
	}

	public int getSecondBall() {
		return this.secondBall;
	}

	public int getFillBall() {
		return this.fillBall;
	}

	public boolean isTenthFrame() {
		if (this.frameNumber == 10) {
			return true;
		}
		return false;
	}

	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public boolean isStrike() {
		return this.getFirstBall() == 10;
	}

	public boolean isSpare() {
		return (this.getFirstBall() + this.getSecondBall() == 10) && !this.isStrike();
	}

	public boolean hasCompletedFrame() {
		if (isStrike() || isSpare()) {
			return true;
		}
		
		return this.hasCompleted;
		
	}

	public int getFrameNumber() {
		return this.frameNumber;
	}

}
