package me.supermaxman.xechat;

import me.supermaxman.xechat.Objects.XeChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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
            ArrayList<XeChannel> list = new ArrayList<XeChannel>();
            list.add(XeChat.g);
            XeChat.channelsOn.put(player, list);
        }
        event.setJoinMessage(player.getName() + " Joined the game!");
        XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getJoinMessage()));
        
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(event.getPlayer().getName() + " Quit!");
        XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getQuitMessage()));
        
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(PlayerChatEvent event) {
        Player p = event.getPlayer();
        if (!XeChat.channelIn.containsKey(p)) {
            XeChat.channelIn.put(p, XeChat.g);
        }

        //if(!XeChat.channels.containsKey(XeChat.channelIn.get(p))){
        //XeChat.channelIn.put(p, XeChat.g);
        //}


        if (XeChat.channelIn.get(p).getName().equalsIgnoreCase("G")) {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            //String ch = XeChat.conf.getString("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, XeChat.g);

            event.setFormat(message);

            if (!m.equalsIgnoreCase("u00a74u00a75u00a73u00a74v|1")) {
                XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(name + ": " + m));
            }
        } else if (XeChat.channelIn.get(p).getName().equalsIgnoreCase("l")) {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            String message = XeChatFormater.format(p, m, name, world, XeChat.l);

            for (Entity e : p.getNearbyEntities(XeChat.conf.getInt("localdistence"), 300, XeChat.conf.getInt("localdistence"))) {
                if (e instanceof Player) {
                    Player r = (Player) e;

                    r.sendMessage(message);

                }
            }
            p.sendMessage(message);
            event.getRecipients().clear();
        } else if (XeChat.channelIn.get(p).getName().equalsIgnoreCase("trade")) {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            //String ch = XeChat.conf.getString("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, XeChat.trade);
            for (Player r : p.getServer().getOnlinePlayers()) {
                if (XeChat.channelsOn.containsKey(r)) {
                    if (XeChat.channelsOn.get(r).contains(XeChat.trade.getName())) {
                        r.sendMessage(message);
                    }
                }
            }

            p.sendMessage(message);
            event.getRecipients().clear();
        } else if (XeChat.channelIn.get(p).getName().equalsIgnoreCase("z")) {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            //String ch = XeChat.conf.getString("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, XeChat.z);
            for (Player r : p.getServer().getOnlinePlayers()) {
                if (XeChat.channelsOn.containsKey(r)) {
                    if (XeChat.channelsOn.get(r).contains(XeChat.z.getName())) {
                        r.sendMessage(message);
                    }
                }
            }

            p.sendMessage(message);
            event.getRecipients().clear();
        } else {
            String m = event.getMessage();
            String name = p.getName();
            String world = p.getWorld().getName();

            //String ch = XeChat.conf.getString("defaultChannel");

            String message = XeChatFormater.format(p, m, name, world, XeChat.channelIn.get(p));
            for (Player r : p.getServer().getOnlinePlayers()) {
                if (XeChat.channelsOn.containsKey(r)) {
                    if (XeChat.channelsOn.get(r).contains(XeChat.channelIn.get(p))) {
                        r.sendMessage(message);
                    }
                }
            }

            event.getRecipients().clear();
        }
    }


}
