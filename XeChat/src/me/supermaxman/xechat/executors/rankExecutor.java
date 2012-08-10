package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

            HashMap<String, ArrayList<Player>> ranks = new HashMap<String, ArrayList<Player>>();
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                String realName = player1.getName();
                String primGroup = XeChat.chat.getPrimaryGroup("world", realName);
                if(ranks.containsKey(primGroup)){
                    ArrayList<Player> players = ranks.get(primGroup);
                    players.add(player1);
                    ranks.put(primGroup, players);
                } else {
                    ArrayList<Player> players = new ArrayList<Player>();
                    players.add(player1);
                    ranks.put(primGroup, players);
                }
            }

            for (Map.Entry<String, ArrayList<Player>> entry : ranks.entrySet()) {
                String s = "";
                for (Player player1 : entry.getValue()) {
                     s += player1.getName() + ", ";
                }
                s = s.substring(0,s.length() - 2);
                String primGroupPrefix = XeChat.chat.getGroupPrefix("world", entry.getKey());
                String prefix = ChatColor.translateAlternateColorCodes('&', primGroupPrefix);
                player.sendMessage(prefix + entry.getKey() + " [" + entry.getValue().size() +"] : " + s);
            }


//            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, Type /rank [name] To get the rank of a player.");
    	}
    }

    public rankExecutor(XeChat XE) {
        super(XE);
    }
}
