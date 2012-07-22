package me.supermaxman.xechat;

import java.util.ArrayList;

import me.supermaxman.xechat.Filters.DefaultFilter;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChatFormater {

    public static String format(Player p, String m, String name, String world, XeChannel channel) {
    	ChatColor dcolor = channel.getColor();
        name = dcolor + XeChat.chat.getPlayerPrefix(p).replaceAll("&", "§") + name + dcolor + ":";
        
        String ch = dcolor + "[" + dcolor + channel.getName() + dcolor + "]";
        m = censorChat(m);
        if (XeChat.conf.getBoolean("worldinchat")) {
            return (String.format("%s %s %s %s", ch, ChatColor.WHITE + "[" + world + "]", name, m));

        } else {
            return (String.format("%s %s %s", ch, name, m));
            
        }
    }

    public static String censorChat(String m){
    	
    	ArrayList<String> censored = DefaultFilter.getCensored();
    	for(String s: censored){
    		m.replaceAll(s.toLowerCase(), "***");
    		m.replaceAll(s.toUpperCase(), "***");
    		System.out.println(1);
    	}
    	
    	
		return m;
    }
    
}
