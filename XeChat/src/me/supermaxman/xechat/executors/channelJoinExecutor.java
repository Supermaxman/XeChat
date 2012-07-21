package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class channelJoinExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	String channelName = args[0];
            String gn = XeChat.permission.getPrimaryGroup(player);
            
            String name = player.getName();
            ChatColor color = ChatColor.WHITE;
            
            if (!XeChat.channelsOn.containsKey(player)) {
                ArrayList<XeChannel> list = new ArrayList<XeChannel>();
                list.add(XeChat.g);
                XeChat.channelsOn.put(player, list);
                }
            if(XeChat.channels.containsKey(channelName)){
            	XeChannel channel = XeChat.channels.get(channelName);
                XeChat.channelsOn.get(player).add(channel);
                XeChat.channelIn.put(player, channel);
                player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " +  channel.getColor()+channel.getName() + ChatColor.AQUA + ".");
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
