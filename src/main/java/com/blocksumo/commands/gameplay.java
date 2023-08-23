package com.blocksumo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

import static com.blocksumo.BlockSumo.getPlugin;

public class gameplay implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        if(label.equalsIgnoreCase("sumo") && args[0] != null) {
            if(args[0].equalsIgnoreCase("start")) {
                //TODO
            }
            if(args[0].equalsIgnoreCase("stop")) {
                //TODO
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
        }

        return parameters;
    }
}
