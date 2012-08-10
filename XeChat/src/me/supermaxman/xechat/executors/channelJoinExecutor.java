package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.Objects.XeChannel;
import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class channelJoinExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];


            if (args.length == 1) {
                XeChat.isWhispering.put(player, false);
                XeChannel channel = null;
                for (Map.Entry<String, XeChannel> channelEntry : XeChat.channels.entrySet()) {
                    if(channelName.equalsIgnoreCase(channelEntry.getKey())){
                        channel = channelEntry.getValue();
                    }
                }

//                XeChannel channel = XeChat.channels.get(channelName);
                if (channel != null) {
                    if (!channel.getPlayers().contains(player.getName())) {
                        if (channel.isPrivate()) {
                            if (args.length > 1) {
                                String pass = args[1];
                                if (pass.equalsIgnoreCase(channel.getPassword())) {
                                    channel.addPlayer(player);
                                    XeChat.channelIn.put(player, channel);

                                    player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                                } else {
                                    player.sendMessage(ChatColor.RED + "[XeChat]: Incorrect Password For  " + channelName + ".");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "[XeChat]: The Channel " + channelName + " Requires A Password.");
                            }
                        } else {
                            channel.addPlayer(player);
                            XeChat.channelIn.put(player, channel);

                            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                        }
                    } else {
                        XeChat.channelIn.put(player, channel);

                        player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "[XeChat]: The Channel " + channelName + " Does Not Exist. Type /create " + channelName + " To  Create It.");
            }

        } else if (args.length != 1) {

            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /join [ChannelName].");

        }
    }

    public channelJoinExecutor(XeChat XE) {
        super(XE);
    }
}
