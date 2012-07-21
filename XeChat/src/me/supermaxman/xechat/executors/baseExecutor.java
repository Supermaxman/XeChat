package me.supermaxman.xechat.executors;

import me.supermaxman.xechat.XeChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

abstract class baseExecutor implements CommandExecutor {
    private static XeChat XE;
    // Permission permission = xEssentials.permission;

    baseExecutor(XeChat XE) {
        this.XE = XE;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        final String commandName = command.getName().toLowerCase();
        Player player;
        final boolean isPlayer = (sender instanceof Player);

        if (isPlayer) {
            player = (Player) sender;
        } else {
            return false;
        }

        this.run(player, args);

        return true;
    }

    protected abstract void run(Player player, String[] args);

}