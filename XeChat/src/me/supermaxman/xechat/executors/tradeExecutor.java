package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
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


            String ch = ChatColor.BLUE + "t";


            String message = XeChatFormater.format(player, m, name, world, ch, ChatColor.BLUE);
            if (!XeChat.channelsOn.containsKey(player)) {
                ArrayList<String> list = new ArrayList<String>();
                list.add("G");
                XeChat.channelsOn.put(player, list);
            }
            XeChat.channelsOn.get(player).add("t");
            for (Player r : player.getServer().getOnlinePlayers()) {
                if (XeChat.channelsOn.containsKey(r)) {
                    if (XeChat.channelsOn.get(r).contains("t")) {
                        r.sendMessage(message);
                    }
                }
            }


        } else if (args.length == 0) {
            XeChat.channelIn.put(player, "t");

            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + ChatColor.BLUE + "Trade" + ChatColor.AQUA + ".");
        }
    }

    public tradeExecutor(XeChat XE) {
        super(XE);
    }
}
