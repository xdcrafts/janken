package com.github.xdcrafts.janken.game.impl.classic;

import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.impl.RulesTableBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Table with rules for classic rock-paper-scissors game.
 *
 * @author Vadim Dubs
 */
public final class ClassicRulesTable {

    private ClassicRulesTable() {
        // Nothing
    }

    public static final Map<ClassicFigure, Map<ClassicFigure, Outcome>> RULES_TABLE = Collections.unmodifiableMap(
        new RulesTableBuilder<ClassicFigure>(new TreeMap<>())
            // ROCK
            .addRule(ClassicFigure.ROCK, ClassicFigure.ROCK, Outcome.DRAW)
            .addRule(ClassicFigure.ROCK, ClassicFigure.PAPER, Outcome.LOSE)
            .addRule(ClassicFigure.ROCK, ClassicFigure.SCISSORS, Outcome.WIN)
            // PAPER
            .addRule(ClassicFigure.PAPER, ClassicFigure.ROCK, Outcome.WIN)
            .addRule(ClassicFigure.PAPER, ClassicFigure.PAPER, Outcome.DRAW)
            .addRule(ClassicFigure.PAPER, ClassicFigure.SCISSORS, Outcome.LOSE)
            // SCISSORS
            .addRule(ClassicFigure.SCISSORS, ClassicFigure.ROCK, Outcome.LOSE)
            .addRule(ClassicFigure.SCISSORS, ClassicFigure.PAPER, Outcome.WIN)
            .addRule(ClassicFigure.SCISSORS, ClassicFigure.SCISSORS, Outcome.DRAW)
            .get()
    );
}
