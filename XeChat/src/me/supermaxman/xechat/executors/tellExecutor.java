package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class tellExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length >= 2) {
            if (player.getServer().getPlayerExact(args[0]) != null) {
                Player r = player.getServer().getPlayerExact(args[0]);
                XeChat.whisper.put(player, r);
                String m = "";
                int i = 0;
                for (String s : args) {
                    if (i == 0) {
                    } else {
                        m = m + " " + s;
                    }
                    i++;
                }
                String name = player.getName();


                String message = XeChatFormater.formatWhisper(player, m, name, r);
                r.sendMessage(message);
                message = XeChatFormater.formatWhisperTo(player, m, name, r);
                player.sendMessage(message);
                XeChat.whisper.put(r, player);
            } else {
                player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, The Player " + args[0] + " Is Not Online Or Does Not Exist.");
            }
        } else if (args.length == 1) {
            if (player.getServer().getPlayerExact(args[0]) != null) {
                XeChat.whisper.put(player, player.getServer().getPlayerExact(args[0]));
                XeChat.isWhispering.put(player, true);
                player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Whispering " +  ChatColor.translateAlternateColorCodes('&', XeChat.chat.getPlayerPrefix(player.getServer().getPlayerExact(args[0]))) + player.getServer().getPlayerExact(args[0]).getName() + ChatColor.AQUA + ".");
            } else {
                player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, The Player " + args[0] + " Is Not Online Or Does Not Exist.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, Type /tell [username] [message].");
        }
    }

    public tellExecutor(XeChat XE) {
        super(XE);
    }
}
