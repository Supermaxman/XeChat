package me.supermaxman.xechat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChatFormater {
	static ChatColor white = ChatColor.WHITE;
	static ChatColor grey = ChatColor.GRAY;
	static ChatColor gold = ChatColor.GOLD;
	static ChatColor red = ChatColor.DARK_RED;
	static ChatColor dred = ChatColor.DARK_RED;
	static ChatColor yellow = ChatColor.YELLOW;
	static ChatColor aqua = ChatColor.AQUA;

	
	
	
	
	public static String chatFormater(Player p, String group, String m, String name, String world, String ch, ChatColor dcolor){
		
		
		String worldchat = "";
		
		if((Boolean) XeChat.conf.get("worldinchat")){
			worldchat = white+"["+world+"]";
		}
				
		
		ChatColor color = grey;
		
		
		if(p.isOp()){
			color = dred;
		}else if(group.equalsIgnoreCase("admin")){
			color = gold;
		}
		name = dcolor+"["+color+name+dcolor+"]: ";
		ch = dcolor+"["+dcolor+ch+dcolor+"]";
		return(ch+worldchat+name+m);
	}
	
	
	
	
	
}
