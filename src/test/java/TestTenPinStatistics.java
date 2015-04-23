

import org.goodbooze.bowling.bowler.TenPinBowlerShotStatistics;
import org.goodbooze.bowling.exceptions.IllegalBowlerStatistics;
import org.junit.Assert;
import org.junit.Test;


public class TestTenPinStatistics {
	
	public static final String STAT_STRING = "10:63.0;9:16.5[1:96.0,0:4.0];8:9.8[2:78.0,1:20.0,0:2.0];7:6.5[3:65.0,2:13.0,1:20.0,0:2.0];6:2.0[4:60.0,3:15.0,2:13.0,1:10.0,0:2.0];5:1.0[5:50.0,4:10.0,3:15.0,2:13.0,1:10.0,0:2.0];4:0.3[6:50.0,5:6.0,4:4.0,3:15.0,2:13.0,1:10.0,0:2.0];3:0.5[7:45.0,6:5.0,5:6.0,4:4.0,3:15.0,2:13.0,1:10.0,0:2.0];2:0.2[8:40.0,7:10.0,6:0.0,5:6.0,4:4.0,3:15.0,2:13.0,1:10.0,0:2.0];1:0.1[9:30.0,8:15.0,7:5.0,6:0.0,5:6.0,4:4.0,3:15.0,2:13.0,1:10.0,0:2.0];0:0.1[10:50.0,9:30.0,8:10.0,7:5.0,6:0.0,5:1.0,4:1.0,3:1.0,2:1.0,1:1.0,0:0.0];";;

	@Test
	public void testAddFirstBallStats() {
		TenPinBowlerShotStatistics stats = new TenPinBowlerShotStatistics();
		
		try {
			stats.addFirstShotStatistic(-1, 0.0);
			// expected exception
			Assert.fail("Expected exception for pin count less than zero");
		} catch (IllegalBowlerStatistics e) {
			Assert.assertTrue("Expected exception for pin count less than zero", e.getMessage().contains("between 0 and 10"));
		}
		
		stats = new TenPinBowlerShotStatistics();
		
		try {
			stats.addFirstShotStatistic(11, 0.0);
			// expected exception
			Assert.fail("Expected exception for pin count greater than 10");
		} catch (IllegalBowlerStatistics e) {
			Assert.assertTrue("Expected exception for pin count greater than 10", e.getMessage().contains("between 0 and 10"));
		}
		
		stats = new TenPinBowlerShotStatistics();
		
		try {
			stats.addFirstShotStatistic(10, 90.0);
		} catch (IllegalBowlerStatistics e) {
			Assert.fail(e.getMessage());
		}
		
		try {
			Assert.assertTrue(stats.validate());
			Assert.fail("Expected exception for not 100 percent first ball");
		} catch (IllegalBowlerStatistics e) {
			Assert.assertTrue("Expected exception for not 100 percent first ball", e.getMessage().contains("100 percent"));
		}
		
		try {
			stats.addFirstShotStatistic(9, 10.0);
			stats.validate();
		} catch (IllegalBowlerStatistics e) {
			Assert.fail(e.getMessage());
		}
		
	}

	@Test
	public void testAddSecondBallStats() {
		TenPinBowlerShotStatistics stats = new TenPinBowlerShotStatistics();
		
		try {
			stats.addFirstShotStatistic(10, 90.0);
			stats.addFirstShotStatistic(9, 10);
			
			stats.addSecondShotStatistic(9, 2, 100.0);
			// expected exception
			Assert.fail("Expected exception for total pin count more than 10");
		} catch (IllegalBowlerStatistics e) {
			Assert.assertTrue("Expected exception for pin count less than zero", e.getMessage().contains("cannot exceed 10"));
		}
		
		stats = new TenPinBowlerShotStatistics();
		
		try {
			stats.addFirstShotStatistic(10, 90.0);
			stats.addFirstShotStatistic(9, 10);
			
			stats.addSecondShotStatistic(9, 1, 90.0);
			
			stats.validate();
			
			// expected exception
			Assert.fail("Expected exception for total pin count more than 10");
		} catch (IllegalBowlerStatistics e) {
			Assert.assertTrue("Expected exception for pin count less than zero", e.getMessage().contains("100 percent"));
		}
		
		stats = new TenPinBowlerShotStatistics();
		
		try {
			stats.addFirstShotStatistic(10, 90.0);
			stats.addFirstShotStatistic(8, 10);
			
			stats.addSecondShotStatistic(8, 2, 90.0);
			stats.addSecondShotStatistic(8, 1, 5.0);
			stats.addSecondShotStatistic(8, 0, 5.0);
			
			stats.validate();
			
		} catch (IllegalBowlerStatistics e) {
			Assert.fail("UnExpected exception" + e.getMessage());
		}
		
		
		
	}
	
	@Test
	public void testImportString() throws IllegalBowlerStatistics {
		TenPinBowlerShotStatistics stats = new TenPinBowlerShotStatistics();
		
		stats.importStatsString(STAT_STRING);
		
		stats.validate();
		
	}
	
}
