package com.blocksumo;

import com.blocksumo.commands.GameplayCommands;
import com.blocksumo.gameplay.PlayerEffects;
import com.blocksumo.listeners.PlayerDeath;
import com.blocksumo.listeners.PlayerOutOfBounds;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import static com.blocksumo.gameplay.InfoDisplay.createBoard;
import static com.blocksumo.gameplay.InfoDisplay.destroyBoard;
import static com.blocksumo.listeners.PlayerDeath.createPlayerList;
import static com.blocksumo.listeners.PlayerDeath.destroyPlayerList;

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

        registerCommandsAndEvents();

        //Create game related tools
        createPlayerList();
        createBoard();

        Bukkit.getLogger().info("Enabled Block Sumo!");
    }

    @Override
    public void onDisable() {
        destroyPlayerList();
        destroyBoard();
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
