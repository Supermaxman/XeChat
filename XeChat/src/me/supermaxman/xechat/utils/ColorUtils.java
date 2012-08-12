package me.supermaxman.xechat.utils;

import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 12/08/12
 * Time: 19:09
 */
public class ColorUtils {

    public static String getColoredName(Player player){
       return ChatColor.translateAlternateColorCodes('&', XeChat.chat.getPlayerPrefix(player)) + player.getName();
    }

}
