package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.RoundResult;
import com.github.xdcrafts.janken.game.RoundResultPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Interface for game results publisher.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultPublisher<T extends Enum<T>> implements RoundResultPublisher<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPublisher.class);

    private final List<Consumer<RoundResult<T>>> subscribers = new ArrayList<>();

    @Override
    public void publish(RoundResult<T> result) {
        this.subscribers.forEach(s -> {
            try {
                s.accept(result);
            } catch (Exception ex) {
                LOGGER.warn("Subscriber[{}] unable to process result={}", s, result, ex);
            }
        });
    }

    @Override
    public void subscribe(Consumer<RoundResult<T>> consumer) {
        if (!this.subscribers.contains(Objects.requireNonNull(consumer, "'consumer' should not be null"))) {
            this.subscribers.add(consumer);
        }
    }
}
