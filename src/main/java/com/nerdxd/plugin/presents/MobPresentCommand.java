package com.nerdxd.plugin.presents;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MobPresentCommand implements CommandExecutor, Listener {

    private Material[] mats = Material.values();

    private Random ran = new Random();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.GREEN + "You have Activated the Command!!!");
        } else {
            System.out.println("YOU ARE NOT A PLAYER!!!");
        }
        return false;
    }

    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            Material rmats = mats[ran.nextInt(mats.length)];
            int oran = ran.nextInt(10)+1;

            while(rmats.isAir() || rmats.name().toLowerCase().contains("command")
                    || rmats.name().toLowerCase().contains("dead")) {
                rmats = mats[ran.nextInt(mats.length)];

            }

            if(oran == 3 || oran == 5 || oran == 7) {
                player.sendMessage(ChatColor.RED + "IMAGINE GETTING COAL LMAO");
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.COAL, 1));
            } else {
                player.sendMessage(ChatColor.GREEN + "You got a present!");
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(rmats, 1));
            }



        }

    }

}
