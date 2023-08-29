package com.blocksumo.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static com.blocksumo.BlockSumo.getWorld;

public class PlayerOutOfBounds implements Listener {
    private final Location center = new Location(getWorld(), 0, -45, 0);

    public double calculateDistanceBetweenPoints(
            double x1,
            double z1,
            double x2,
            double z2) {
        return Math.sqrt((z2 - z1) * (z2 - z1) + (x2 - x1) * (x2 - x1));
    }

    @EventHandler
    public void cancelBlockPlacementOutOfBounds(BlockPlaceEvent event) {
        Location block = event.getBlockPlaced().getLocation();

        //Clamp to a 20 block diameter cylinder
        double distance = calculateDistanceBetweenPoints(0, 0, block.getX(), block.getZ());

        if(distance > 20.5) {
            event.setCancelled(true);
        }

        //Clamp to -60y -> -10y
        if(block.getY() < -60 || block.getY() > -10) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void teleportSpectatorOutOfBounds(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(player.getGameMode() != GameMode.SPECTATOR) { return; }

        //Clamp to a 32 block diameter cylinder
        double distance = calculateDistanceBetweenPoints(0, 0, player.getX(), player.getZ());

        if(distance > 32.5) {
            player.teleport(center);
        }

        //Clamp to -60y -> -10y
        if(player.getY() < -60 || player.getY() > -10) {
            player.teleport(center);
        }
    }
}
