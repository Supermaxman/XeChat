package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:20
 */
public class heroChatFuckeryExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[XeChat]: Herp");
        } else if (args.length < 0) {
            String[] args2 = Arrays.copyOfRange(args, 1, args.length);
            if(args[0].equalsIgnoreCase("join")){
                XeChat.joinExecutor.run(player,args2);
             //leave
            }
            else if(args[0].equalsIgnoreCase("leave"))
            {
               XeChat.channelLeaveExecutor.run(player,args2);
            }
            else {
                XeChat.joinExecutor.run(player,args2);
            }
        }
    }

    public heroChatFuckeryExecutor(XeChat XE) {
        super(XE);
    }
}
