package com.blocksumo;

import com.blocksumo.commands.gameplayCommands;
import com.blocksumo.gameplay.playerEffects;
import com.blocksumo.listeners.playerDeath;
import com.blocksumo.listeners.playerOutOfBounds;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockSumo extends JavaPlugin {

    private static World world;
    private static BlockSumo plugin;

    public static BlockSumo getPlugin() {
        return plugin;
    }
    public static World getWorld() {
        return world;
    }

    @Override
    public void onEnable() {
        world = Bukkit.getWorld("Block Sumo");
        plugin = this;

        Bukkit.getLogger().info("Enabled Block Sumo!");

        registerCommandsAndEvents();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Disabled Block Sumo!");
    }

    private void registerCommandsAndEvents () {
        getServer().getPluginManager().registerEvents(new playerOutOfBounds(), plugin);
        getServer().getPluginManager().registerEvents(new playerEffects(), plugin);
        getServer().getPluginManager().registerEvents(new playerDeath(), plugin);

        getCommand("sumo").setExecutor(new gameplayCommands());
        getCommand("sumo").setTabCompleter(new gameplayCommands());
    }
}
