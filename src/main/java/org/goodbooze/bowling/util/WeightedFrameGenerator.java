package org.goodbooze.bowling.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to generate a frame based on weighted averages of individual shots.
 * 
 * @author Jeff
 *
 */
public class WeightedFrameGenerator {

	private Map<Integer, Integer> firstShotWeighted = new HashMap<Integer, Integer>();
	
	private Map<Integer, Map<Integer, Integer>> secondShotsWeighted = new HashMap<Integer, Map<Integer, Integer>>();
	
	public void addFirstShotPinCount(int pinCount) {
		Integer index = firstShotWeighted.size();
		firstShotWeighted.put(index, Integer.valueOf(pinCount));
	}
	
	public void addSecondShotPinCount(int firstShotPinCount, int pinCount) {

		Map<Integer, Integer> firstShotMap = secondShotsWeighted.get(Integer.valueOf(firstShotPinCount));
		if (firstShotMap == null) {
			firstShotMap = new HashMap<Integer, Integer>();
			secondShotsWeighted.put(Integer.valueOf(firstShotPinCount), firstShotMap);
		}
		
		Integer index = firstShotMap.size();
		firstShotMap.put(index, Integer.valueOf(pinCount));
	}
	
	public int generateFirstBall() {
		return this.firstShotWeighted.get(getRandomIndex());
	}
	
	public int generateSecondBall(int firstBallCount) {
		Map<Integer, Integer> secondShotMap = this.secondShotsWeighted.get(Integer.valueOf(firstBallCount));
		
		// if for some reason we don't have stats, return zero pins
		if (secondShotMap == null) {
			return 0;
		}
		
		return secondShotMap.get(getRandomIndex());
	}
	

	private Integer getRandomIndex() {
		return Integer.valueOf((int) (1000 * Math.random()));
	}
	
}
