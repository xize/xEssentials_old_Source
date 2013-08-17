package tv.mineinthebox.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import tv.mineinthebox.xEssentials;

public class handler {
	xEssentials plugin;
	public handler(xEssentials plugin) {
		this.plugin = plugin;
	}

	public void getListener() {
		/*
		 *  
		 * PlayerJoinEvent System
		 * 
		 */
		
		setListener(new playerJoin());

		/*
		 * 
		 * PlayerLeaveEvent system
		 * 
		 */
		
		setListener(new playerLeave());
		
		/*
		 * 
		 *  PlayerMoveEvent system
		 * 
		 */
		setListener(new playermove());
	}

	public void setListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}

}
