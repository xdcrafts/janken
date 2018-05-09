package com.github.xdcrafts.janken.game;

/**
 * Interface for rules.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public interface RoundRules<T extends Enum<T>> {

    /**
     * Compare two figures and detect if first player wins, loses or it is a draw.
     */
    Outcome compare(T first, T second);
}
