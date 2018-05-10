package com.github.xdcrafts.janken.shell;

import com.github.xdcrafts.janken.game.Game;
import com.github.xdcrafts.janken.game.GameStats;
import com.github.xdcrafts.janken.game.Outcome;
import com.github.xdcrafts.janken.game.RoundResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModelBuilder;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * Spring Shell Commands associated with Game.
 * @param <T> enumeration of game values, like 'rock', 'paper', 'scissors' in classic case.
 *
 * @author Vadim Dubs
 */
@ShellComponent
@SuppressWarnings("unchecked")
public class GameCommands<T extends Enum<T>> {

    @Autowired
    private Game<T> game;
    @Autowired
    private GameStats<T> gameStats;

    /**
     * Command rendering list of figures as table.
     */
    @ShellMethod(value = "Display a list of available figures.")
    public Table figures() {
        final TableModelBuilder tableModelBuilder = new TableModelBuilder<>()
            .addRow();
        this.game.figures().stream()
            .map(Enum::toString)
            .map(String::toLowerCase)
            .forEach(tableModelBuilder::addValue);
        return new TableBuilder(tableModelBuilder.build())
            .addFullBorder(BorderStyle.oldschool)
            .build();
    }

    /**
     * Command rendering current game stats.
     */
    @ShellMethod(value = "Display current game stats.")
    public Table stats() {
        final TableModelBuilder tableModelBuilder = new TableModelBuilder<>()
            .addRow()
            .addValue("WIN %");
        Arrays.stream(Outcome.values())
            .forEach(o -> tableModelBuilder.addValue(o + " #"));
        this.game.figures().stream()
            .map(Enum::toString)
            .map(f -> f + " %")
            .forEach(tableModelBuilder::addValue);
        tableModelBuilder
            .addRow()
            .addValue(this.gameStats.percentOf(Outcome.WIN));
        Arrays.stream(Outcome.values())
            .forEach(o -> tableModelBuilder.addValue(this.gameStats.numberOf(o)));
        this.game.figures().stream()
            .map(this.gameStats::percentOf)
            .forEach(tableModelBuilder::addValue);
        return new TableBuilder(tableModelBuilder.build())
            .addFullBorder(BorderStyle.oldschool)
            .build();
    }

    /**
     * Command that makes a next move.
     */
    @ShellMethod(key = "throw", value = "Throw your figure.")
    public String throwHand(String value) {
        final RoundResult<T> result = this.game.throwHand(value);
        return MessageFormat.format(
            "You: [{0}] <-> AI: [{1}] => {2}", result.getPlayerFigure(), result.getAiFigure(), result.getOutcome()
        );
    }

}
