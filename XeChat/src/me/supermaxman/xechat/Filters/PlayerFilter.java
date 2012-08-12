package me.supermaxman.xechat.Filters;


import me.supermaxman.xechat.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerFilter {


    public static String addColorNames(String m, ChatColor c) {

//        Player[] names = s.getOnlinePlayers();
//        int i = 0;
//        while (i < names.length) {
//            if (m.contains(names[i].getName())) {
//                m = m.replaceAll(names[i].getName(), ChatColor.translateAlternateColorCodes('&', XeChat.chat.getPlayerPrefix(names[i])) + names[i].getName() + c);
//            }
//            i++;
//        }
        String m2 = "";
        for (String s : m.split(" ")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                m2 += s.replaceAll(player.getName(), ColorUtils.getColoredName(player)) + c + " ";
            }
        }



        return m2.substring(0, m2.length() - 1);
    }


}
