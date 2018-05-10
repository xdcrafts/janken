package com.github.xdcrafts.janken.game;

import java.util.Arrays;
import java.util.List;

/**
 * Game interface.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public interface Game<T extends Enum<T>> extends Round<T> {

    /**
     * Returns figures enum class.
     */
    Class<T> figuresEnum();

    /**
     * Returns available figures.
     */
    default List<T> figures() {
        return Arrays.asList(figuresEnum().getEnumConstants());
    }

    /**
     * Throw your figure and get the result with response from ai.
     */
    default RoundResult<T> throwHand(String figure) {
        return throwHand(Enum.valueOf(figuresEnum(), figure.toUpperCase()));
    }
}
