package com.nicholai.tradein.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tradexp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(!(sender instanceof Player)){ return true; }


        if (cmd.getName().equalsIgnoreCase("tradexp")){
            int currentXP = player.getLevel();
            try {
                if (Integer.parseInt(args[0]) > currentXP || Integer.parseInt(args[0]) < 0) {
                    if (Integer.parseInt(args[0]) > currentXP) {
                        player.sendMessage("You don't have enough XP Levels to do this command!");
                    }
                    if (Integer.parseInt(args[0]) < 0) {
                        player.sendMessage("You cannot give negative XP Points!");
                    }
                    return true;
                }
            } catch(Exception e) {
                player.sendMessage("Unknown Parameter Error!");
                return true;
            }
            player.setLevel(currentXP-Integer.parseInt(args[0]));
            Player target = Bukkit.getServer().getPlayer(args[1]);
            target.setLevel(target.getLevel()+Integer.parseInt(args[0]));
            player.sendMessage("Sent " + (args[0]) + " XP Levels To " + (args[1]) + "!");
        }
        return true;
    }
}
