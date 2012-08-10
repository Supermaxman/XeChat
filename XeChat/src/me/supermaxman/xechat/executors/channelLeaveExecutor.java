package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.Objects.XeChannel;
import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class channelLeaveExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];

            XeChannel channel = null;
            for (Map.Entry<String, XeChannel> channelEntry : XeChat.channels.entrySet()) {
                if(channelName.equalsIgnoreCase(channelEntry.getKey())){
                    channel = channelEntry.getValue();
                }
            }
            if (channel != null) {
                if (channel.getPlayers().contains(player.getName())) {
                    channel.removePlayer(player);
                    XeChat.channelIn.put(player, XeChat.g);
                    player.sendMessage(ChatColor.AQUA + "[XeChat]: You Have Left " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                } else {
                    player.sendMessage(ChatColor.RED + "[XeChat]: You Are Not In " + channelName + ".");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[XeChat]: The Channel " + channelName + " Does Not Exist.");
            }

        } else if (args.length == 0) {

            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, type /leave [ChannelName].");

        }
    }

    public channelLeaveExecutor(XeChat XE) {
        super(XE);
    }
}
