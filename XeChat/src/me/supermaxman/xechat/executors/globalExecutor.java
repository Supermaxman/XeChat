package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:20
 */
public class globalExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            player.sendMessage(ChatColor.RED + "[XeChat]: Error, type /g to talk in global.");
        } else if (args.length == 0) {
            XeChat.channelIn.put(player, "G");
            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " + ChatColor.WHITE + "Global" + ChatColor.AQUA + ".");
        }
    }

    public globalExecutor(XeChat XE) {
        super(XE);
    }
}
