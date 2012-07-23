package me.supermaxman.xechat.Objects;

import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class XeChannel {


    private String name;
    private String creator;
    private ChatColor color;
    private ArrayList<String> players = new ArrayList<String>();
    private boolean isPermenent = false;

    public XeChannel(String channelName, String channelCreator, ChatColor channelColor) {
        name = channelName;
        creator = channelCreator;
        color = channelColor;
    }

    public XeChannel(String name) {
        this.name = name;
    }


    public void sendString(String m) {
        if (m != null) {
            for (String s : players) {
                Player p = XeChat.XE.getServer().getPlayerExact(s);
                if (p != null) {
                    p.sendMessage(m);
                }
            }
            save();
//            XeChat.log.info(toDBFormat());
        } else {
            XeChat.log.warning("Dont send Null to a channel derp.");
        }
    }

    public String getName() {
        return name;
    }

    public boolean isPermenent() {
        return isPermenent;
    }

    public void setPermenent(boolean b) {
        this.isPermenent = b;
    }

    public String getCreatorName() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public ArrayList<String> getPlayers() {
        return players;

    }

    public void addPlayer(Player p) {
        this.players.add(p.getName());

    }

    public void addPlayer(String string) {
        this.players.add(string);

    }

    public void removePlayer(Player p) {
        this.players.remove(p.getName());

    }

    public void save() {
        FileConfiguration config = XeChat.conf;
        config.set("channel." + name + ".creator", creator);
        config.set("channel." + name + ".color", color.getChar());
        config.set("channel." + name + ".players", players);
        XeChat.XE.saveConfig();
    }

    @Override
    public String toString() {
        return "XeChannel{" +
                "name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", color=" + color +
                ", players=" + players +
                '}';
    }


}
