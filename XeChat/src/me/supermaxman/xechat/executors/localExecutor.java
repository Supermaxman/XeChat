package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:18
 */
public class localExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String m = "";
            int i = 0;
            for (String s : args) {
                if (i == 0) {
                    m = m + s;
                } else {
                    m = m + " " + s;
                }
                i++;
            }
            String name = player.getName();
            String world = player.getWorld().getName();


            String message = XeChatFormater.format(player, m, name, world, XeChat.l);


            for (Entity e : player.getNearbyEntities(XeChat.conf.getInt("localdistence"), 300, XeChat.conf.getInt("localdistence"))) {
                if (e instanceof Player) {
                    Player r = (Player) e;

                    r.sendMessage(message);

                }
            }
            player.sendMessage(message);

        } else if (args.length == 0) {
            XeChat.channelIn.put(player, XeChat.l);
            XeChat.isWhispering.put(player, false);
            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + XeChat.l.getColor() + XeChat.l.getName() + ChatColor.AQUA + ".");
        }
    }

    public localExecutor(XeChat XE) {
        super(XE);
    }
}
