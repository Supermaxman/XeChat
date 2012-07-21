package me.supermaxman.xechat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class XeChatListener implements Listener {
    final XeChat plugin;

    public XeChatListener(XeChat plugin) {
        this.plugin = plugin;
    }

    private final ChatColor white = ChatColor.WHITE;
    ChatColor grey = ChatColor.GRAY;
    ChatColor gold = ChatColor.GOLD;
    ChatColor red = ChatColor.DARK_RED;
    ChatColor dred = ChatColor.DARK_RED;
    private final ChatColor yellow = ChatColor.YELLOW;
    ChatColor aqua = ChatColor.AQUA;


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(PlayerChatEvent event) {
        Player p = event.getPlayer();
        if (!XeChat.channelIn.containsKey(p)) {
            XeChat.channelIn.put(p, "G");
        }

        if (XeChat.channelIn.get(p).equalsIgnoreCase("G")) {
            String gn = XeChat.permission.getPrimaryGroup(p);
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            String ch = (String) XeChat.conf.get("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, ch, white);

            event.setFormat(message);


        } else if (XeChat.channelIn.get(p).equalsIgnoreCase("l")) {
            String gn = XeChat.permission.getPrimaryGroup(p);
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            String ch = yellow + "l";
            String message = XeChatFormater.format(p, m, name, world, ch, yellow);

            for (Entity e : p.getNearbyEntities((Integer) XeChat.conf.get("localdistence"), 300, (Integer) XeChat.conf.get("localdistence"))) {
                if (e instanceof Player) {
                    Player r = (Player) e;

                    r.sendMessage(message);

                }
            }
            p.sendMessage(message);
            event.getRecipients().clear();
        }
    }


}
