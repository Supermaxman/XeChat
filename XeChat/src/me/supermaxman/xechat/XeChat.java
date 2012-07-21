package me.supermaxman.xechat;

import me.supermaxman.xechat.executors.globalExecutor;
import me.supermaxman.xechat.executors.localExecutor;
import me.supermaxman.xechat.executors.tradeExecutor;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class XeChat extends JavaPlugin {

    //Required
    private Logger log;
    public static FileConfiguration conf;
    private final XeChatListener Listener = new XeChatListener(this);
    public static Permission permission = null;
    public static final HashMap<Player, String> channelIn = new HashMap<Player, String>();
    public static final HashMap<Player, List<String>> channelsOn = new HashMap<Player, List<String>>();

    //herpus

    @Override
    public void onDisable() {
        log.info("Disabled.");
    }

    @Override
    public void onEnable() {
        log = getLogger();
        if (!setupPermissions()) {
            log.severe("Could not find a permissions connector!");
            this.setEnabled(false);
            return;
        }

        getServer().getPluginManager().registerEvents(Listener, this);

        conf = getConfig();

        log.info("All systems go! Version:" + this.getDescription().getVersion());
        setupConfig();


        getCommand("l").setExecutor(new localExecutor(this));
        getCommand("g").setExecutor(new globalExecutor(this));
        getCommand("t").setExecutor(new tradeExecutor(this));
        //getCommand("c").setExecutor(CommandExecutor);
        //getCommand("d").setExecutor(CommandExecutor);
    }

    void setupConfig() {
        if (conf.get("worldinchat") == null) {
            conf.set("worldinchat", false);
        }
        if (conf.get("defaultChannel") == null) {
            conf.set("defaultChannel", "G");
        }
        if (conf.get("localdistence") == null) {
            conf.set("localdistence", 100);
        }
        saveConfig();
    }


    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
            return true;
        }
        return false;
    }
}