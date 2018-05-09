package com.github.xdcrafts.janken.configuration;

import com.github.xdcrafts.janken.game.Game;
import com.github.xdcrafts.janken.game.Round;
import com.github.xdcrafts.janken.game.RoundAI;
import com.github.xdcrafts.janken.game.RoundRules;
import com.github.xdcrafts.janken.game.impl.DefaultGame;
import com.github.xdcrafts.janken.game.impl.DefaultRandomRoundAI;
import com.github.xdcrafts.janken.game.impl.DefaultRound;
import com.github.xdcrafts.janken.game.impl.DefaultTableRoundRules;
import com.github.xdcrafts.janken.game.impl.classic.ClassicFigure;
import com.github.xdcrafts.janken.game.impl.classic.ClassicRulesTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for classic rock-paper-scissors game.
 *
 * @author Vadim Dubs
 */
@Configuration
public class ClassicGameConfiguration {

    /**
     * Rules.
     */
    @Bean
    public RoundRules<ClassicFigure> classicRules() {
        return new DefaultTableRoundRules<>(ClassicFigure.class, ClassicRulesTable.RULES_TABLE);
    }

    /**
     * Round AI.
     */
    @Bean
    public RoundAI<ClassicFigure> classicRandomRoundAI() {
        return new DefaultRandomRoundAI<>(ClassicFigure.class);
    }

    /**
     * Round.
     */
    @Bean
    public Round<ClassicFigure> classicRound(RoundRules<ClassicFigure> rules, RoundAI<ClassicFigure> roundAI) {
        return new DefaultRound<>(rules, roundAI);
    }

    /**
     * Game.
     */
    @Bean
    public Game<ClassicFigure> classicGame(Round<ClassicFigure> round) {
        return new DefaultGame<>(ClassicFigure.class, round);
    }
}
