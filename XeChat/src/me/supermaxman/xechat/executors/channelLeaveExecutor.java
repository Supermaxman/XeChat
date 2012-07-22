package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelLeaveExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	String channelName = args[0];            
            
        	
            
            if(XeChat.channels.containsKey(channelName)){
            	XeChannel channel = XeChat.channels.get(channelName);
            	if(channel.getPlayers().contains(player.getName())){
            	channel.removePlayer(player);
                XeChat.channelIn.put(player, XeChat.g);
                player.sendMessage(ChatColor.AQUA + "[XeChat]: You Have Left " +  channel.getColor()+channel.getName() + ChatColor.AQUA + ".");
            	}else{
                    player.sendMessage(ChatColor.RED + "[XeChat]: You Are Not In "+channelName+".");
            	}
            }else{
                player.sendMessage(ChatColor.RED + "[XeChat]: The Channel "+channelName+" Does Not Exist.");
            }

        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /leave [ChannelName].");
            
        }
    }

    public channelLeaveExecutor(XeChat XE) {
        super(XE);
    }
}
