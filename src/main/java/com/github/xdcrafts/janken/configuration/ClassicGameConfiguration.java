package com.github.xdcrafts.janken.configuration;

import com.github.xdcrafts.janken.game.Game;
import com.github.xdcrafts.janken.game.GameStats;
import com.github.xdcrafts.janken.game.Round;
import com.github.xdcrafts.janken.game.RoundAI;
import com.github.xdcrafts.janken.game.RoundResultPublisher;
import com.github.xdcrafts.janken.game.RoundRules;
import com.github.xdcrafts.janken.game.impl.DefaultGame;
import com.github.xdcrafts.janken.game.impl.DefaultGameStats;
import com.github.xdcrafts.janken.game.impl.DefaultPublisher;
import com.github.xdcrafts.janken.game.impl.DefaultRound;
import com.github.xdcrafts.janken.game.impl.DefaultTableRoundRules;
import com.github.xdcrafts.janken.game.impl.classic.ClassicFigure;
import com.github.xdcrafts.janken.game.impl.classic.ClassicRulesTable;
import com.github.xdcrafts.janken.game.impl.ai.DumbPredictiveRoundAI;
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
     * Round.
     */
    @Bean
    public Round<ClassicFigure> classicRound(RoundRules<ClassicFigure> rules, RoundAI<ClassicFigure> roundAI) {
        return new DefaultRound<>(rules, roundAI);
    }

    /**
     * Publisher.
     */
    @Bean
    public RoundResultPublisher<ClassicFigure> publisher() {
        return new DefaultPublisher<>();
    }

    /**
     * Round AI.
     */
    @Bean
    public RoundAI<ClassicFigure> roundAI(
        RoundResultPublisher<ClassicFigure> publisher, RoundRules<ClassicFigure> rules
    ) {
        final DumbPredictiveRoundAI<ClassicFigure> ai = new DumbPredictiveRoundAI<>(ClassicFigure.class, rules);
        publisher.subscribe(ai::consume);
        return ai;
    }

    /**
     * Game.
     */
    @Bean
    public Game<ClassicFigure> classicGame(Round<ClassicFigure> round, RoundResultPublisher<ClassicFigure> publisher) {
        return new DefaultGame<>(ClassicFigure.class, round, publisher);
    }

    /**
     * Game stats.
     */
    @Bean
    public GameStats<ClassicFigure> classicGameStats(RoundResultPublisher<ClassicFigure> publisher) {
        final DefaultGameStats<ClassicFigure> stats = new DefaultGameStats<>();
        publisher.subscribe(stats::append);
        return stats;
    }
}
