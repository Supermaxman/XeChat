package me.supermaxman.xechat;

import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChatFormater {

    public static String format(Player p, String m, String name, String world, XeChannel channel) {
    	ChatColor dcolor = channel.getColor();
        name = dcolor + XeChat.chat.getPlayerPrefix(p).replaceAll("&", "§") + name + dcolor + ":";
        
        String ch = dcolor + "[" + dcolor + channel.getName() + dcolor + "]";
        if (XeChat.conf.getBoolean("worldinchat")) {
            return (String.format("%s %s %s %s", ch, ChatColor.WHITE + "[" + world + "]", name, m));

        } else {
            return (String.format("%s %s %s", ch, name, m));
            
        }
    }


}
