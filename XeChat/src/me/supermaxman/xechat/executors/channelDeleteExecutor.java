package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class channelDeleteExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	String channelName = args[0];
            String gn = XeChat.permission.getPrimaryGroup(player);
            
            String name = player.getName();
            ChatColor color = ChatColor.WHITE;
            
            
            
            
            
            
            
            if(XeChat.channels.containsKey(channelName)){
            	XeChannel channel = XeChat.channels.get(channelName);
            	if((channel.getCreatorName().equalsIgnoreCase(player.getName()))||(XeChat.permission.has(player, "xechat.delete.any"))){
        		XeChat.channels.remove(channel);
            	for(Player p : player.getServer().getOnlinePlayers()){
            		if(XeChat.channelIn.containsKey(p)){
            			
            			if(XeChat.channelsOn.get(p).contains(channel)){
            			XeChat.channelIn.put(p, XeChat.g);
            			XeChat.channelsOn.get(p).remove(channel);
                        (p).sendMessage(ChatColor.AQUA + "[XeChat]: "+channelName+" Has Been Deleted, You Were Moved To "+XeChat.g.getColor()+XeChat.g.getName()+ChatColor.AQUA+".");
            			}
            			
            		}
            	}
            }else{
                player.sendMessage(ChatColor.RED + "[XeChat]: You Do Not Have Permission to Delete "+channelName+".");
            }
            }else{
                player.sendMessage(ChatColor.RED + "[XeChat]: The Channel "+channelName+" Does Not Exist.");
            }

        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /delete [ChannelName].");
            
        }
    }

    public channelDeleteExecutor(XeChat XE) {
        super(XE);
    }
}
