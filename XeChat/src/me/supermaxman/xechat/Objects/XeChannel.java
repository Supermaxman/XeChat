package me.supermaxman.xechat.Objects;

import me.supermaxman.xechat.XeChat;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class XeChannel {


    private final String name;
    private String creator;
    private ChatColor color;
    private final ArrayList<String> players = new ArrayList<String>();
    private boolean isPermenent = false;
    private boolean isPrivate = false;
    private String pass = null;

    public XeChannel(String channelName, String channelCreator, ChatColor channelColor) {
        name = channelName;
        creator = channelCreator;
        color = channelColor;
        save();
    }

    public XeChannel(String name) {
        this.name = name;
    }
    public void delete(){
        FileConfiguration config = XeChat.conf;
        config.set("channel." + name, null);
        
        XeChat.XE.saveConfig();
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
        } else {
            XeChat.log.warning("Dont send Null to a channel derp.");
        }
    }

    public void setPrivate(boolean b, String pass) {
        this.isPrivate = b;
        this.pass = pass;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getPassword() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public boolean isPermanent() {
        return isPermenent;
    }

    public void setPermanent(boolean permanent) {
        this.isPermenent = permanent;
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

    /**
     * Add a name to the player list via the Player object.
     *
     * @param player Player to add to the list
     */
    public void addPlayer(Player player) {
        this.players.add(player.getName());
    }

    /**
     * Add a name to the player list.
     *
     * @param playerName Name to add to player list.
     */
    public void addPlayer(String playerName) {
        this.players.add(playerName);
    }

    /**
     * Removes a player from the player list.
     *
     * @param player Player to remove from player list.
     */
    public void removePlayer(Player player) {
        this.players.remove(player.getName());

    }

    public void save() {
        FileConfiguration config = XeChat.conf;
        config.set("channel." + name + ".creator", creator);
        config.set("channel." + name + ".color", color.getChar());
        config.set("channel." + name + ".players", players);
        if (this.isPrivate) {
            config.set("channel." + name + ".password", pass);
        }
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
