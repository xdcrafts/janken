package com.github.xdcrafts.janken.game;

import java.util.function.Supplier;

/**
 * Interface for ai behavior.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public interface RoundAI<T extends Enum<T>> extends Supplier<T> {
}
