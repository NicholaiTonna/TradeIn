package com.nicholai.tradein;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Tradein extends JavaPlugin {
    public static Tradein plugin;
    @Override
    public void onEnable(){
        plugin = this;
        FileConfiguration config = this.getConfig();
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (configFile.length() == 0){
            this.saveDefaultConfig();
            this.getConfig();
            config.addDefault("AllowTimeFlyCommand", true);
            config.addDefault("AllowTradeXpCommand", true);
            config.addDefault("AllowTradeInCommand", true);
            config.addDefault("MinecraftXpMechanism", false);
            config.options().copyDefaults(true);
            saveConfig();
        }
        this.saveDefaultConfig();
        this.getConfig();
        saveConfig();

        if (config.getBoolean("AllowTradeXpCommand")) {
            com.nicholai.tradein.commands.Tradexp commands = new com.nicholai.tradein.commands.Tradexp();
            getCommand("tradexp").setExecutor(commands);
        }
        if (config.getBoolean("AllowTimeFlyCommand")) {
            com.nicholai.tradein.commands.Timefly commands2 = new com.nicholai.tradein.commands.Timefly();
            getCommand("timefly").setExecutor(commands2);
        }
        if (config.getBoolean("AllowTradeInCommand")) {
            com.nicholai.tradein.commands.Tradein commands3 = new com.nicholai.tradein.commands.Tradein();
            getCommand("tradein").setExecutor(commands3);
        }
    }
    @Override
    public void onDisable(){ }
}
