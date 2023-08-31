package com.blocksumo.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.blocksumo.gameplay.InfoDisplay.*;

public class GameplayCommands implements TabExecutor {
    static Logger logger = Bukkit.getLogger();
    private boolean display_flag = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        if(args.length == 0) {
            return true;
        }

        if(label.equalsIgnoreCase("sumo")) {
            if(args[0].equalsIgnoreCase("start")) { //TODO: Game countdown

            }
            else if(args[0].equalsIgnoreCase("stop")) {

            }
            else if(args[0].equalsIgnoreCase("display")) {
                if(!display_flag)
                    showBoard();
                else
                    hideBoard();

                //Toggle the flag
                display_flag = !display_flag;
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
