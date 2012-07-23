package me.supermaxman.xechat;

import me.supermaxman.xechat.Filters.*;
import me.supermaxman.xechat.Objects.XeChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class XeChatFormater {

    public static String format(Player p, String m, String name, String world, XeChannel channel) {
        ChatColor dcolor = channel.getColor();
        name = dcolor + XeChat.chat.getPlayerPrefix(p).replaceAll("&", "�") + name + dcolor + ":";

        String ch = dcolor + "[" + dcolor + channel.getName() + dcolor + "]";
        m = censorChat(m, p);
        m = PlayerFilter.addColorNames(m, p.getServer(), dcolor);
        m = ColorFilter.addColorChat(m);
        //Completely unnecessary. Handled by ncp.
        if (SpamFilter.checkSpam(m, p, channel)) {
            if (XeChat.conf.getBoolean("worldinchat")) {
                return (String.format("%s %s %s %s", ch, ChatColor.WHITE + "[" + world + "]", name, m));

            } else {
                return (String.format("%s %s %s", ch, name, m));

            }
        } else {
            return ChatColor.RED + "Was Kicked For Spam";
        }
    }

    public static String formatWhisper(Player p, String m, String name, Player r) {
        ChatColor dcolor = ChatColor.LIGHT_PURPLE;
        name = dcolor + XeChat.chat.getPlayerPrefix(p).replaceAll("&", "�") + name + dcolor + ":";

        String ch = dcolor + "[" + dcolor + "From" + "]";
        m = censorChat(m, p);
        m = PlayerFilter.addColorNames(m, p.getServer(), dcolor);
        m = ColorFilter.addColorChat(m);
        return (String.format("%s %s %s", ch, name, m));
    }

    public static String formatWhisperTo(Player p, String m, String name, Player r) {
        ChatColor dcolor = ChatColor.LIGHT_PURPLE;
        name = dcolor + XeChat.chat.getPlayerPrefix(r).replaceAll("&", "�") + r.getName() + dcolor + ":";

        String ch = dcolor + "[" + dcolor + "To" + "]";
        m = censorChat(m, p);
        m = PlayerFilter.addColorNames(m, p.getServer(), dcolor);
        m = ColorFilter.addColorChat(m);
        return (String.format("%s %s %s", ch, name, m));
    }


    public static String censorChat(String m, Player p) {


        ArrayList<String> censored = DefaultFilter.getCensored();
        for (String s : censored) {
            if (m.toLowerCase().contains(s.toLowerCase())) {
                m = m.toLowerCase().replaceAll(s.toLowerCase(), "****");

            }
        }
        //Why not kick on all foul language?
        ArrayList<String> kcensored = KickFilter.getCensored();

        for (String s : kcensored) {
            if (m.toLowerCase().contains(s.toLowerCase())) {
                m = ChatColor.RED + "Was Kicked For Foul Language";
                p.kickPlayer(ChatColor.RED + "Kicked For Foul Language.");

                break;
            }
        }


        return m;
    }

}