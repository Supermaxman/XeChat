package me.supermaxman.xechat.Filters;


import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class PlayerFilter {
	
	
	

    public static String addColorNames(String m, Server s, ChatColor c) {
    	
    	Player[] names = s.getOnlinePlayers();
    	int i = 0;
    	while(i<names.length){
    		if(m.contains(names[i].getName())){
    			m = m.replaceAll(names[i].getName(), XeChat.chat.getPlayerPrefix(names[i]).replaceAll("&", "§")+names[i].getName()+c);
    		}
    		i++;
    	}
		return m;
    }
	
	
}
