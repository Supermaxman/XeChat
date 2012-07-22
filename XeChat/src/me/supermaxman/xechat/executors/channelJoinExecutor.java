package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelJoinExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	String channelName = args[0];            
            
        	
            
            if(XeChat.channels.containsKey(channelName)){
            	XeChannel channel = XeChat.channels.get(channelName);
            	if(!channel.getPlayers().contains(player.getName())){
            		
            	channel.addPlayer(player);
                XeChat.channelIn.put(player, channel);
                
                player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " +  channel.getColor()+channel.getName() + ChatColor.AQUA + ".");
            	}else{
                    XeChat.channelIn.put(player, channel);
                    
                    player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " +  channel.getColor()+channel.getName() + ChatColor.AQUA + ".");
            	}
            }else{
                player.sendMessage(ChatColor.RED + "[XeChat]: The Channel "+channelName+" Does Not Exist. Type /create "+channelName+" To  Create It.");
            }

        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /join [ChannelName].");
            
        }
    }

    public channelJoinExecutor(XeChat XE) {
        super(XE);
    }
}
