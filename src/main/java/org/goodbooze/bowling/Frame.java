package org.goodbooze.bowling;

import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;

public interface Frame {
	
	public void setFrameNumber(int frameNumber) throws InvalidFrameNumber;
	
	public void setFirstBall(int numberOfPins) throws TooManyPinsException;
	public void setSecondBall(int numberOfPins) throws TooManyPinsException;
	public void setFillBall(int numberOfPins)throws TooManyPinsException, NotTenthFrameException;
	
	public int getFirstBall();
	public int getSecondBall();
	public int getFillBall();
	
	public int getFrameNumber();
	
	public boolean isTenthFrame();
	public boolean isStrike();
	public boolean isSpare();
	public boolean hasCompletedFrame();

	public int getScore();
	public void setScore(int score);

}
