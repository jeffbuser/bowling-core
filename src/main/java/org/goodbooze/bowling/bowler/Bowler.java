package org.goodbooze.bowling.bowler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.goodbooze.bowling.Game;

/**
 * Basic domain class to represent a bowler.
 * 
 * @author Jeff
 *
 */
public class Bowler {
	
	private String bowlerId;
	private String bowlerName;
	private String usbcNumber;
	
	private Date lastModified;
	
	private List<Game> games;
	
	private double average;
	
	private TenPinBowlerShotStatistics shotStatistics;

	public String getBowlerId() {
		return bowlerId;
	}

	public void setBowlerId(String bowlerId) {
		this.bowlerId = bowlerId;
	}

	public String getBowlerName() {
		return bowlerName;
	}

	public void setBowlerName(String bowlerName) {
		this.bowlerName = bowlerName;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public TenPinBowlerShotStatistics getShotStatistics() {
		return shotStatistics;
	}

	public void setShotStatistics(TenPinBowlerShotStatistics shotStatistics) {
		this.shotStatistics = shotStatistics;
	}

	public List<Game> getGames() {
		return games;
	}

	public void addGame(Game game) {
		if (this.games == null) {
			this.games = new ArrayList<Game>();
		}
		this.games.add(game);
	}

	public String getUsbcNumber() {
		return usbcNumber;
	}

	public void setUsbcNumber(String usbcNumber) {
		this.usbcNumber = usbcNumber;
	}
}
