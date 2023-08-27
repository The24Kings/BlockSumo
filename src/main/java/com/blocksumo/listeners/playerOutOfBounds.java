package com.blocksumo.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class playerOutOfBounds implements Listener {
    @EventHandler
    public void arenaBounds(BlockPlaceEvent event) {
        if(event.getPlayer() == null) {
            return;
        }

        World world = event.getBlockPlaced().getWorld();

        Location bounds1 = new Location(world, -16, -60, -16);
        Location bounds2 = new Location(world, 16, -10, 16);

        Location blockPos = event.getBlockPlaced().getLocation();

        //If Outside X Bound of -16 and 16
        if(blockPos.getX() < bounds1.getX() || blockPos.getX() > bounds2.getX()) {
            //Bukkit.getLogger().warn("OUT OF BOUNDS");
            event.setCancelled(true);
        }

        //If outside Z Bound of -16 and 16
        if(blockPos.getZ() < bounds1.getZ() || blockPos.getZ() > bounds2.getZ()) {
            //Bukkit.getLogger().warn("OUT OF BOUNDS");
            event.setCancelled(true);
        }

        //If outside Y Bound of -60 and -10
        if(blockPos.getY() < bounds1.getY() || blockPos.getY() > bounds2.getY()) {
            //Bukkit.getLogger().warn("OUT OF BOUNDS");
            if(blockPos.getY() > bounds2.getY()) {
                event.getPlayer().sendMessage(Component.text("Cannot build this high!").color(TextColor.color(0xE83B31)));
            }
            event.setCancelled(true);
        }

    }
}
