package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.RoundResult;
import com.github.xdcrafts.janken.game.Round;
import com.github.xdcrafts.janken.game.RoundAI;
import com.github.xdcrafts.janken.game.RoundRules;

import java.util.Objects;

/**
 * Class defining rock-paper-scissors-like game round against ai.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultRound<T extends Enum<T>> implements Round<T> {

    private final RoundRules<T> rules;
    private final RoundAI<T> roundAI;

    public DefaultRound(RoundRules<T> rules, RoundAI<T> roundAI) {
        this.rules = Objects.requireNonNull(rules, "'rules' should not be null");
        this.roundAI = Objects.requireNonNull(roundAI, "'roundAi' should not be null");
    }

    @Override
    public RoundResult<T> throwHand(T figure) {
        Objects.requireNonNull(figure, "'figure' should not be null");
        final T aiFigure = this.roundAI.get();
        final Outcome outcome = this.rules.compare(figure, aiFigure);
        return new RoundResult<>(figure, aiFigure, outcome);
    }
}
