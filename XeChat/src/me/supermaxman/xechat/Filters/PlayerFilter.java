package me.supermaxman.xechat.Filters;


import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class PlayerFilter {


    public static String addColorNames(String m, Server s, ChatColor c) {

        Player[] names = s.getOnlinePlayers();
        int i = 0;
        while (i < names.length) {
            if (m.contains(names[i].getName())) {
                m = m.replaceAll(names[i].getName(), ChatColor.translateAlternateColorCodes('&', XeChat.chat.getPlayerPrefix(names[i])) + names[i].getName() + c);
            }
            i++;
        }
        return m;
    }


}
