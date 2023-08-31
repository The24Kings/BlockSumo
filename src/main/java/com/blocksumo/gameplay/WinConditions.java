package com.blocksumo.gameplay;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WinConditions implements Listener {
    public static boolean checkDeathCondition() {
        //TODO
        return true;
    }

    @EventHandler
    public void playerStandingOnGold(PlayerMoveEvent event) {
        Block block = event.getPlayer().getLocation().subtract(0,1,0).getBlock();

        if(!event.hasChangedBlock()) { return; }

        if(block.getType() != Material.GOLD_BLOCK) {
            return;
        }

        goldCountdown(event.getPlayer());
    }

    private void goldCountdown(Player player) {
        Block block = player.getLocation().subtract(0,1,0).getBlock();
        while(block.getType() == Material.GOLD_BLOCK) {
            continue; //TODO
        }

    }
}
