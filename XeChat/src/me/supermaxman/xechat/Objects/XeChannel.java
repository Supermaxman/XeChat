package me.supermaxman.xechat.Objects;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class XeChannel{
	
	
	private String name;
	private String creator;
	private ChatColor color;
	private ArrayList<Player> players = new ArrayList<Player>();
	
	
	public XeChannel(String channelName, String channelCreator, ChatColor channelColor) {
		name = channelName;
		creator = channelCreator;
		color = channelColor;
    }
	
	
	
	
	public String getName(){
		return name;
	}
	public String getCreatorName(){
		return creator;
	}
	public ChatColor getColor(){
		return color;
	}
	public ArrayList<Player> getPlayers(){
		return players;
		
	}
	public void addPlayer(Player p){
		this.players.add(p);
		
	}
	public void removePlayer(Player p){
		this.players.remove(p);
		
	}
}
