package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.GameStats;
import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.RoundResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Default game stats.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultGameStats<T extends Enum<T>> implements GameStats<T> {

    private long numberOfRounds = 0;
    private final Map<Object, Integer> counters = new HashMap<>();

    /**
     * Append new result and recalculate stats.
     */
    public void append(RoundResult<T> roundResult) {
        final Outcome outcome = roundResult.getOutcome();
        final T figure = roundResult.getPlayerFigure();
        this.counters.merge(outcome, 1, Integer::sum);
        this.counters.merge(figure, 1, Integer::sum);
        this.numberOfRounds++;
    }

    @Override
    public int numberOf(Outcome outcome) {
        return this.counters.getOrDefault(outcome, 0);
    }

    @Override
    public float percentOf(Outcome outcome) {
        return this.numberOfRounds == 0
            ? 0
            : this.counters.getOrDefault(outcome, 0).floatValue() / this.numberOfRounds * 100;
    }

    @Override
    public float percentOf(T figure) {
        return this.numberOfRounds == 0
            ? 0
            : this.counters.getOrDefault(figure, 0).floatValue() / this.numberOfRounds * 100;
    }
}
