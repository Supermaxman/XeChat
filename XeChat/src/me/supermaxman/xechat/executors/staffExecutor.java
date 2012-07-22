package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import me.supermaxman.xechat.XeChatFormater;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class staffExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
    	if(XeChat.permission.has(player, "xechat.staff.chat")){
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
            
            String message = XeChatFormater.format(player, m, name, world, XeChat.z);
            if(!XeChat.z.getPlayers().contains(player.getName())){

            XeChat.z.addPlayer(player);
            }
            XeChat.z.sendString(message);
            
        } else if (args.length == 0) {
            XeChat.channelIn.put(player, XeChat.z);
            if(!XeChat.z.getPlayers().contains(player.getName())){

            XeChat.z.addPlayer(player);
            }
            player.sendMessage(ChatColor.AQUA + "[XeChat]: Now Talking In " +  XeChat.z.getColor()+XeChat.z.getName() + ChatColor.AQUA + ".");
        }
    	
        
    }else{
        player.sendMessage(ChatColor.RED + "[XeChat]: ERROR, You do not have permission to enter " +  XeChat.z.getColor()+XeChat.z.getName() + ChatColor.RED + ".");
    }
    }
    public staffExecutor(XeChat XE) {
        super(XE);
    }
}
