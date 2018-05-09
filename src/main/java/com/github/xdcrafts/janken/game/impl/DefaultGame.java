package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.Game;
import com.github.xdcrafts.janken.game.GameStats;
import com.github.xdcrafts.janken.game.Round;
import com.github.xdcrafts.janken.game.RoundResult;

import java.util.Objects;

/**
 * Default implementation of game.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultGame<T extends Enum<T>> implements Game<T> {

    private final DefaultGameStats<T> gameStats = new DefaultGameStats<>();
    private final Class<T> figuresEnum;
    private final Round<T> round;

    public DefaultGame(Class<T> figuresEnum, Round<T> round) {
        this.figuresEnum = Objects.requireNonNull(figuresEnum, "'figuresEnum' should not be null");
        this.round = Objects.requireNonNull(round, "'round' should not be null");
    }

    @Override
    public RoundResult<T> throwHand(T figure) {
        final RoundResult<T> roundResult = this.round.throwHand(figure);
        this.gameStats.append(roundResult);
        return roundResult;
    }

    @Override
    public GameStats<T> gameStats() {
        return this.gameStats;
    }

    @Override
    public Class<T> figuresEnum() {
        return this.figuresEnum;
    }
}
