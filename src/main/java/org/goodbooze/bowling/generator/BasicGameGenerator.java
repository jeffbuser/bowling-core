package org.goodbooze.bowling.generator;

import java.util.Random;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;
import org.goodbooze.bowling.games.TenPinGame;

public class BasicGameGenerator implements GameGenerator{

	// constant for number of pins in a rack 
	private static final int TOTAL_PINS = 10;
	
	public Game generateGame() {
		TenPinGame game = new TenPinGame();
		
		try {
			for (Frame frame: game.getFrames()) {
				populateFrame(frame);
			}
		} catch (TooManyPinsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFrameNumber e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotTenthFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return game;
	}

	public Frame populateFrame(Frame frame) throws TooManyPinsException, InvalidFrameNumber, NotTenthFrameException {
		int firstBall = randomPinGenerator(TOTAL_PINS);
		
		int secondBall = 0;
		
		int fillBall = 0;
		
		if (firstBall < 10) {
			secondBall = randomPinGenerator(TOTAL_PINS - firstBall);
		}
		
		int frameIndex = frame.getFrameNumber();
		if (frameIndex  == 10) {
			
			if (firstBall == 10) {
				secondBall = randomPinGenerator(TOTAL_PINS);
				
				if (secondBall == 10) {
					fillBall = randomPinGenerator(TOTAL_PINS);
				} else {
					fillBall = randomPinGenerator(TOTAL_PINS - secondBall);
				}
			} else if (firstBall + secondBall == 10){
				fillBall = randomPinGenerator(TOTAL_PINS);
			}
			
		}
		
		frame.setFrameNumber(frameIndex);
		frame.setFirstBall(firstBall);
		frame.setSecondBall(secondBall);
		
		if (frame.isTenthFrame()) {
			frame.setFillBall(fillBall);
		}
		
		return frame;
	}
	
	private int randomPinGenerator(int max) {
		int pins = (int) ((Math.random() * max) + 1);
		if (pins > max) {
			pins = max;
		}
		return pins;
	}
	
	

}
