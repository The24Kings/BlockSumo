package com.blocksumo.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerEventHandler implements Listener {
    @EventHandler
    public void arenaBounds(PlayerInteractEvent event) {
        if(event.getClickedBlock() == null) {
            return;
        }

        Location bounds1 = new Location(event.getClickedBlock().getWorld(), -16, -60, -16);
        Location bounds2 = new Location(event.getClickedBlock().getWorld(), 16, -10, 16);

        Location blockPos = event.getClickedBlock().getLocation();

        //If Outside X Bound of -16 and 16
        if(blockPos.getX() < bounds1.getX() || blockPos.getX() > bounds2.getX()) {
            event.setCancelled(true);
        }

        //If outside Z Bound of -16 and 16
        if(blockPos.getZ() < bounds1.getZ() || blockPos.getZ() > bounds2.getZ()) {
            event.setCancelled(true);
        }

        //If outside Y Bound of -60 and -10
        if(blockPos.getY() < bounds1.getY() || blockPos.getY() > bounds2.getY()) {
            event.setCancelled(true);
        }

    }
}
