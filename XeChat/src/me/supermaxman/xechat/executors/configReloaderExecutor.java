package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import org.bukkit.entity.Player;

public class configReloaderExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if(player.isOp()){
        	XeChat.conf = XeChat.XE.getConfig();
        }
    }
    public configReloaderExecutor(XeChat XE) {
        super(XE);
    }
}
