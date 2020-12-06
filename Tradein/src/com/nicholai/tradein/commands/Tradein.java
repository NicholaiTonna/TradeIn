package com.nicholai.tradein.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tradein implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(!(sender instanceof Player)){ return true; }

        if (cmd.getName().equalsIgnoreCase("tradein")){
            if (args.length == 0){
                player.sendMessage("Type \"/tradein help\" for a list of commands and their usage or \"/tradein <command> help\" for help on a specific command");
            } else if (args.length == 1){
                if (args[0].equalsIgnoreCase("help")){
                    player.sendMessage("TradeIn Commands:");
                    player.sendMessage("/tradein help - Shows a list of commands and their usage");
                    player.sendMessage("/tradein <command> help - Shows the usage of a specific command");
                    player.sendMessage("/tradexp <xp> <player> - Gives the player the specified amount of XP levels from you");
                    player.sendMessage("/timefly <time> -  Calculates and gives you the price in XP levels it will cost for you to fly for the specified time. (20 seconds = 1 XP Level)");
                    player.sendMessage("/timefly <time> confirm -  Deducts the cost in XP Levels it takes to fly for the specified time and let's you fly");
                }
            } else if (args.length == 2){
                if (args[0].equalsIgnoreCase("help")){
                    player.sendMessage("(You provided more than 1 value, if you want to check the usage of a command, type in \"/tradein <command> help\")");
                    player.sendMessage("TradeIn Commands:");
                    player.sendMessage("/tradein help - Shows a list of commands and their usage");
                    player.sendMessage("/tradein <command> help - Shows the usage of a specific command");
                    player.sendMessage("/tradexp <xp> <player> - Gives the player the specified amount of XP levels from you");
                    player.sendMessage("/timefly <time> -  Calculates and gives you the price in XP levels it will cost for you to fly for the specified time. (20 seconds = 1 XP Level)");
                    player.sendMessage("/timefly <time> confirm -  Deducts the cost in XP Levels it takes to fly for the specified time and let's you fly");
                } else if (args[1].equalsIgnoreCase("help")){
                    if (args[0].equalsIgnoreCase("tradein")){
                        player.sendMessage("(You can also type in \"/tradein help\") instead of /tradein tradein help)");
                        player.sendMessage("TradeIn Commands:");
                        player.sendMessage("/tradein help - Shows a list of commands and their usage");
                        player.sendMessage("/tradein <command> help - Shows the usage of a specific command");
                        player.sendMessage("/tradexp <xp> <player> - Gives the player the specified amount of XP levels from you");
                        player.sendMessage("/timefly <time> -  Calculates and gives you the price in XP levels it will cost for you to fly for the specified time. (20 seconds = 1 XP Level)");
                        player.sendMessage("/timefly <time> confirm -  Deducts the cost in XP Levels it takes to fly for the specified time and let's you fly");
                    } else if (args[0].equalsIgnoreCase("tradexp")){
                        player.sendMessage("/tradexp <xp> <player> - Gives the player the specified amount of XP levels from you");
                    } else if (args[0].equalsIgnoreCase("timefly")) {
                        player.sendMessage("/timefly <time> -  Calculates and gives you the price in XP levels it will cost for you to fly for the specified time. (20 seconds = 1 XP Level)");
                        player.sendMessage("/timefly <time> confirm -  Deducts the cost in XP Levels it takes to fly for the specified time and let's you fly");
                    } else {
                        player.sendMessage("Error: Unknown value \"" + args[0] + "\"");
                    }
                } else {
                    player.sendMessage("Unknown Parameter Error!");
                }
            } else {
                player.sendMessage("Too many Values Error!");
            }
        }
        return true;
    }
}
