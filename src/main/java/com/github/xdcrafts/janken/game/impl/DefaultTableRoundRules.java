package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.RoundRules;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Implementation of round rules based on a table.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
public class DefaultTableRoundRules<T extends Enum<T>> implements RoundRules<T> {

    private final Map<T, Map<T, Outcome>> table;

    public DefaultTableRoundRules(Class<T> figuresEnum, Map<T, Map<T, Outcome>> table) {
        this.table = Objects.requireNonNull(table, "'table' should not be null");
        final T[] figures = Objects
            .requireNonNull(figuresEnum, "'figuresEnum' should not be null")
            .getEnumConstants();
        final List<T> figuresList = Arrays.asList(figures);
        final Map<T, T> missingRules = new TreeMap<>();
        figuresList.forEach(firstFigure -> {
            if (!table.containsKey(firstFigure)) {
                figuresList.forEach(secondFigure -> missingRules.put(firstFigure, secondFigure));
            } else {
                final Map<T, Outcome> outcomes = table.get(firstFigure);
                figuresList.forEach(secondFigure -> {
                    if (!outcomes.containsKey(secondFigure)) {
                        missingRules.put(firstFigure, secondFigure);
                    }
                });
            }
        });
        if (!missingRules.isEmpty()) {
            throw new IllegalArgumentException("'table' is not exhaustive, missed rules for: " + missingRules);
        }
    }

    @Override
    public Outcome compare(T first, T second) {
        return this.table.get(first).get(second);
    }
}
