package com.nerdxd.plugin.presents;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("MobPresents plugin is now enabled...");
        getCommand("MobPresents").setExecutor(new MobPresentCommand());
        Bukkit.getPluginManager().registerEvents(new MobPresentCommand(), this);
    }

}
