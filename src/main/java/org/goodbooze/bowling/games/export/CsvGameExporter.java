package org.goodbooze.bowling.games.export;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;

/**
 * Class to export a bowling game to CSV format.
 * 
 * @author Jeff
 *
 */
public class CsvGameExporter extends AbstractGameExporter<String>{

	private static final String FRAME_SEPARATOR = ",";
	private static final String GAME_TERMINATOR = ";";
	private static final String SCORE_SEPARATOR = ":";
	private static final String SPARE_STRING = "/";
	private static final String STRIKE_STRING = "X";

	@Override
	public String exportGame(Game exportGame) {
		StringBuilder gameString = new StringBuilder();
		
		for (Frame frame: exportGame.getFrames()) {
			gameString.append(getFrameString(frame));
			
			if(!frame.isTenthFrame()) {
				gameString.append(FRAME_SEPARATOR);
			} 
		}
		
		gameString.append(SCORE_SEPARATOR).append(exportGame.getScore());
		gameString.append(GAME_TERMINATOR);
		
		return gameString.toString();
	}
	
	private String getFrameString(Frame frame) {
		if (frame.isTenthFrame()) {
			return getTenthFrameString(frame);
		}
		if (frame.getFirstBall() == 10) {
			return STRIKE_STRING;
		} else if (frame.getFirstBall() + frame.getSecondBall() == 10) {
			return frame.getFirstBall() + SPARE_STRING;
		} 

		return String.valueOf(frame.getFirstBall()) + String.valueOf(frame.getSecondBall());
	}

	private String getTenthFrameString(Frame frame) {
		StringBuilder tenthFrame = new StringBuilder();
		
		// TODO:  Clean this up...this is ugly!
		if (frame.getFirstBall() == 10) {
			tenthFrame.append(STRIKE_STRING);
			
			if (frame.getSecondBall() == 10) {
				tenthFrame.append(STRIKE_STRING);
				
				if (frame.getFillBall() == 10) {
					tenthFrame.append(STRIKE_STRING);
				} else {
					tenthFrame.append(frame.getFillBall());
				}
			} else {
				tenthFrame.append(frame.getSecondBall());
				if (frame.getSecondBall() + frame.getFillBall() == 10) {
					tenthFrame.append(SPARE_STRING);
				} else {
					tenthFrame.append(frame.getFillBall());
				}
			}
		} else if (frame.getFirstBall() + frame.getSecondBall() == 10) {
			tenthFrame.append(frame.getFirstBall()).append(SPARE_STRING);
			if(frame.getFillBall() == 10) {
				tenthFrame.append(STRIKE_STRING);
			} else {
				tenthFrame.append(frame.getFillBall());
			}
		} else {
			tenthFrame.append(frame.getFirstBall()).append(frame.getSecondBall());
		}
		
		return tenthFrame.toString();
	}

	private boolean needToPrintFillBall(Frame frame) {
		if(!frame.isTenthFrame()) {
			return false;
		}
	
		if (frame.isStrike() || frame.isSpare()) {
			return true;
		}
		
		return false;
	}

}
