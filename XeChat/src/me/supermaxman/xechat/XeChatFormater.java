package me.supermaxman.xechat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChatFormater {

    public static String format(Player p, String group, String m, String name, String world, String ch, ChatColor dcolor) {
        boolean worldchatbool = XeChat.conf.getBoolean("worldinchat");

        String worldchat = "";

        if (worldchatbool) {
            worldchat = ChatColor.WHITE + "[" + world + "]";
        }


//        if (p.isOp()) {
//            color = ChatColor.DARK_RED;
//        } else if (group.equalsIgnoreCase("admin")) {
//            color = ChatColor.GOLD;
//        }
        name = dcolor + XeChat.chat.getPlayerPrefix(p).replaceAll("&", "§") + name + dcolor + ":";
        ch = dcolor + "[" + dcolor + ch + dcolor + "]";
        if (worldchatbool) {
            return (String.format("%s %s %s %s", ch, worldchat, name, m));

        } else {
            return (String.format("%s %s %s", ch, name, m));

        }
    }


}
