package com.github.xdcrafts.janken.game.classic;

import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.RoundRules;
import com.github.xdcrafts.janken.game.impl.DefaultTableRoundRules;
import com.github.xdcrafts.janken.game.impl.classic.ClassicFigure;
import com.github.xdcrafts.janken.game.impl.classic.ClassicRulesTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for classic rules.
 */
public class ClassicRulesTest {

    private static final RoundRules<ClassicFigure> RULES = new DefaultTableRoundRules<>(
        ClassicFigure.class, ClassicRulesTable.RULES_TABLE
    );

    @Test
    public void test() {
        assertEquals(Outcome.DRAW, RULES.compare(ClassicFigure.ROCK, ClassicFigure.ROCK));
        assertEquals(Outcome.LOSE, RULES.compare(ClassicFigure.ROCK, ClassicFigure.PAPER));
        assertEquals(Outcome.WIN, RULES.compare(ClassicFigure.ROCK, ClassicFigure.SCISSORS));

        assertEquals(Outcome.WIN, RULES.compare(ClassicFigure.PAPER, ClassicFigure.ROCK));
        assertEquals(Outcome.DRAW, RULES.compare(ClassicFigure.PAPER, ClassicFigure.PAPER));
        assertEquals(Outcome.LOSE, RULES.compare(ClassicFigure.PAPER, ClassicFigure.SCISSORS));

        assertEquals(Outcome.LOSE, RULES.compare(ClassicFigure.SCISSORS, ClassicFigure.ROCK));
        assertEquals(Outcome.WIN, RULES.compare(ClassicFigure.SCISSORS, ClassicFigure.PAPER));
        assertEquals(Outcome.DRAW, RULES.compare(ClassicFigure.SCISSORS, ClassicFigure.SCISSORS));
    }
}
