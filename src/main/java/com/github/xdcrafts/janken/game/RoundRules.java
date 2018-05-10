package com.github.xdcrafts.janken.game;

import java.util.List;

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

    /**
     * Find a figures which will produce specified outcome against specified figure.
     */
    List<T> figuresOutcome(T figure, Outcome outcome);
}
