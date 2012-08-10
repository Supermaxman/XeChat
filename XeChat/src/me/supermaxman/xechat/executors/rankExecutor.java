package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class rankExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	if(args.length>0){
    		

             String realName = Bukkit.getPlayer(args[0]).getName();
             String primGroup = XeChat.chat.getPrimaryGroup("world", realName);
             String primGroupPrefix = XeChat.chat.getGroupPrefix("world", primGroup);
             String prefix = ChatColor.translateAlternateColorCodes('&', primGroupPrefix);
             player.sendMessage(
                     ChatColor.AQUA
                             +"[XeChat]: "
                             + prefix
    		                 + realName
                             + ChatColor.AQUA
                             + " is in "
                             + prefix
                             +primGroup
                             +ChatColor.AQUA
                             +"."
             );
    	        
    	}else{
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, Type /rank [name] To get the rank of a player.");
    	}
    }

    public rankExecutor(XeChat XE) {
        super(XE);
    }
}
