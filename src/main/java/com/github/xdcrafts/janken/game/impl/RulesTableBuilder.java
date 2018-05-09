package com.github.xdcrafts.janken.game.impl;

import com.github.xdcrafts.janken.game.Outcome;

import java.util.Map;
import java.util.Objects;

/**
 * Builder-helper class for rules table.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
@SuppressWarnings("unchecked")
public class RulesTableBuilder<T extends Enum<T>> {

    private final Map<T, Map<T, Outcome>> rulesTable;

    public RulesTableBuilder(Map<T, Map<T, Outcome>> rulesTable) {
        this.rulesTable = Objects.requireNonNull(rulesTable, "'rulesTable' should not be null");
    }

    /**
     * Adds new rule.
     */
    public RulesTableBuilder<T> addRule(T first, T second, Outcome outcome) {
        final Map<T, Outcome> outcomeMap;
        if (this.rulesTable.containsKey(first)) {
            outcomeMap = this.rulesTable.get(first);
        } else {
            try {
                outcomeMap = this.rulesTable.getClass().newInstance();
                this.rulesTable.put(first, outcomeMap);
            } catch (IllegalAccessException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        outcomeMap.put(second, outcome);
        return this;
    }

    /**
     * Returns rules table.
     */
    public Map<T, Map<T, Outcome>> get() {
        return this.rulesTable;
    }
}
