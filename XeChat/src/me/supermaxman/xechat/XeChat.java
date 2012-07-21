package me.supermaxman.xechat;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class XeChat extends JavaPlugin implements Listener {

    //Required
    public static XeChat plugin;
    public static FileConfiguration conf;
    public XeChatListener Listener = new XeChatListener(this);
    public XeChatCommandExecutor CommandExecutor = new XeChatCommandExecutor(this);
    public static Permission permission = null;
    public static HashMap<Player, String> channelIn = new HashMap<Player, String>();
    public static HashMap<Player, List<String>> channelsOn = new HashMap<Player, List<String>>();

    //herpus

    @Override
    public void onDisable() {
        System.out.println("[XeChat]: Disabled.");
    }

    @Override
    public void onEnable() {
        setupPermissions();
        getServer().getPluginManager().registerEvents(Listener, this);
        conf = getConfig();
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
        this.plugin = this;
        setupConfig();


        getCommand("l").setExecutor(CommandExecutor);
        getCommand("g").setExecutor(CommandExecutor);
        //getCommand("c").setExecutor(CommandExecutor);
        //getCommand("d").setExecutor(CommandExecutor);
    }

    public void setupConfig() {
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
        }
        return (permission != null);
    }
}