package me.supermaxman.xechat.executors;

import java.util.Map;

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
                    if (args[2].toUpperCase().equalsIgnoreCase("MAGIC")) {
                        player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, Specified Chat Color Is Not Allowed, Defaulting to White.");
                    } else {
                        color = ChatColor.valueOf(args[2].toUpperCase());
                    }
                } catch (IllegalArgumentException e) {
                    player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, Specified Chat Color Does Not Exist, Defaulting to White.");
                }
            }
            boolean canCreate = true;
            for (Map.Entry<String, XeChannel> channelEntry : XeChat.channels.entrySet()) {
                if(channelName.equalsIgnoreCase(channelEntry.getKey())){
                   	canCreate = false;
                   	break;
                }
            }
            if (canCreate) {
                int amt = 0;
                int max = 0;
                if (XeChat.permission.has(player, "xechat.channels.create")) {
                    if (XeChat.permission.has(player, "xechat.channels.2")) {
                        max = 2;
                    }
                    if (XeChat.permission.has(player, "xechat.channels.4")) {
                        max = 4;
                    }
                    if (XeChat.permission.has(player, "xechat.channels.6")) {
                        max = 6;
                    }
                    if (XeChat.permission.has(player, "xechat.channels.8")) {
                        max = 8;
                    }

                    for (XeChannel ch : XeChat.channels.values()) {
                        if (ch.getCreatorName().equalsIgnoreCase(name)) {
                            amt++;
                        }
                    }

                    if (amt < max) {

                        XeChat.isWhispering.put(player, false);
                        XeChannel channel = new XeChannel(channelName, name, color);
                        if (args.length >= 2) {
                            String pass = args[1];
                            channel.setPrivate(true, pass);
                        }
                        
                        channel.addPlayer(player);
                        channel.save();
                        XeChat.channels.put(channelName, channel);
                        XeChat.channelIn.put(player, channel);
                        player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                    } else {
                        player.sendMessage(ChatColor.RED + "[XeChat]: You Have Hit Your Maximum Amount Of Channels.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "[XeChat]: Your Rank Cannot Create Channels.");
                }
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
