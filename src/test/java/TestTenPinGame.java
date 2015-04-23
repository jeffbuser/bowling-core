import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.Game;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;
import org.goodbooze.bowling.frames.TenPinFrame;
import org.goodbooze.bowling.games.TenPinGame;
import org.junit.Assert;
import org.junit.Test;


/**
 * Tests the ten pin implementation of a bowling game 
 * @author Jeff
 *
 */
public class TestTenPinGame {

	@Test
	public void testAllNineOpenGameScoring() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
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
		
		Assert.assertEquals("Game should equal 90", 90, game.getScore());
		
	}
	
	@Test
	public void testRandomGameScoring() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,9,1,0));
		game.getFrames().set(1, getFrame(2,10,0,0));
		game.getFrames().set(2, getFrame(3,10,0,0));
		game.getFrames().set(3, getFrame(4,9,0,0));
		game.getFrames().set(4, getFrame(5,8,0,0));
		game.getFrames().set(5, getFrame(6,10,0,0));
		game.getFrames().set(6, getFrame(7,4,4,0));
		game.getFrames().set(7, getFrame(8,9,1,0));
		game.getFrames().set(8, getFrame(9,9,1,0));
		game.getFrames().set(9, getFrame(10,9,0,0));
		
		Assert.assertEquals("Game should equal 158", 158, game.getScore());
		
	}
	
	@Test
	public void test300GameScoring() throws InvalidFrameNumber, TooManyPinsException, NotTenthFrameException {
		
		Game game = new TenPinGame();
		
		game.getFrames().set(0, getFrame(1,10,0,0));
		game.getFrames().set(1, getFrame(2,10,0,0));
		game.getFrames().set(2, getFrame(3,10,0,0));
		game.getFrames().set(3, getFrame(4,10,0,0));
		game.getFrames().set(4, getFrame(5,10,0,0));
		game.getFrames().set(5, getFrame(6,10,0,0));
		game.getFrames().set(6, getFrame(7,10,0,0));
		game.getFrames().set(7, getFrame(8,10,0,0));
		game.getFrames().set(8, getFrame(9,10,0,0));
		game.getFrames().set(9, getFrame(10,10,10,10));
		
		Assert.assertEquals("Game should equal 300", 300, game.getScore());
		
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
