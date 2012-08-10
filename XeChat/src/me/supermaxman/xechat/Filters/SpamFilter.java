package me.supermaxman.xechat.Filters;

import me.supermaxman.xechat.Objects.XeChannel;
import org.bukkit.entity.Player;

public class SpamFilter {

    public static boolean checkSpam(String m, final Player p, XeChannel ch) {
        //Does not prevent worlds end or other spam bots, will cause major leaving spam.
        /*
        m = ch.getName() + ":" + m;
        if (XeChat.lastchat.containsKey(p)) {
            if (m.equalsIgnoreCase(XeChat.lastchat.get(p))) {
                p.kickPlayer(ChatColor.RED + "Kicked For Spam.");
                return false;
            }
        }
        XeChat.lastchat.put(p, m);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(XeChat.XE, new Runnable() {
            public void run() {
                XeChat.lastchat.remove(p);
            }
        }, 40);
        */
        return true;
    }

}
