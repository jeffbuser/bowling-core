package org.goodbooze.bowling.util;

import java.util.Map;

import org.goodbooze.bowling.bowler.TenPinBowlerShotStatistics;
import org.goodbooze.bowling.bowler.TenPinBowlerShotStatistics.ShotStatistic;

/**
 * Factory class that creates a weighted frame generator that can be
 * used to generate a statistically weighted game given a bowlers statistics
 * @author Jeff
 *
 */
public class WeightedFrameGeneratorFactory {
	
	public static final int SEED_NUMBER = 1000;
	
	public static WeightedFrameGenerator getWeightedShotGenerator(TenPinBowlerShotStatistics statistics) {
		
		WeightedFrameGenerator generator = new WeightedFrameGenerator();
		
		for (Integer firstBallPins: statistics.getFirstShotStatistics().keySet()) {
			
			for (int i = 0; i < statistics.getFirstShotStatistics().get(firstBallPins).getShotPercentage() * 10; i++) {
				generator.addFirstShotPinCount(firstBallPins.intValue());
			}
			
			Map<Integer, ShotStatistic> secondShotStat = statistics.getSecondShotStatistics(firstBallPins);
			if (secondShotStat != null) {
				for (Integer secondShotPins: secondShotStat.keySet()) {
					
					for (int j = 0; j < secondShotStat.get(secondShotPins).getShotPercentage() * 10; j++) {
						generator.addSecondShotPinCount(firstBallPins.intValue(), secondShotPins);
					}
					
				}	
			}
			
			
		}
		
		return generator;
		
	}

}
