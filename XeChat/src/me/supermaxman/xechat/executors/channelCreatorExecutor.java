package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.Objects.XeChannel;
import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelCreatorExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];

            String name = player.getName();
            ChatColor color = ChatColor.WHITE;
            
            if (args.length >= 3) {
                try {
                    if (args[2].toUpperCase().equalsIgnoreCase("MAGIC")){
                        player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, Specified Chat Color Is Not Allowed, Defaulting to White.");
                    }else{
                    	color = ChatColor.valueOf(args[2].toUpperCase());
                    }
                } catch (IllegalArgumentException e) {
                    player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, Specified Chat Color Does Not Exist, Defaulting to White.");
                }
            }
            if (!XeChat.channels.containsKey(channelName)) {
                XeChat.isWhispering.put(player, false);
                XeChannel channel = new XeChannel(channelName, name, color);
                if(args.length>=2){
                	String pass = args[1];
                	channel.setPrivate(true, pass);
                }
                
                channel.addPlayer(player);
                XeChat.channels.put(channelName, channel);
                XeChat.channelIn.put(player, channel);
                player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
            } else {
                player.sendMessage(ChatColor.RED + "[XeChat]: Channel " + channelName + " Already Exists.");
            }

        } else if (args.length == 0) {

            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /create [ChannelName] <Password> <Color>.");

        }
    }

    public channelCreatorExecutor(XeChat XE) {
        super(XE);
    }
}
