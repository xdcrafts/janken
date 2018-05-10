package com.github.xdcrafts.janken.game.impl.ai;

import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.RoundAI;
import com.github.xdcrafts.janken.game.RoundResult;
import com.github.xdcrafts.janken.game.RoundRules;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simple predictive AI based on transitions frequency.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DumbPredictiveRoundAI<T extends Enum<T>> implements RoundAI<T> {

    private static final Random RANDOM = new Random();

    private final Map<T, Map<T, Integer>> transitions = new HashMap<>();
    private final RoundRules<T> rules;
    private final T[] figures;
    private RoundResult<T> previous = null;

    public DumbPredictiveRoundAI(Class<T> figuresEnum, RoundRules<T> rules) {
        this.figures = Objects.requireNonNull(figuresEnum, "'figuresEnum' should not be null").getEnumConstants();
        this.rules = Objects.requireNonNull(rules, "'rules' should not be null");
        final List<T> figuresList = Arrays.asList(figuresEnum.getEnumConstants());
        figuresList.forEach(first ->
            this.transitions.put(
                first,
                figuresList.stream().collect(Collectors.toMap(Function.identity(), o -> 0))
            )
        );
    }

    /**
     * Consume new result and update prediction model.
     */
    public void consume(RoundResult<T> result) {
        if (this.previous != null) {
            this.transitions
                .get(previous.getPlayerFigure())
                .merge(result.getPlayerFigure(), 1, Integer::sum);
        }
        this.previous = result;
    }

    private T getRandom() {
        return this.figures[RANDOM.nextInt(this.figures.length)];
    }

    @Override
    public T get() {
        if (this.previous != null) {
            return this.transitions
                .get(this.previous.getPlayerFigure())
                .entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .map(f -> this.rules.figuresOutcome(f, Outcome.LOSE))
                .map(l -> l.get(0))
                .orElse(getRandom());
        } else {
            return getRandom();
        }
    }
}
