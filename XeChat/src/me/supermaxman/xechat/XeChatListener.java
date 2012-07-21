package me.supermaxman.xechat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class XeChatListener implements Listener {
    final XeChat plugin;

    public XeChatListener(XeChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!XeChat.channelsOn.containsKey(player)) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("G");
            XeChat.channelsOn.put(player, list);
        }
        XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getJoinMessage()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getQuitMessage()));
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player p = event.getPlayer();
        if (!XeChat.channelIn.containsKey(p)) {
            XeChat.channelIn.put(p, "G");
        }

        if (XeChat.channelIn.get(p).equalsIgnoreCase("G")) {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            //String ch = XeChat.conf.getString("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, XeChat.g);

            event.setFormat(message);

            XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(name + ": " + m));
        } else if (XeChat.channelIn.get(p).equalsIgnoreCase("l")) {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();
            
            String message = XeChatFormater.format(p, m, name, world,  XeChat.l);

            for (Entity e : p.getNearbyEntities(XeChat.conf.getInt("localdistence"), 300, XeChat.conf.getInt("localdistence"))) {
                if (e instanceof Player) {
                    Player r = (Player) e;

                    r.sendMessage(message);

                }
            }
            p.sendMessage(message);
            event.getRecipients().clear();
        } else if (XeChat.channelIn.get(p).equalsIgnoreCase("trade")) {
        	String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            //String ch = XeChat.conf.getString("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, XeChat.g);

            event.setFormat(message);
        }
    }


}
