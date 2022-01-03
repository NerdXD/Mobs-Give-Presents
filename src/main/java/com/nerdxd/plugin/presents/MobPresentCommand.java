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

    //this event fires when an entity has been damaged by another entity
    @EventHandler
    public void onEntityHit(EntityDamageByEntityEvent e) {
        //checking if the entity that was hit is the player
        if(e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();

            // from mats we are getting a random item from mc using this piece of code also we are picking a random number from the length of however long the mats array is
            Material rmats = mats[ran.nextInt(mats.length)];

            //picking a random number for the probability below
            int oran = ran.nextInt(10)+1;

            //Checking if the item contains comand or dead and checking if it is air
            while(rmats.isAir() || rmats.name().toLowerCase().contains("command")
                    || rmats.name().toLowerCase().contains("dead")) {
                //rerolling the random material
                rmats = mats[ran.nextInt(mats.length)];

            }

            /*
            just a bit of probability there is a 30% chance that you will get coal
            I am sure there is a much smarter way to do this but I am bad at math lol
             */
            if(oran == 3 || oran == 5 || oran == 7) {
                //if the var up above got any of these numbers (that var picks numbers random from 1 - 10) this will run
                player.sendMessage(ChatColor.RED + "IMAGINE GETTING COAL LMAO");
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(Material.COAL, 1));
            } else {
                //if the var doesn't get the number up above then the player will get a present

                /*
                also if you wanted to actually put the item in the player's inventory
                instead of just dropping it at the player's location you can do player#getInventory()#addItem()
                I believe
                 */

                player.sendMessage(ChatColor.GREEN + "You got a present!");
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(rmats, 1));
            }



        }

    }

}
