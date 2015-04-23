package org.goodbooze.bowling.games.export;

import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;

public class CsvGameExporter extends AbstractGameExporter<String>{

	@Override
	public String exportGame(Game exportGame) {
		StringBuilder gameString = new StringBuilder();
		
		for (Frame frame: exportGame.getFrames()) {
			gameString.append(getFrameString(frame));
			
			if(!frame.isTenthFrame()) {
				gameString.append(",");
			} 
		}
		
		gameString.append(":").append(exportGame.getScore());
		gameString.append(";");
		
		return gameString.toString();
	}
	
	private String getFrameString(Frame frame) {
		if (frame.isTenthFrame()) {
			return getTenthFrameString(frame);
		}
		if (frame.getFirstBall() == 10) {
			return "X";
		} else if (frame.getFirstBall() + frame.getSecondBall() == 10) {
			return frame.getFirstBall() + "/";
		} 

		return String.valueOf(frame.getFirstBall()) + String.valueOf(frame.getSecondBall());
	}

	private String getTenthFrameString(Frame frame) {
		StringBuilder tenthFrame = new StringBuilder();
		if (frame.getFirstBall() == 10) {
			tenthFrame.append("X");
			
			if (frame.getSecondBall() == 10) {
				tenthFrame.append("X");
				
				if (frame.getFillBall() == 10) {
					tenthFrame.append("X");
				} else {
					tenthFrame.append(frame.getFillBall());
				}
			} else {
				tenthFrame.append(frame.getSecondBall());
				if (frame.getSecondBall() + frame.getFillBall() == 10) {
					tenthFrame.append("/");
				} else {
					tenthFrame.append(frame.getFillBall());
				}
			}
		} else if (frame.getFirstBall() + frame.getSecondBall() == 10) {
			tenthFrame.append(frame.getFirstBall()).append("/");
			if(frame.getFillBall() == 10) {
				tenthFrame.append("X");
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
