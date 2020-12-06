package com.nicholai.tradein.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Timer;
import java.util.TimerTask;

public class Timefly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Timer timer = new Timer();
        Player player = (Player) sender;
        class Helper extends TimerTask{
            @Override
            public void run() {
                player.setFlying(false);
                player.setAllowFlight(false);
                timer.cancel();
            }
        }
        if(!(sender instanceof Player)){ return true; }


        if (cmd.getName().equalsIgnoreCase("timefly")){
            int currentXP = player.getLevel();
            double timeSec = 0;
            try {
                timeSec = Integer.parseInt(args[0]);
            }catch(Exception e) {
                player.sendMessage("Unknown Parameter Error!");
                return true;
            }
            if (timeSec < 20) { return true; }
            double costXP = Math.ceil(timeSec/20.0);
            if (args.length == 2){
                if (args[1].equalsIgnoreCase("confirm")){
                    if (currentXP < (int) costXP){
                        player.sendMessage("You don't have enough! You need " + (int) costXP + " Levels to do this!");
                        return true;
                    } else {
                        player.setLevel(currentXP - (int) costXP);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        player.sendMessage("Now flying for " + timeSec + " Seconds, Enjoy!");
                        TimerTask task = new Helper();
                        timer.schedule(task, (int) timeSec * 1000, 10000);
                    }
                } else {
                    player.sendMessage("Unknown Parameter Error!");
                }
            } else {
                player.sendMessage("This will cost you " + (int) costXP + " Levels, confirm with /timefly " + (int)timeSec + " confirm");
            }
            //if(Integer.parseInt(args[0]) > currentXP || Integer.parseInt(args[0]) < 0){ return true; }
            //player.setLevel(currentXP-Integer.parseInt(args[0]));
            //Player target = Bukkit.getServer().getPlayer(args[1]);
            //target.setLevel(target.getLevel()+Integer.parseInt(args[0]));

        }
        return true;
    }
}
