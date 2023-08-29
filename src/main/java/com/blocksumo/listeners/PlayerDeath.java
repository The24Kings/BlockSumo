package com.blocksumo.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static com.blocksumo.BlockSumo.getPlugin;
import static com.blocksumo.BlockSumo.getWorld;

public class PlayerDeath implements Listener {
    private static Map<UUID, Integer> playerLives = new HashMap<>();
    private final Location respawnPoint = new Location(getWorld(), 0, -45, 0);
    static Logger logger = Bukkit.getLogger();

    public static void createPlayerList() {
        for(Player online : getWorld().getPlayers()) {
            playerLives.put(online.getUniqueId(), 5);
        }
    }

    public static Map<UUID, Integer> getAlivePlayers() {
        return playerLives;
    }

    public static Integer getPlayerLives(UUID uuid) {
        return playerLives.get(uuid);
    }

    public static void destroyPlayerList() {
        playerLives.clear();
    }

    private void zeroLivesLeft() {

    }

    //TODO: Respawn the player based on how to actual game does. Currently just a place holder.
    private void killPlayer(Player player, UUID identifier) {
        String name = player.getName();

        player.playSound(respawnPoint, Sound.BLOCK_NOTE_BLOCK_PLING,1,1);

        if(player.teleport(respawnPoint)) { player.setGameMode(GameMode.SPECTATOR); }

        int delay = 20 * 5; //5-Second respawn delay based on ticks
        Integer lives = playerLives.get(identifier);

        try {
            playerLives.replace(identifier, lives, lives - 1);

            getWorld().sendMessage(Component.text(name + " has " + getPlayerLives(identifier) + " live(s) remaining", NamedTextColor.RED));

            if(lives - 1 == 0) { //Keep player in spectator if they have 0 lives
                getWorld().sendMessage(Component.text(name + " lost.", NamedTextColor.RED));
                zeroLivesLeft();
                return;
            }

        } catch(IllegalArgumentException e) {
            Bukkit.getLogger().severe("Unable to decrement the lives of player" + name + "!");
        }

        //TODO: Respawn Counter
        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(new Location(getWorld(), 0, -50, 0));
        }, delay);
    }

    @EventHandler
    public void playerFallenInVoid (PlayerMoveEvent event) {
        Player player = event.getPlayer();
        UUID identifier = player.getUniqueId();

        // If player is above -60 or in spectator -> return
        if(player.getLocation().getY() > -60 || player.getGameMode() == GameMode.SPECTATOR) { return; }

        //TODO: team colors?
        getWorld().sendMessage(Component.text(player.getName(), NamedTextColor.RED).append(Component.text(" fell into the void.", NamedTextColor.WHITE)));

        killPlayer(player, identifier);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        UUID identifier = player.getUniqueId();

        //TODO: team colors?
        getWorld().sendMessage(Component.text(player.getName(), NamedTextColor.RED).append(Component.text(" has died.")));

        killPlayer(player, identifier);
    }
}
