package com.blocksumo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static com.blocksumo.BlockSumo.getPlugin;
import static com.blocksumo.BlockSumo.getWorld;

public class playerDeath implements Listener {
    private static Map<UUID, Integer> playerLives = new HashMap<>();
    static Logger logger = Bukkit.getLogger();

    public static void createPlayerList() {
        for(Player online : getWorld().getPlayers()) {
            playerLives.put(online.getUniqueId(), 5);
        }

        logger.info("Created Map");
    }

    public static Map<UUID, Integer> getAlivePlayers() {
        return playerLives;
    }

    public static Integer getPlayerLives(UUID uuid) {
        return playerLives.get(uuid);
    }

    public static void destroyPlayerList() {
        playerLives.clear();
        logger.info("Successfully Deleted Map");
    }

    private void zeroLivesLeft() {

    }

    //TODO: Respawn the player based on how to actual game does. Currently just a place holder.
    private void killPlayer(Player player, UUID identifier) {
        Location respawnPoint = new Location(getWorld(), 0, -45, -1);
        String name = getPlugin().getServer().getPlayer(identifier).getName();

        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(respawnPoint);

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING,1,1);

        int delay = 20 * 5; //5-Second respawn delay based on ticks
        int lives = playerLives.get(identifier);

        try {
            playerLives.replace(identifier, lives, lives - 1);
            logger.info("Player: " + name + " now has " + playerLives.get(identifier) + " lives!");

            if(lives - 1 == 0) { //Keep player in spectator if they have 0 lives
                logger.info("Player has 0 lives left.");
                zeroLivesLeft();
                return;
            }

        } catch(NullPointerException e) {
            Bukkit.getLogger().warning("Unable to decrement the lives of player" + name + "!");
        }

        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            logger.info("Started Player Respawn Counter.");

            player.setGameMode(GameMode.SURVIVAL);
        }, delay);
    }

    @EventHandler
    public void playerFallenInVoid (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID identifier = player.getUniqueId();

        if(player.getLocation().getY() > -60) { //Trigger if player is below threshold of y -60
            return;
        }

        logger.info("Player is below threshold.");

        killPlayer(player, identifier);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        UUID identifier = player.getUniqueId();

        logger.info("Player died.");

        killPlayer(player, identifier);
    }
}
