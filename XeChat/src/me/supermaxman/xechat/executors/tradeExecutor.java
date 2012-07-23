package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class tradeExecutor extends baseExecutor {
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


            String message = XeChatFormater.format(player, m, name, world, XeChat.trade);
            if (!XeChat.trade.getPlayers().contains(player.getName())) {
                XeChat.trade.addPlayer(player);
            }
            XeChat.trade.sendString(message);


        } else if (args.length == 0) {
            XeChat.channelIn.put(player, XeChat.trade);
            XeChat.whisper.remove(player);
            if (!XeChat.trade.getPlayers().contains(player.getName())) {

                XeChat.trade.addPlayer(player);
            }
            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + XeChat.trade.getColor() + XeChat.trade.getName() + ChatColor.AQUA + ".");
        }
    }

    public tradeExecutor(XeChat XE) {
        super(XE);
    }
}
