package org.goodbooze.bowling.bowler;

import java.util.HashMap;
import java.util.Map;

import org.goodbooze.bowling.exceptions.IllegalBowlerStatistics;

/**
 * Domain class that represents the statistics for each shot of a ten pin game.
 * 
 * @author Jeff
 *
 */
public class TenPinBowlerShotStatistics {

	/**
	 * Statistics of the first shot where the key is the pin count and the value is the shot statistic object.
	 */
	private Map<Integer, ShotStatistic> firstShotStatistics = new HashMap<Integer, ShotStatistic>();

	/**
	 * Map of Maps.  First key is the pin count of the first ball, second key is the pin fall for the second ball
	 * and the statistic object for the second ball.
	 */
	private Map<Integer, Map<Integer, ShotStatistic>> secondShotStatistics = new HashMap<Integer, Map<Integer, ShotStatistic>>();

	public Map<Integer, ShotStatistic> getFirstShotStatistics() {
		return firstShotStatistics;
	}

	/**
	 * Adds a statistic for a first shot.
	 * <P>
	 * For example, if a bowler averages a strike half of the time, this method
	 * would be called with addFirstShotStatistic(10, 50.0d)
	 * 
	 * @param pinCount
	 * @param percentage
	 * @throws IllegalBowlerStatistics
	 */
	public void addFirstShotStatistic(int pinCount, double percentage)
			throws IllegalBowlerStatistics {

		// validate input
		if (pinCount < 0 || pinCount > 10) {
			throw new IllegalBowlerStatistics(
					"First ball count must be between 0 and 10.");
		} else if (percentage < 0.0d || percentage > 100.0d) {
			throw new IllegalBowlerStatistics(
					"Percentage must be between 0.0 and 100.0");
		}

		if (this.firstShotStatistics.containsKey(Integer.valueOf(pinCount))) {
			this.firstShotStatistics.remove(Integer.valueOf(pinCount));
		}
		this.firstShotStatistics.put(Integer.valueOf(pinCount),
				new ShotStatistic(pinCount, percentage));
	}

	public Map<Integer, ShotStatistic> getSecondShotStatistics(
			int firstBallPinCount) {
		return secondShotStatistics.get(Integer.valueOf(firstBallPinCount));
	}

	/**
	 * Adds a second shot statistic for a given first ball pin count.
	 * <p>
	 * For example if a bowler makes 95% of his/her single pin spares, this would be called
	 * with <b>addSecondShotStatistic(9, 1, 95.0d)</b>
	 * <p>
	 * and then to represent the missed single pins, <b>addSecondShotStatistic(9, 0, 5.0d)</b>
	 * 
	 * @param firstBallPinCount
	 * @param pinCount
	 * @param percentage
	 * @throws IllegalBowlerStatistics
	 */
	public void addSecondShotStatistic(int firstBallPinCount, int pinCount,
			double percentage) throws IllegalBowlerStatistics {

		// validate input
		if (firstBallPinCount < 0 || firstBallPinCount > 9) {
			throw new IllegalBowlerStatistics(
					"First ball count for second ball statistics must be between 0 and 9.");
		} else if (firstBallPinCount + pinCount > 10) {
			throw new IllegalBowlerStatistics(
					"Pin count sum for first and second ball cannot exceed 10.");
		} else if (percentage < 0.0d || percentage > 100.0d) {
			throw new IllegalBowlerStatistics(
					"Percentage must be between 0.0 and 100.0");
		}

		Map<Integer, ShotStatistic> secondBallStats = secondShotStatistics
				.get(Integer.valueOf(firstBallPinCount));

		if (secondBallStats == null) {
			secondBallStats = new HashMap<Integer, ShotStatistic>();
			this.secondShotStatistics.put(Integer.valueOf(firstBallPinCount), secondBallStats);
		}

		if (secondBallStats.containsKey(Integer.valueOf(pinCount))) {
			secondBallStats.remove(Integer.valueOf(pinCount));
		}
		secondBallStats.put(Integer.valueOf(pinCount), new ShotStatistic(
				pinCount, percentage));
	}

	/**
	 * Validates that all permutations of first shot statistics add up to 100% and that
	 * each permutation for second ball spares adds up to 100% as well.
	 * 
	 * @return true, otherwise throws exception
	 * @throws IllegalBowlerStatistics
	 */
	public boolean validate() throws IllegalBowlerStatistics {
		
		double firstShotPercentage = 0.0d;
		for (ShotStatistic stat: this.firstShotStatistics.values()) {
			firstShotPercentage += stat.getShotPercentage();
		}
		
		if (firstShotPercentage != 100.0d) {
			throw new IllegalBowlerStatistics("First shot percentage must add up to 100 percent, it is " + firstShotPercentage);
		}
		
		for (Integer firstShotPins: this.secondShotStatistics.keySet()) {
			double secondShotPercentage = 0;
			
			for (ShotStatistic stat: this.secondShotStatistics.get(firstShotPins).values()) {
				secondShotPercentage += stat.getShotPercentage();
			}
			
			if (secondShotPercentage != 100.0d) {
				throw new IllegalBowlerStatistics("Second shot percentage must add up to 100 percent for first ball pin count " + firstShotPins);
			}
		}
		
		return true;
		
	}
	
	public void importStatsString(String importString) throws IllegalBowlerStatistics {
		
		String[] firstShots = importString.split(";");
		
		for (int i=0; i < firstShots.length; i++) {
			String firstShot = firstShots[i].split("\\[")[0];
			
			int firstShotPins = getPinCount(firstShot);
			double firstShotPercentage =  getPercentage(firstShot);
			addFirstShotStatistic(firstShotPins, firstShotPercentage);

			if (firstShots[i].replace("\\]", "").split("\\[").length > 1) {
				String[] secondShots = firstShots[i].replaceAll("]", "").split("\\[")[1].split(",");
				for (int j=0; j < secondShots.length; j++) {
					String secondShot = secondShots[j];
					
					addSecondShotStatistic(firstShotPins, getPinCount(secondShot), getPercentage(secondShot));
				}
			}
		}
		
	}
	
	private int getPinCount(String singleStatString) {
		return Integer.valueOf(singleStatString.split(":")[0]);
	}
	
	private double getPercentage(String singleStatString) {
		return Double.valueOf(singleStatString.split(":")[1]);
	}

	/**
	 * Inner class to help encapsulate an individual pin statistic
	 * @author Jeff
	 *
	 */
	public class ShotStatistic {

		private int shotPins;

		private double shotPercentage;

		public ShotStatistic(int pinCount, double percentage) {
			this.shotPins = pinCount;
			this.shotPercentage = percentage;
		}

		public double getShotPercentage() {
			return shotPercentage;
		}

		public void setShotPercentage(double shotPercentage) {
			this.shotPercentage = shotPercentage;
		}

		public int getShotPins() {
			return shotPins;
		}

		public void setShotPins(int shotPins) {
			this.shotPins = shotPins;
		}

	}

}
