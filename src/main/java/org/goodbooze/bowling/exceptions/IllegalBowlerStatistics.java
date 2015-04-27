package org.goodbooze.bowling.exceptions;

/**
 * Exception to be raised when a bowlers statistics are
 * not valid, such as first shot percentage is more than 100%.
 * 
 * @author Jeff
 *
 */
public class IllegalBowlerStatistics extends Exception {

	public IllegalBowlerStatistics(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
