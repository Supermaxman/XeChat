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
            String gn = XeChat.permission.getPrimaryGroup(player);
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


            String ch = ChatColor.YELLOW + "l";


            String message = XeChatFormater.format(player, gn, m, name, world, ch, ChatColor.YELLOW);


            for (Entity e : player.getNearbyEntities((Integer) XeChat.conf.get("localdistence"), 300, (Integer) XeChat.conf.get("localdistence"))) {
                if (e instanceof Player) {
                    Player r = (Player) e;

                    r.sendMessage(message);

                }
            }
            player.sendMessage(message);

        } else if (args.length == 0) {
            XeChat.channelIn.put(player, "l");
            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + ChatColor.YELLOW + "Local" + ChatColor.AQUA + ".");
        }
    }

    public localExecutor(XeChat XE) {
        super(XE);
    }
}
