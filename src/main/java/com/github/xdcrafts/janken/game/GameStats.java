package com.github.xdcrafts.janken.game;

/**
 * Interface representing game stats.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public interface GameStats<T extends Enum<T>> {

    /**
     * Returns number of specified outcomes.
     */
    int numberOf(Outcome outcome);

    /**
     * Returns percentage of specified outcomes.
     */
    float percentOf(Outcome outcome);

    /**
     * Returns percentage of times when player thrown figure.
     */
    float percentOf(T figure);
}
