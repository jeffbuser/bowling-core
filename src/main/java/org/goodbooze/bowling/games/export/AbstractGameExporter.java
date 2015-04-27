package org.goodbooze.bowling.games.export;

import org.goodbooze.bowling.Game;

/**
 * Abstract class for game exporter classes to extend.
 * 
 * @author Jeff
 *
 * @param <T>
 */
public abstract class AbstractGameExporter<T> {
	
	public abstract T exportGame(Game exportGame);

}
