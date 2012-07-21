package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import me.supermaxman.xechat.Objects.XeChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:24
 */
public class tradeExecutor extends baseExecutor {
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
            
            
            
            String message = XeChatFormater.format(player, m, name, world, XeChat.trade);
            if (!XeChat.channelsOn.containsKey(player)) {
                ArrayList<XeChannel> list = new ArrayList<XeChannel>();
                list.add(XeChat.g);
                XeChat.channelsOn.put(player, list);
            }
            XeChat.channelsOn.get(player).add(XeChat.trade);
            for (Player r : player.getServer().getOnlinePlayers()) {
                if (XeChat.channelsOn.containsKey(r)) {
                    if (XeChat.channelsOn.get(r).contains(XeChat.trade)) {
                        r.sendMessage(message);
                    }
                }
            }


        } else if (args.length == 0) {
            XeChat.channelIn.put(player, XeChat.trade);

            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " +  XeChat.trade.getColor()+XeChat.trade.getName() + ChatColor.AQUA + ".");
        }
    }

    public tradeExecutor(XeChat XE) {
        super(XE);
    }
}
