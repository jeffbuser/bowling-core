package org.goodbooze.bowling.games.export;

import org.goodbooze.bowling.Game;

public abstract class AbstractGameExporter<T> {
	
	public abstract T exportGame(Game exportGame);

}
