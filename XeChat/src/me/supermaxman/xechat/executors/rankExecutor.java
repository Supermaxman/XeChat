package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class rankExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	if(args.length>0){
    		
    		 XeChat.chat.getPrimaryGroup("world", args[0]);
    	        player.sendMessage(ChatColor.AQUA+"[XeChat]: " +ChatColor.translateAlternateColorCodes('&', XeChat.chat.getGroupPrefix("world", XeChat.chat.getPrimaryGroup("world", args[0]))) 
    		 +args[0] + ChatColor.AQUA + " is in "
    	     +ChatColor.translateAlternateColorCodes('&', XeChat.chat.getGroupPrefix("world", XeChat.chat.getPrimaryGroup("world", args[0]))) 
    	     +XeChat.chat.getPrimaryGroup("world", args[0])+ChatColor.AQUA+".");
    	        
    	}else{
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, Type /rank [name] To get the rank of a player.");
    	}
    }

    public rankExecutor(XeChat XE) {
        super(XE);
    }
}
