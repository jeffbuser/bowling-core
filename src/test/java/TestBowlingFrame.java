import org.goodbooze.bowling.Frame;
import org.goodbooze.bowling.exceptions.InvalidFrameNumber;
import org.goodbooze.bowling.exceptions.NotTenthFrameException;
import org.goodbooze.bowling.exceptions.TooManyPinsException;
import org.goodbooze.bowling.frames.TenPinFrame;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for the basic frame class
 * @author Jeff
 *
 */
public class TestBowlingFrame {
	
	@Test
	public void testFrameCreate() throws InvalidFrameNumber, TooManyPinsException {
		Frame frame = new TenPinFrame();
		frame.setFrameNumber(1);
		frame.setFirstBall(9);
		frame.setSecondBall(1);
	
		Assert.assertEquals("First ball set/get", 9, frame.getFirstBall());
		Assert.assertEquals("Second ball set/get", 1, frame.getSecondBall());
		
		Assert.assertFalse("Not 10th frame", frame.isTenthFrame());
	}
	
	@Test
	public void testFrameNumberBounds() {
		Frame frame = new TenPinFrame();
		
		try {
			frame.setFrameNumber(0);
			Assert.fail("Expected exception");
		} catch (InvalidFrameNumber e) {
			// expected exception
		}
		
		try {
			frame.setFrameNumber(11);
			Assert.fail("Expected exception");
		} catch (InvalidFrameNumber e) {
			// expected exception
		}
		
		try {
			frame.setFrameNumber(10);
			Assert.assertTrue("Frame should be isTenthFrame", frame.isTenthFrame());
		} catch (InvalidFrameNumber e) {
			Assert.fail("UnExpected exception");
		}
	}
	
	@Test
	public void testTenthFrame() {
		Frame frame = new TenPinFrame();
		
		try {
			frame.setFrameNumber(1);
			frame.setFillBall(10);
			Assert.fail("Expected exception");
		} catch (InvalidFrameNumber e) {
			Assert.fail("Expected NotTenthFrameException");
		} catch (TooManyPinsException e) {
			Assert.fail("Expected NotTenthFrameException");
		} catch (NotTenthFrameException e) {
			// expected exception
		}
		
		try {
			frame.setFrameNumber(10);
			frame.setFillBall(10);
			
			Assert.assertEquals("Fill ball set/get", 10, frame.getFillBall());
		} catch (InvalidFrameNumber e) {
			Assert.fail("Unexpected exception");
		} catch (TooManyPinsException e) {
			Assert.fail("Unexpected exception");
		} catch (NotTenthFrameException e) {
			Assert.fail("Unexpected exception");
		}
		
		
	}
	
	@Test
	public void testStrikeSpare() throws TooManyPinsException {
		Frame frame = new TenPinFrame();
		frame.setFirstBall(10);
		Assert.assertTrue("Is a strike", frame.isStrike());
		Assert.assertFalse("is not a spare", frame.isSpare());
		
		frame = new TenPinFrame();
		frame.setFirstBall(8);
		frame.setSecondBall(2);
		Assert.assertFalse("is not a strike", frame.isStrike());
		Assert.assertTrue("is a spare", frame.isSpare());
		
		frame = new TenPinFrame();
		frame.setFirstBall(5);
		frame.setSecondBall(4);
		Assert.assertFalse("is not a strike", frame.isStrike());
		Assert.assertFalse("is not a spare", frame.isSpare());
	}

}
