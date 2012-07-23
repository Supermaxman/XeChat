package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.Objects.XeChannel;
import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelListExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String s = ChatColor.AQUA + "[XeChat]: Channels: ";
            String name = args[0];
            for (XeChannel c : XeChat.channels.values()) {
                if (c.getCreatorName().equalsIgnoreCase(name)) {
                    s = s + c.getColor() + c.getName() + ChatColor.AQUA + ",";
                }
            }
            player.sendMessage(s);


        } else if (args.length == 0) {
            String s = ChatColor.AQUA + "[XeChat]: Channels: ";
            for (XeChannel c : XeChat.channels.values()) {
                s = s + c.getColor() + c.getName() + ChatColor.AQUA + ",";

            }
            player.sendMessage(s);


        }
    }

    public channelListExecutor(XeChat XE) {
        super(XE);
    }
}