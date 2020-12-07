package com.nicholai.tradein.commands;

import com.nicholai.tradein.Tradein;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Tradexp implements CommandExecutor {
    public static int getExpAtLevel(int level){
        if(level <= 16){
            return (int) (Math.pow(level,2) + 6*level);
        } else if(level <= 31){
            return (int) (2.5*Math.pow(level,2) - 40.5*level + 360.0);
        } else {
            return (int) (4.5*Math.pow(level,2) - 162.5*level + 2220.0);
        }
    }
    public static int xpToGive(Player player, int Level){
        int xpPlayerLevel = getExpAtLevel(player.getLevel());
        int xpReducedLevel = getExpAtLevel(player.getLevel() - Level);
        return xpPlayerLevel-xpReducedLevel;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if(!(sender instanceof Player)){ return true; }
        if (cmd.getName().equalsIgnoreCase("tradexp")){
            FileConfiguration config = Tradein.plugin.getConfig();
            if (config.getBoolean("MinecraftXpMechanism")){
                try {
                    if (Integer.parseInt(args[0]) > player.getLevel() || Integer.parseInt(args[0]) < 0) {
                        if (Integer.parseInt(args[0]) > player.getLevel()) {
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

                Player target = Bukkit.getServer().getPlayer(args[1]);
                if (player.getDisplayName().equalsIgnoreCase(target.getDisplayName())){
                    player.sendMessage("You cannot give XP to yourself!");
                    return true;
                }
                target.giveExp(xpToGive(player,Integer.parseInt(args[0])));
                player.setLevel(player.getLevel()-Integer.parseInt(args[0]));
                player.sendMessage("Sent XP Points To " + (args[1]) + "!");
            } else {
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
        }
        return true;
    }
}
