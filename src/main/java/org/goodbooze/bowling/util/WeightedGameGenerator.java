package org.goodbooze.bowling.util;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.TooManyPinsException;
import org.goodbooze.bowling.games.TenPinGame;
import org.goodbooze.bowling.generator.GameGenerator;

public class WeightedGameGenerator implements GameGenerator {

	private WeightedFrameGenerator frameGenerator;
	
	public WeightedGameGenerator(WeightedFrameGenerator frameGenerator) {
		this.frameGenerator = frameGenerator;
	}
	
	public Game generateGame() {
		TenPinGame game = new TenPinGame();
		
		for (Frame frame: game.getFrames()) {
			populateFrame(frame);
		}
				
		return game;
		
	}

	private void populateFrame(Frame frame) {
		if (frame.isTenthFrame()) {
			populateTenthFrame(frame);
			return;
		}
		
		try {
			frame.setFirstBall(this.frameGenerator.generateFirstBall());
			frame.setSecondBall(this.frameGenerator.generateSecondBall(frame.getFirstBall()));
		} catch (TooManyPinsException e) {
			throw new RuntimeException(e);
		}
	}

	private void populateTenthFrame(Frame frame) {
		
		try {
			frame.setFirstBall(this.frameGenerator.generateFirstBall());
			
			if (frame.getFirstBall() == 10) {
				frame.setSecondBall(this.frameGenerator.generateFirstBall());
				if (frame.getSecondBall() == 10) {
					frame.setFillBall(this.frameGenerator.generateFirstBall());
				} else {
					frame.setFillBall(this.frameGenerator.generateSecondBall(frame.getSecondBall()));
				}
			} else {
				frame.setSecondBall(this.frameGenerator.generateSecondBall(frame.getFirstBall()));
				if (frame.getFirstBall() + frame.getSecondBall() == 10) {
					frame.setFillBall(this.frameGenerator.generateFirstBall());
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		

	}
	
}
