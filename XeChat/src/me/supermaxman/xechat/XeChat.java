package me.supermaxman.xechat;

import me.supermaxman.xechat.IRC.ircBot;
import me.supermaxman.xechat.IRC.pircbot.IrcException;
import me.supermaxman.xechat.IRC.pircbot.NickAlreadyInUseException;
import me.supermaxman.xechat.Objects.XeChannel;
import me.supermaxman.xechat.executors.channelCreatorExecutor;
import me.supermaxman.xechat.executors.channelDeleteExecutor;
import me.supermaxman.xechat.executors.channelJoinExecutor;
import me.supermaxman.xechat.executors.globalExecutor;
import me.supermaxman.xechat.executors.localExecutor;
import me.supermaxman.xechat.executors.staffExecutor;
import me.supermaxman.xechat.executors.tradeExecutor;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class XeChat extends JavaPlugin {

    //Required
    private Logger log;
    public static FileConfiguration conf;
    private final XeChatListener Listener = new XeChatListener(this);
    public static Permission permission = null;
    public static Chat chat = null;
    public static final HashMap<Player, XeChannel> channelIn = new HashMap<Player, XeChannel>();
    public static final HashMap<Player, List<XeChannel>> channelsOn = new HashMap<Player, List<XeChannel>>();
    static ircBot bot;
    public static XeChannel g = new XeChannel("G", "server", ChatColor.WHITE);
    public static XeChannel l = new XeChannel("l", "server", ChatColor.YELLOW);
    public static XeChannel trade = new XeChannel("trade", "server", ChatColor.BLUE);
    public static XeChannel z = new XeChannel("z", "server", ChatColor.DARK_GREEN);
    public static final HashMap<String, XeChannel> channels = new HashMap<String, XeChannel>();
    
    @Override
    public void onDisable() {
        log.info("Disabled.");
        bot.disconnect();
        bot.dispose();
    }

    @Override
    public void onEnable() {
        log = getLogger();
        if (!setupPermissions() || !setupChat()) {
            log.severe("Vault fail!");
            this.setEnabled(false);
            return;
        }
        setupChannels();
        getServer().getPluginManager().registerEvents(Listener, this);
        setupConfig();
        conf = getConfig();

        log.info("All systems go! Version:" + this.getDescription().getVersion());


        getCommand("l").setExecutor(new localExecutor(this));
        getCommand("g").setExecutor(new globalExecutor(this));
        getCommand("trade").setExecutor(new tradeExecutor(this));
        getCommand("z").setExecutor(new staffExecutor(this));
        getCommand("create").setExecutor(new channelCreatorExecutor(this));
        getCommand("join").setExecutor(new channelJoinExecutor(this));
        getCommand("delete").setExecutor(new channelDeleteExecutor(this));

        setupIRC();
    }
    

    public void setupChannels(){
    	channels.put("g", g);
    	channels.put("l", l);
    	channels.put("trade", trade);
    	channels.put("z", z);
    	
    }
    
    
    
    void setupIRC() {

        bot = new ircBot(this);
        try {
            bot.connect("127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NickAlreadyInUseException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        bot.joinChannel(conf.getString("IRC.Channel"));
        bot.setVerbose(false);
        bot.setMessageDelay(0);
        bot.sendMessage(conf.getString("IRC.Channel"), conf.getString("IRC.JoinMessage"));
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
        if (!conf.contains("IRC.nick")) {
            conf.set("IRC.nick", "DevBot");
        }
        if (!conf.contains("IRC.JoinMessage")) {
            conf.set("IRC.JoinMessage", "IM ALIVE");
        }
        if (!conf.contains("IRC.Channel")) {
            conf.set("IRC.Channel", "#Xe");
        }
        if (!conf.contains("IRC.nsPass")) {
            conf.set("IRC.nsPass", "Your_epic_pass");
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

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatRegisteredServiceProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatRegisteredServiceProvider != null) {
            chat = chatRegisteredServiceProvider.getProvider();
            return true;
        }
        return false;
    }
}