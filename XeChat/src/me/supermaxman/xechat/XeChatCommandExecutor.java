package me.supermaxman.xechat;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class XeChatCommandExecutor implements CommandExecutor{
	public XeChat plugin;
	public XeChatCommandExecutor(XeChat plugin) {this.plugin = plugin;}
	ChatColor white = ChatColor.WHITE;
	ChatColor grey = ChatColor.GRAY;
	ChatColor gold = ChatColor.GOLD;
	ChatColor red = ChatColor.DARK_RED;
	ChatColor dred = ChatColor.DARK_RED;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor aqua = ChatColor.AQUA;
	ChatColor blue = ChatColor.BLUE;
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(commandLabel.equalsIgnoreCase("l")){
				if(args.length>0){
				String gn = XeChat.permission.getPrimaryGroup(p);
				String m = "";
				int i = 0;
				for(String s : args){
					if(i==0){
						m = m+s;
					}else{
						m = m + " "+s;
					}
					i++;
				}
				String name = p.getName();
				String world = p.getWorld().getName();
				
				
				String ch = yellow+"l";
				
				
				String message = XeChatFormater.chatFormater(p, gn, m, name, world, ch, yellow);
				
				
				for (Entity e: p.getNearbyEntities((Integer)XeChat.conf.get("localdistence"), 300, (Integer)XeChat.conf.get("localdistence"))){
					if(e instanceof Player){
						Player r = (Player) e;

						r.sendMessage(message);
						
					}
				}
				p.sendMessage(message);
				
				}else if(args.length==0){
					XeChat.channelIn.put(p, "l");
					p.sendMessage(aqua+"[XeChat]: Now Talking In "+yellow+"Local"+aqua+".");
				}
			}else if(commandLabel.equalsIgnoreCase("g")){
				if(args.length>0){
					p.sendMessage(red+"[XeChat]: Error, type /g to talk in global.");
				}else if(args.length==0){
					XeChat.channelIn.put(p, "G");
					p.sendMessage(aqua+"[XeChat]: Now Talking In "+white+"Global"+aqua+".");
				}
			}else if(commandLabel.equalsIgnoreCase("t")){
				if(args.length>0){
					String gn = XeChat.permission.getPrimaryGroup(p);
					String m = "";
					int i = 0;
					for(String s : args){
						if(i==0){
							m = m+s;
						}else{
							m = m + " "+s;
						}
						i++;
					}
					String name = p.getName();
					String world = p.getWorld().getName();
					
					
					String ch = blue+"t";
					
					
					String message = XeChatFormater.chatFormater(p, gn, m, name, world, ch, blue);
					if(!XeChat.channelsOn.containsKey(p)){
						
						//XeChat.channelsOn.put(p, ?????);
					}
					XeChat.channelsOn.get(p).add("t");
					for (Player r: p.getServer().getOnlinePlayers()){
						if(XeChat.channelsOn.containsKey(r)){
							if(XeChat.channelsOn.get(r).contains("t")){
								r.sendMessage(message);
							}
						}
					}
					
					
				}else if(args.length==0){
					XeChat.channelIn.put(p, "t");
					
					p.sendMessage(aqua+"[XeChat]: Now Talking In "+blue+"Trade"+aqua+".");
				}
			}
			
			
			
			
			
		}
		
		
		return true;
	}
	
	
}
