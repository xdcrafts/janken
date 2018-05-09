package com.github.xdcrafts.janken.game;

/**
 * Class defining rock-paper-scissors-like game round against ai.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public interface Round<T extends Enum<T>> {

    /**
     * Throw your figure and get the result with response from ai.
     */
    RoundResult<T> throwHand(T figure);
}
