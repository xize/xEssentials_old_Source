package tv.mineinthebox.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import tv.mineinthebox.xEssentials;
import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.events.joinEvent.alternateAccountEvent;
import tv.mineinthebox.events.joinEvent.bancheck;
import tv.mineinthebox.events.joinEvent.flyEvent;
import tv.mineinthebox.events.joinEvent.joinMessageEvent;
import tv.mineinthebox.events.joinEvent.loadMemory;
import tv.mineinthebox.events.joinEvent.modreqEvent;
import tv.mineinthebox.events.joinEvent.taskEvent;
import tv.mineinthebox.events.joinEvent.vanishEvent;
import tv.mineinthebox.events.kickEvent.banKickEvent;
import tv.mineinthebox.events.kickEvent.delKickMemory;
import tv.mineinthebox.events.kickEvent.saveLocationEvent;
import tv.mineinthebox.events.leaveEvent.delLeaveMemory;
import tv.mineinthebox.events.leaveEvent.leaveMessageEvent;
import tv.mineinthebox.events.leaveEvent.locationSave;
import tv.mineinthebox.events.leaveEvent.saveInventory;
import tv.mineinthebox.events.leaveEvent.torchEventLeave;
import tv.mineinthebox.events.playerMoveEvent.firefly;
import tv.mineinthebox.events.playerMoveEvent.torchEvent;
import tv.mineinthebox.events.playerMoveEvent.zoneEvent;

public class handler {
	xEssentials plugin;
	public handler(xEssentials plugin) {
		this.plugin = plugin;
	}

	public void getListener() {
		 /*
		  * 
		  * ban system
		  * 
		  */
		
		if(xEssentialsMemory.isBanSystemEnabled) {
			setListener(new bancheck()); 
			setListener(new banKickEvent());
			if(xEssentialsMemory.showAlternateAccounts) {
				setListener(new alternateAccountEvent());
			}
		}
		
		/*
		 * 
		 * join event
		 * 
		 * 
		 */
		
		setListener(new flyEvent());
		setListener(new joinMessageEvent());
		setListener(new modreqEvent());
		setListener(new taskEvent());
		setListener(new vanishEvent());
		setListener(new loadMemory());
		
		/*
		 * 
		 * kick event 
		 * 
		 */
		
		setListener(new saveLocationEvent());
		setListener(new delKickMemory());
		
		/*
		 * 
		 * leave event
		 * 
		 */
		
		setListener(new leaveMessageEvent());
		setListener(new locationSave());
		if(xEssentialsMemory.playerSaveInventory) {setListener(new saveInventory());}
		setListener(new torchEventLeave());
		setListener(new delLeaveMemory());
		
		/*
		 * 
		 * player move events
		 * 
		 */
		
		setListener(new firefly());
		setListener(new torchEvent());
		setListener(new zoneEvent());
	}

	public void setListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}

}
