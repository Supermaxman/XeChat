package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelDeleteExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	String channelName = args[0];
        	
            
            
            
            if(XeChat.channels.containsKey(channelName)){
            	XeChannel channel = XeChat.channels.get(channelName);
            	if((channel.getCreatorName().equalsIgnoreCase(player.getName()))||(XeChat.permission.has(player, "xechat.delete.any"))){
        		if(channel.isPermenent()==false){
            	XeChat.channels.remove(channelName);
            	for(Player p : player.getServer().getOnlinePlayers()){
            		if(channel.getPlayers().contains(p.getName())){
            		XeChat.channelIn.put(p, XeChat.g);
            		p.sendMessage(ChatColor.AQUA + "[XeChat]: "+channelName+" Has Been Deleted, You Were Moved To "+XeChat.g.getColor()+XeChat.g.getName()+ChatColor.AQUA+".");
            		}
            	}
            	channel.getPlayers().clear();
        		}else{
                    player.sendMessage(ChatColor.RED + "[XeChat]: You Cannot Delete "+channelName+".");
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
