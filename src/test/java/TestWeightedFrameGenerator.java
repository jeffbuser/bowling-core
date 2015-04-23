import org.goodbooze.bowling.bowler.TenPinBowlerShotStatistics;
import org.goodbooze.bowling.exceptions.IllegalBowlerStatistics;
import org.goodbooze.bowling.util.WeightedFrameGenerator;
import org.goodbooze.bowling.util.WeightedFrameGeneratorFactory;
import org.junit.Assert;
import org.junit.Test;


public class TestWeightedFrameGenerator {

	@Test
	public void testWeightedFrameGeneratorFactory() throws IllegalBowlerStatistics {
		
		TenPinBowlerShotStatistics stats = new TenPinBowlerShotStatistics();
		stats.importStatsString(TestTenPinStatistics.STAT_STRING);
		stats.validate();
		
		WeightedFrameGenerator generator = WeightedFrameGeneratorFactory.getWeightedShotGenerator(stats);
		
		for (int i = 0; i < 10000; i++) {
			int firstBall = generator.generateFirstBall();
			int secondBall = generator.generateSecondBall(firstBall);
			System.out.println(firstBall + " - " + secondBall);
			Assert.assertTrue("first ball between 0 and 10", (firstBall >= 0) && (firstBall <= 10));
			Assert.assertTrue("first and second ball between 0 and 10", (firstBall + secondBall >= 0) && (firstBall + secondBall <= 10));
		}
	}
	
}
