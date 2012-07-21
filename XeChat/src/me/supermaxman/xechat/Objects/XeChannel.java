package me.supermaxman.xechat.Objects;

import org.bukkit.ChatColor;

public class XeChannel{
	
	
	private String name;
	private String creator;
	private ChatColor color;
	
	
	
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
	
	
}
