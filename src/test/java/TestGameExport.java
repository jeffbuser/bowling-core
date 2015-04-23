
import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;
import org.goodbooze.bowling.frames.TenPinFrame;
import org.goodbooze.bowling.games.TenPinGame;
import org.goodbooze.bowling.games.export.CsvGameExporter;
import org.junit.Assert;
import org.junit.Test;


public class TestGameExport {
	
	@Test
	public void testCsvExportNoMarks() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,9,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,9,0,0));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "90,90,90,90,90,90,90,90,90,90:90;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithSpare() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,9,1,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,9,0,0));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "9/,90,90,90,90,90,90,90,90,90:100;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithSpareInTenth() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,9,1,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,9,1,9));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "9/,90,90,90,90,90,90,90,90,9/9:110;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithStrike() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,9,0,0));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "X,90,90,90,90,90,90,90,90,90:100;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithStrikeInTenth() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,10,9,0));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "X,90,90,90,90,90,90,90,90,X90:110;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithDoubleInTenth() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,10,10,9));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "X,90,90,90,90,90,90,90,90,XX9:120;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithTripleInTenth() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,10,10,10));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "X,90,90,90,90,90,90,90,90,XXX:121;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithSpareStrikeInTenth() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,9,1,10));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "X,90,90,90,90,90,90,90,90,9/X:111;", gameExport);
		
	}
	
	@Test
	public void testCsvExportWithStrikeSpareInTenth() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,9,0,0));
		game.getFrames().set(2, getFrame(3,9,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,9,0,0));
		game.getFrames().set(5, getFrame(6,9,0,0));
		game.getFrames().set(6, getFrame(7,9,0,0));
		game.getFrames().set(7, getFrame(8,9,0,0));
		game.getFrames().set(8, getFrame(9,9,0,0));
		game.getFrames().set(9, getFrame(10,10,9,1));
		
		CsvGameExporter exporter = new CsvGameExporter();
		String gameExport = exporter.exportGame(game);
		
		Assert.assertEquals("Csv export failed", "X,90,90,90,90,90,90,90,90,X9/:111;", gameExport);
		
	}
	
	
	
	private Frame getFrame(int frameIndex, int firstBall, int secondBall, int fillBall) throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		Frame resultFrame = new TenPinFrame();
		
		resultFrame.setFrameNumber(frameIndex);
		resultFrame.setFirstBall(firstBall);
		resultFrame.setSecondBall(secondBall);
		
		if (frameIndex == 10 && firstBall + secondBall >= 10) {
			resultFrame.setFillBall(fillBall);
		}
		
		return resultFrame;
	}

}
