package me.supermaxman.xechat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChatFormater {

    public static String format(Player p, String group, String m, String name, String world, String ch, ChatColor dcolor) {


        String worldchat = "";

        if (XeChat.conf.getBoolean("worldinchat")) {
            worldchat = ChatColor.WHITE + "[" + world + "]";
        }


//        if (p.isOp()) {
//            color = ChatColor.DARK_RED;
//        } else if (group.equalsIgnoreCase("admin")) {
//            color = ChatColor.GOLD;
//        }
        name = dcolor + XeChat.chat.getPlayerPrefix(p) + name + dcolor + ": ";
        ch = dcolor + "[" + dcolor + ch + dcolor + "]";

        return (ch + worldchat + name + m);
    }


}
