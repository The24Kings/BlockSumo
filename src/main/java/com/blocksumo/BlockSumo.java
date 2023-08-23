package com.blocksumo;

import com.blocksumo.commands.gameplay;
import com.blocksumo.gameplay.playerEffects;
import com.blocksumo.listeners.PlayerEventHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockSumo extends JavaPlugin {

    private static World world;
    private static BlockSumo plugin;

    public static BlockSumo getPlugin() {
        return plugin;
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
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(), plugin);
        getServer().getPluginManager().registerEvents(new playerEffects(), plugin);

        getCommand("sumo").setExecutor(new gameplay());
        getCommand("sumo").setTabCompleter(new gameplay());
    }
}
