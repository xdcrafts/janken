package com.github.xdcrafts.janken.game;

import java.util.function.Consumer;

/**
 * Interface for game results publisher.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public interface RoundResultPublisher<T extends Enum<T>> {

    /**
     * Publish and propagate result to all subscribers.
     */
    void publish(RoundResult<T> result);

    /**
     * Subscribe to round results.
     */
    void subscribe(Consumer<RoundResult<T>> consumer);
}
