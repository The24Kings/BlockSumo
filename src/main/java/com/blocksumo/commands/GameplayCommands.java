package com.blocksumo.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static com.blocksumo.BlockSumo.getPlugin;
import static com.blocksumo.BlockSumo.getWorld;
import static com.blocksumo.listeners.PlayerDeath.*;

public class GameplayCommands implements TabExecutor {
    static Logger logger = Bukkit.getLogger();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        if(args.length == 0) {
            return true;
        }

        if(label.equalsIgnoreCase("sumo")) {
            if(args[0].equalsIgnoreCase("start")) {
                logger.info("Game Started!");

                createPlayerList();
            }
            else if(args[0].equalsIgnoreCase("stop")) {
                logger.info("Game Stopped!");

                destroyPlayerList();
            }
            else if(args[0].equalsIgnoreCase("display")) {
                getWorld().sendMessage(Component.text("Current Players in list: "));

                for(UUID id : getAlivePlayers().keySet()) {
                    getWorld().sendMessage(Component.text(getPlugin().getServer().getPlayer(id).getName() + " : " + getPlayerLives(id)));
                }

            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> parameters = new ArrayList<>();

        if(args.length == 1) {
            parameters.add("start");
            parameters.add("stop");
            parameters.add("display");
        }

        return parameters;
    }
}
