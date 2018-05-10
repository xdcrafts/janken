package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.Game;
import com.github.xdcrafts.janken.game.Round;
import com.github.xdcrafts.janken.game.RoundResult;
import com.github.xdcrafts.janken.game.RoundResultPublisher;

import java.util.Objects;

/**
 * Default implementation of game.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultGame<T extends Enum<T>> implements Game<T> {

    private final Class<T> figuresEnum;
    private final RoundResultPublisher<T> publisher;
    private final Round<T> round;

    public DefaultGame(Class<T> figuresEnum, Round<T> round, RoundResultPublisher<T> publisher) {
        this.figuresEnum = Objects.requireNonNull(figuresEnum, "'figuresEnum' should not be null");
        this.round = Objects.requireNonNull(round, "'round' should not be null");
        this.publisher = Objects.requireNonNull(publisher, "'publisher' should not be null");
    }

    @Override
    public RoundResult<T> throwHand(T figure) {
        final RoundResult<T> roundResult = this.round.throwHand(figure);
        publisher.publish(roundResult);
        return roundResult;
    }

    @Override
    public Class<T> figuresEnum() {
        return this.figuresEnum;
    }
}
