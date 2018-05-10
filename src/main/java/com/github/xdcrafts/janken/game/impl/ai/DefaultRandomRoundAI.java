package com.github.xdcrafts.janken.game.impl.ai;

import com.github.xdcrafts.janken.game.RoundAI;

import java.util.Objects;
import java.util.Random;

/**
 * Implementation of ai that always peeks random figure.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultRandomRoundAI<T extends Enum<T>> implements RoundAI<T> {

    private static final Random RANDOM = new Random();

    private final T[] figures;

    public DefaultRandomRoundAI(Class<T> figuresEnum) {
        this.figures = Objects
            .requireNonNull(figuresEnum, "'figuresEnum' should not be null")
            .getEnumConstants();
    }

    @Override
    public T get() {
        return this.figures[RANDOM.nextInt(this.figures.length)];
    }
}
