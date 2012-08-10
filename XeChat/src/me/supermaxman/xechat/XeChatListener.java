package me.supermaxman.xechat;

import me.supermaxman.xechat.Objects.XeChannel;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class XeChatListener implements Listener {
    final XeChat plugin;

    public XeChatListener(XeChat plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!XeChat.g.getPlayers().contains(player.getName())) {
            XeChat.g.addPlayer(player);
        }
        if (!XeChat.channelIn.containsKey(player)) {
            XeChat.channelIn.put(player, XeChat.g);
        }
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', XeChat.chat.getPlayerPrefix(player)) + player.getName() + ChatColor.YELLOW + " Joined the game.");
        XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getJoinMessage()));

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', XeChat.chat.getPlayerPrefix(player)) + player.getName() + ChatColor.YELLOW + " Quit the game.");
        XeChat.bot.sendMessage(XeChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getQuitMessage()));

    }
    
	@EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        if (!XeChat.channelIn.containsKey(p)) {
            XeChat.channelIn.put(p, XeChat.g);
        }
        if (!XeChat.g.getPlayers().contains(p.getName())) {
            XeChat.g.addPlayer(p);
        }
        if (!XeChat.isWhispering.containsKey(p)) {
            XeChat.isWhispering.put(p, false);
        }
        
        if (XeChat.isWhispering.get(p)) {
            String m = event.getMessage();
            String name = p.getName();
            Player r = XeChat.whisper.get(p);
            String message = XeChatFormater.formatWhisper(p, m, name, r);

            r.sendMessage(message);
            message = XeChatFormater.formatWhisperTo(p, m, name, r);
            p.sendMessage(message);
            event.getRecipients().clear();
        } else {
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
            } else {

                String m = event.getMessage();
                String name = p.getName();
                String world = p.getWorld().getName();
                XeChannel channel = XeChat.channelIn.get(p);
                String message = XeChatFormater.format(p, m, name, world, XeChat.channelIn.get(p));
                channel.sendString(message);
                
                event.getRecipients().clear();
            }
        }
    }


}
