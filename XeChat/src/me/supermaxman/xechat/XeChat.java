package me.supermaxman.xechat;

import me.supermaxman.xechat.IRC.ircBot;
import me.supermaxman.xechat.IRC.pircbot.IrcException;
import me.supermaxman.xechat.IRC.pircbot.NickAlreadyInUseException;
import me.supermaxman.xechat.Objects.XeChannel;
import me.supermaxman.xechat.executors.*;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class XeChat extends JavaPlugin {

    //Required
    public static Logger log;
    public static FileConfiguration conf;

    private final XeChatListener Listener = new XeChatListener(this);
    public static Permission permission = null;
    public static Chat chat = null;
    public static final HashMap<Player, XeChannel> channelIn = new HashMap<Player, XeChannel>();
    static ircBot bot;
    public static XeChannel g = new XeChannel("G", "server", ChatColor.WHITE);
    public static XeChannel l = new XeChannel("l", "server", ChatColor.YELLOW);
    public static XeChannel trade = new XeChannel("trade", "server", ChatColor.BLUE);
    public static XeChannel z = new XeChannel("z", "server", ChatColor.DARK_GREEN);
    public static final HashMap<String, XeChannel> channels = new HashMap<String, XeChannel>();
    public static final HashMap<Player, Player> whisper = new HashMap<Player, Player>();
    public static final HashMap<Player, Boolean> isWhispering = new HashMap<Player, Boolean>();
    public static final HashMap<Player, String> lastchat = new HashMap<Player, String>();
    
    public static XeChat XE;

    @Override
    public void onDisable() {
        log.info("Disabled.");
        bot.disconnect();
        bot.dispose();
        saveLoadedChannels();
    }

    @Override
    public void onEnable() {
        XE = this;
        log = getLogger();
        conf = getConfig();
        if (!setupPermissions() || !setupChat()) {
            log.severe("Vault fail!");
            this.setEnabled(false);
            return;
        }
        setupChannels();
        getServer().getPluginManager().registerEvents(Listener, this);
        setupConfig();
        log.info("All systems go! Version:" + this.getDescription().getVersion());
        
        getCommand("local").setExecutor(new localExecutor(this));
        getCommand("global").setExecutor(new globalExecutor(this));
        getCommand("trade").setExecutor(new tradeExecutor(this));
        getCommand("z").setExecutor(new staffExecutor(this));
        getCommand("create").setExecutor(new channelCreatorExecutor(this));
        getCommand("join").setExecutor(new channelJoinExecutor(this));
        getCommand("delete").setExecutor(new channelDeleteExecutor(this));
        getCommand("leave").setExecutor(new channelLeaveExecutor(this));
        getCommand("channellist").setExecutor(new channelListExecutor(this));
        getCommand("chlist").setExecutor(new channelListExecutor(this));
        getCommand("tell").setExecutor(new tellExecutor(this));
        getCommand("whisper").setExecutor(new tellExecutor(this));
        getCommand("msg").setExecutor(new tellExecutor(this));
        getCommand("r").setExecutor(new replyExecutor(this));
        getCommand("reply").setExecutor(new replyExecutor(this));
        getCommand("where").setExecutor(new whereExecutor(this));
        getCommand("rank").setExecutor(new rankExecutor(this));

        setupIRC();


    }


    public void setupChannels() {
        g.setPermanent(true);
        l.setPermanent(true);
        trade.setPermanent(true);
        z.setPermanent(true);

        if (conf.isConfigurationSection("channel")) {
            for (Map.Entry<String, Object> entry : conf.getConfigurationSection("channel").getValues(false).entrySet()) {
//                log.info(entry.getKey());
                XeChannel xeChannel = new XeChannel(entry.getKey());
                ConfigurationSection cs = conf.getConfigurationSection("channel." + entry.getKey());
                xeChannel.setColor(ChatColor.getByChar(cs.getString("color")));
                xeChannel.setCreator(cs.getString("creator"));
                for (String players : cs.getStringList("players")) {
                    xeChannel.addPlayer(players);
                }
                if(cs.getString("password")!=null){
                	xeChannel.setPrivate(true, cs.getString("password"));
                }
                channels.put(entry.getKey(), xeChannel);

//                XeChannel derp = new XeChannel(entry.getKey());

            }


        } else {
            channels.put("G", g);
            channels.put("l", l);
            channels.put("trade", trade);
            channels.put("z", z);
            saveLoadedChannels();
        }
    }

    public void saveLoadedChannels() {
        for (XeChannel xeChannel : channels.values()) {
            xeChannel.save();
        }
        saveConfig();
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