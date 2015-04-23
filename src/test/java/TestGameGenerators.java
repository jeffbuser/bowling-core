import java.math.BigDecimal;
import java.math.BigInteger;

import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.bowler.TenPinBowlerShotStatistics;
import org.goodbooze.bowling.exceptions.IllegalBowlerStatistics;
import org.goodbooze.bowling.games.export.CsvGameExporter;
import org.goodbooze.bowling.generator.BasicGameGenerator;
import org.goodbooze.bowling.generator.GameGenerator;
import org.goodbooze.bowling.util.WeightedFrameGenerator;
import org.goodbooze.bowling.util.WeightedFrameGeneratorFactory;
import org.goodbooze.bowling.util.WeightedGameGenerator;
import org.junit.Assert;
import org.junit.Test;

public class TestGameGenerators {

	private static final int NUM_OF_BULK_GAMES = 100000;

	@Test
	public void testGameGenerator() {
		BasicGameGenerator generator = new BasicGameGenerator();

		Game game = generator.generateGame();
		Assert.assertTrue("Game between 0 and 300", 0 <= game.getScore()
				&& game.getScore() <= 300);

		CsvGameExporter exporter = new CsvGameExporter();
		System.out.println(exporter.exportGame(game));
	}

	@Test
	public void testSimpleGameGeneratorMultiple() {
		BasicGameGenerator generator = new BasicGameGenerator();
		CsvGameExporter exporter = new CsvGameExporter();
		
		testGameGenerator(generator, exporter);
	}

	private void testGameGenerator(GameGenerator generator,
			CsvGameExporter exporter) {
		Game game = null;
		
		Game lowGame = null;
		Game highGame = null;
		
		int numberOfHighGames = 1;

		BigInteger totalPins = new BigInteger("0");
		
		for (int i = 0; i <= NUM_OF_BULK_GAMES; i++) {
			
			game = generator.generateGame();
			Assert.assertTrue("Game between 0 and 300", 0 <= game.getScore()
					&& game.getScore() <= 300);

			if (game.getScore() >= 200) {
				System.out.println(exporter.exportGame(game));
			}
			
			totalPins = totalPins.add(BigInteger.valueOf(game.getScore()));
			
			if (lowGame == null || game.getScore() < lowGame.getScore()) {
				lowGame = game;
			}
			
			if (highGame == null || game.getScore() > highGame.getScore()) {
				highGame = game;
				numberOfHighGames = 1;
			} else if (game.getScore() == highGame.getScore()) {
				numberOfHighGames++;
			}
			
		}
		System.out.println("Total Pins: " + totalPins.toString());
		System.out.println("Average Score: " + BigDecimal.valueOf(totalPins.longValue()).divide(BigDecimal.valueOf(NUM_OF_BULK_GAMES)));
		
		System.out.println("Low Game: " + lowGame.getScore());
		System.out.println("High Game: " + highGame.getScore() + " (" + numberOfHighGames + ")");
	}
	
	@Test
	public void testWeightedGameGeneratorMultiple() throws IllegalBowlerStatistics {
		
		TenPinBowlerShotStatistics stats = new TenPinBowlerShotStatistics();
		stats.importStatsString(TestTenPinStatistics.STAT_STRING);
		stats.validate();
		
		WeightedFrameGenerator frameGenerator = WeightedFrameGeneratorFactory.getWeightedShotGenerator(stats);
		
		GameGenerator generator = new WeightedGameGenerator(frameGenerator);
		CsvGameExporter exporter = new CsvGameExporter();
		
		testGameGenerator(generator, exporter);
	}
	

}
