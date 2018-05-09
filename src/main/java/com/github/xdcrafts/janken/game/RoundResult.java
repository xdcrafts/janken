package com.github.xdcrafts.janken.game;

import java.util.Objects;

/**
 * Aggregate representing game result.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class RoundResult<T extends Enum<T>> {

    private final T playerFigure;
    private final T aiFigure;
    private final Outcome outcome;

    public RoundResult(T playerFigure, T aiFigure, Outcome outcome) {
        this.playerFigure = Objects.requireNonNull(playerFigure, "'playerFigure' should not be null");
        this.aiFigure = Objects.requireNonNull(aiFigure, "'aiFigure' should not be null");
        this.outcome = Objects.requireNonNull(outcome, "'outcome' should not be null");
    }

    public T getPlayerFigure() {
        return playerFigure;
    }

    public T getAiFigure() {
        return aiFigure;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public String toString() {
        return "RoundResult{" +
                "playerFigure=" + playerFigure +
                ", aiFigure=" + aiFigure +
                ", outcome=" + outcome +
                '}';
    }
}
