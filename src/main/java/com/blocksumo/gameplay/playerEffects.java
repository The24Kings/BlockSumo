package com.blocksumo.gameplay;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class playerEffects implements Listener {
    private static Material getRandomWoolColor() throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();
        int num = random.nextInt(1, 17);

        switch (num) {
            case 1 -> {return Material.LIGHT_GRAY_WOOL;}
            case 2 -> {return Material.GRAY_WOOL;}
            case 3 -> {return Material.BLACK_WOOL;}
            case 4 -> {return Material.BROWN_WOOL;}
            case 5 -> {return Material.RED_WOOL;}
            case 6 -> {return Material.ORANGE_WOOL;}
            case 7 -> {return Material.YELLOW_WOOL;}
            case 8 -> {return Material.LIME_WOOL;}
            case 9 -> {return Material.GREEN_WOOL;}
            case 10 -> {return Material.CYAN_WOOL;}
            case 11 -> {return Material.LIGHT_BLUE_WOOL;}
            case 12 -> {return Material.BLUE_WOOL;}
            case 13 -> {return Material.PURPLE_WOOL;}
            case 14 -> {return Material.MAGENTA_WOOL;}
            case 15 -> {return Material.PINK_WOOL;}
            default -> {return Material.WHITE_WOOL;}
        }
    }

    @EventHandler
    public void placeWhiteWool(BlockPlaceEvent event) throws NoSuchAlgorithmException {
        Block block = event.getBlockPlaced();

        if(block.getType() != Material.WHITE_WOOL) {
            return;
        }

        block.setType(getRandomWoolColor());
    }

    @EventHandler
    public void onWalkOnWool(PlayerMoveEvent event) throws NoSuchAlgorithmException {
        Block block = event.getPlayer().getLocation().subtract(0,1,0).getBlock();

        if(!block.getType().toString().contains("WOOL")) {
            return;
        }

        if(!event.hasChangedBlock()) {
            return;
        }

        block.setType(getRandomWoolColor());
    }
}
