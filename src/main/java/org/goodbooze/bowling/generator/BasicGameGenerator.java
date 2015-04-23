package org.goodbooze.bowling.generator;

import java.util.Random;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;
import org.goodbooze.bowling.games.TenPinGame;

public class BasicGameGenerator implements GameGenerator{

	// using mod function need to use 11 in order to 
	private static final int STRIKE_MOD = 10;
	
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
		int firstBall = randomPinGenerator(STRIKE_MOD);
		
		int secondBall = 0;
		
		int fillBall = 0;
		
		if (firstBall < 10) {
			secondBall = randomPinGenerator(STRIKE_MOD - firstBall);
		}
		
		int frameIndex = frame.getFrameNumber();
		if (frameIndex  == 10) {
			
			if (firstBall == 10) {
				secondBall = randomPinGenerator(STRIKE_MOD);
				
				if (secondBall == 10) {
					fillBall = randomPinGenerator(STRIKE_MOD);
				} else {
					fillBall = randomPinGenerator(STRIKE_MOD - secondBall);
				}
			} else if (firstBall + secondBall == 10){
				fillBall = randomPinGenerator(STRIKE_MOD);
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
