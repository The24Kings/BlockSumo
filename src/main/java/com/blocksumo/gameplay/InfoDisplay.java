package com.blocksumo.gameplay;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import static com.blocksumo.BlockSumo.getPlugin;
import static com.blocksumo.BlockSumo.getWorld;
import static com.blocksumo.listeners.PlayerDeath.getAlivePlayers;

/** Scoreboard setup
 * BLOCK SUMO
 * ---------
 * Kills: <Number of Kills> <- TODO
 *
 * Sumo Lives: (updates from most -> least)
 * 24Kings <Number of Lives>
 * Etc.
 *
 */

public class InfoDisplay {
    final private static ScoreboardManager manager = Bukkit.getScoreboardManager();
    final private static Scoreboard board = manager.getNewScoreboard();
    final private Server server = getPlugin().getServer();

    //Title
    final private static Objective sumo_display = board.registerNewObjective(
            "player_lives",
            Criteria.DUMMY ,
            Component.text("Block Sumo")
    );

    //TODO: Make each player have a unique board using teams
    public static void createBoard() {
        sumo_display.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score;

        for(Player player : Bukkit.getOnlinePlayers()) {
             score = sumo_display.getScore(player);
             score.setScore(getAlivePlayers().get(player.getUniqueId()));
        }

        //Set the separator at the top of the board
        score = sumo_display.getScore("--------");
        score.setScore(10);

        score = sumo_display.getScore(" ");
        score.setScore(9);

        score = sumo_display.getScore("Kills: ");
        score.setScore(0);
    }

    public static void destroyBoard() {
        if(sumo_display.getScoreboard() == null) { return; }

        sumo_display.getScoreboard().getEntries().clear();
        hideBoard();
    }

    public static Objective getScoreboard() {
        return sumo_display;
    }

    public static void showBoard() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(board);
        }
    }

    public static void hideBoard() {
        for(Player player : getWorld().getPlayers()) {
            player.setScoreboard(manager.getNewScoreboard());
        }
    }

    public static void updatePlayerLife(Player player) {
        Score score = sumo_display.getScore(player);
        int lives = score.getScore() - 1;

        score.setScore(lives);
    }
}
