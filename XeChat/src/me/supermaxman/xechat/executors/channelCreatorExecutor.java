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
            	//add custom channel colors
            }
            
            XeChannel channel = new XeChannel(channelName, name, color);
            if (!XeChat.channelsOn.containsKey(player)) {
                ArrayList<XeChannel> list = new ArrayList<XeChannel>();
                list.add(XeChat.g);
                XeChat.channelsOn.put(player, list);
                }
            
            XeChat.channelsOn.get(player).add(channel);
            XeChat.channels.add(channel);
            
        } else if (args.length == 0) {
        	
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /create [ChannelName] <Color>.");
            
        }
    }

    public channelCreatorExecutor(XeChat XE) {
        super(XE);
    }
}
