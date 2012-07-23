package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class replyExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
        	if(XeChat.whisper.containsKey(player)){
        		Player r = XeChat.whisper.get(player);
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
            
            
            String message = XeChatFormater.formatWhisper(player, m, name, r);
            r.sendMessage(message);
            message = XeChatFormater.formatWhisperTo(player, m, name, r);
            player.sendMessage(message);
        	}else{
                player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, You Are Not Whispering Anyone.");
        	}
        } else{
            player.sendMessage(ChatColor.RED + "[XeChat]: SYNTAX ERROR, Type /r To Respond To The Last Player You Whispered.");
        }
    }

    public replyExecutor(XeChat XE) {
        super(XE);
    }
}
