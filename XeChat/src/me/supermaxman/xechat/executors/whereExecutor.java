package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class whereExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        player.sendMessage(ChatColor.AQUA + "[XeChat]: You are at "
                + ChatColor.BLUE + player.getLocation().getBlockX() + ChatColor.AQUA + ", "
                + ChatColor.BLUE + player.getLocation().getBlockY() + ChatColor.AQUA + ", "
                + ChatColor.BLUE + player.getLocation().getBlockZ() + ChatColor.AQUA + " in "
                + ChatColor.BLUE + player.getWorld().getName() + ChatColor.AQUA + ".");

    }

    public whereExecutor(XeChat XE) {
        super(XE);
    }
}
