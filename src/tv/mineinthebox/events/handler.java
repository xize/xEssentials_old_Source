package tv.mineinthebox.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import tv.mineinthebox.xEssentials;
import tv.mineinthebox.events.enumTypes.playerJoinEnum;

public class handler {
	
	public void getListener() {
		setListener(new playerJoin(playerJoinEnum.altmessage));
		setListener(new playerJoin(playerJoinEnum.isSafe));
		setListener(new playerJoin(playerJoinEnum.isWild));
		setListener(new playerJoin(playerJoinEnum.isXeph0re));
		setListener(new playerJoin(playerJoinEnum.welcomeMessage));
	}
	
	public void setListener(Listener listener) {
		xEssentials plugin = (xEssentials) Bukkit.getPluginManager().getPlugin("xEssentials");
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}

}
