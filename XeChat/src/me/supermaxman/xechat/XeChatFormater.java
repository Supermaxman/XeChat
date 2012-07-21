package me.supermaxman.xechat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChatFormater {

    public static String format(Player p, String group, String m, String name, String world, String ch, ChatColor dcolor) {

        name = dcolor + XeChat.chat.getPlayerPrefix(p).replaceAll("&", "§") + name + dcolor + ":";
        ch = dcolor + "[" + dcolor + ch + dcolor + "]";
        if (XeChat.conf.getBoolean("worldinchat")) {
            return (String.format("%s %s %s %s", ch, ChatColor.WHITE + "[" + world + "]", name, m));

        } else {
            return (String.format("%s %s %s", ch, name, m));

        }
    }


}
