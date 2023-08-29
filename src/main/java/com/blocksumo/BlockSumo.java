package com.blocksumo;

import com.blocksumo.commands.GameplayCommands;
import com.blocksumo.gameplay.PlayerEffects;
import com.blocksumo.listeners.PlayerDeath;
import com.blocksumo.listeners.PlayerOutOfBounds;
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
        getServer().getPluginManager().registerEvents(new PlayerOutOfBounds(), plugin);
        getServer().getPluginManager().registerEvents(new PlayerEffects(), plugin);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), plugin);

        getCommand("sumo").setExecutor(new GameplayCommands());
        getCommand("sumo").setTabCompleter(new GameplayCommands());
    }
}
