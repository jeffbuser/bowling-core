package org.goodbooze.bowling.generator;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;

public interface GameGenerator {
	
	public Game generateGame();
	
}
