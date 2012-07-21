package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class channelCreatorExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	String channelName = args[0];
            String gn = XeChat.permission.getPrimaryGroup(player);
            
            String name = player.getName();
            ChatColor color = ChatColor.WHITE;
            if(args.length>=2){
            	try{
            		color = ChatColor.valueOf(args[1].toUpperCase());
            	}catch(IllegalArgumentException e){
                    player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, Specified Chat Color Does Not Exist, Defaulting to White.");
            	}
            }
            
            XeChannel channel = new XeChannel(channelName, name, color);
            if (!XeChat.channelsOn.containsKey(player)) {
                ArrayList<XeChannel> list = new ArrayList<XeChannel>();
                list.add(XeChat.g);
                XeChat.channelsOn.put(player, list);
                }
            if(!XeChat.channels.containsKey(channelName)){
                XeChat.channelsOn.get(player).add(channel);
                XeChat.channels.put(channelName, channel);
                XeChat.channelIn.put(player, channel);
                player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " +  channel.getColor()+channel.getName() + ChatColor.AQUA + ".");
            }else{
                player.sendMessage(ChatColor.RED + "[XeChat]: Channel "+channelName+" Already Exists.");
            }

        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /create [ChannelName] <Color>.");
            
        }
    }

    public channelCreatorExecutor(XeChat XE) {
        super(XE);
    }
}
