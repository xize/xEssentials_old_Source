package tv.mineinthebox.events;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import tv.mineinthebox.xEssentials;
import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.events.EntityEvent.creatureSpawnManager;
import tv.mineinthebox.events.EntityEvent.disableFireWork;
import tv.mineinthebox.events.EntityEvent.disableSpawnEggs;
import tv.mineinthebox.events.EntityEvent.egglogger;
import tv.mineinthebox.events.EntityEvent.enderDragonCheck;
import tv.mineinthebox.events.EntityEvent.endermanCheck;
import tv.mineinthebox.events.EntityEvent.firespread;
import tv.mineinthebox.events.EntityEvent.witherGrief;
import tv.mineinthebox.events.EntityEvent.zombieTarget;
import tv.mineinthebox.events.chatEvent.AntiFloodSpam;
import tv.mineinthebox.events.chatEvent.chatEvent;
import tv.mineinthebox.events.chatEvent.muteManager;
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
import tv.mineinthebox.events.kickEvent.saveKickAlt;
import tv.mineinthebox.events.kickEvent.saveLocationEvent;
import tv.mineinthebox.events.leaveEvent.delLeaveMemory;
import tv.mineinthebox.events.leaveEvent.leaveMessageEvent;
import tv.mineinthebox.events.leaveEvent.locationSave;
import tv.mineinthebox.events.leaveEvent.saveInventory;
import tv.mineinthebox.events.leaveEvent.saveLeaveAlt;
import tv.mineinthebox.events.leaveEvent.torchEventLeave;
import tv.mineinthebox.events.playerDeathEvent.deathBackEvent;
import tv.mineinthebox.events.playerDeathEvent.deathHeadEvent;
import tv.mineinthebox.events.playerMoveEvent.firefly;
import tv.mineinthebox.events.playerMoveEvent.torchEvent;
import tv.mineinthebox.events.playerMoveEvent.zoneEvent;
import tv.mineinthebox.events.pluginEnableEvent.TPS;
import tv.mineinthebox.events.pvp.KillBounty;
import tv.mineinthebox.events.pvp.clientSideGraveyard;
import tv.mineinthebox.events.pvp.disablePvp;
import tv.mineinthebox.events.teleportEvent.teleportBack;
import tv.mineinthebox.events.weatherEvent.weatherEvent;
import tv.mineinthebox.events.broadcast.broadcast;

public class handler {

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
				setListener(new saveKickAlt());
				setListener(new saveLeaveAlt());
				setListener(new alternateAccountEvent());
			}
		}
		
		/*
		 * 
		 * join event
		 * 
		 * 
		 */
		
		setListener(new loadMemory());
		setListener(new flyEvent());
		setListener(new joinMessageEvent());
		setListener(new modreqEvent());
		setListener(new taskEvent());
		setListener(new vanishEvent());
		
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
		
		/*
		 * 
		 * chat system
		 * 
		 */
		setListener(new muteManager());
		if(xEssentialsMemory.smilleys) {
			setListener(new chatEvent());
		}
		if(xEssentialsMemory.isAntiFloodEnabled) {
			setListener(new AntiFloodSpam());
		}
		
		/*
		 * 
		 * weather system
		 * 
		 * 
		 */
		if(xEssentialsMemory.weather) {
			setListener(new weatherEvent());
		}
		
		/*
		 * 
		 * pluginEnableEvent
		 * 
		 */
		setListener(new TPS());
	
		/*
		 * 
		 * entity event
		 * 
		 * 
		 */
		if(xEssentialsMemory.zombieAggroEnabled) {
			setListener(new zombieTarget());
		}
		if(xEssentialsMemory.firespread) {
			setListener(new firespread());
		}
		setListener(new creatureSpawnManager());
		if(xEssentialsMemory.firework) {
			setListener(new disableFireWork());
		}
		if(xEssentialsMemory.withergrief) {
			setListener(new witherGrief());
		}
		if(xEssentialsMemory.endermangrief) {
			setListener(new endermanCheck());
		}
		if(xEssentialsMemory.enderdragongrief) {
			setListener(new enderDragonCheck());
		}
		if(xEssentialsMemory.disable_spawneggs) {
			setListener(new disableSpawnEggs());
		}
		if(xEssentialsMemory.logSpawnEggs) {
			setListener(new egglogger());
		}
		
		/*
		 * 
		 * teleport event
		 * 
		 * 
		 */
		
		setListener(new teleportBack());
		
		/*
		 * 
		 * 
		 * death event
		 * 
		 * 
		 */
		
		setListener(new deathBackEvent());
		if(xEssentialsMemory.mobsUseHeads) {
			setListener(new deathHeadEvent());
		}
		
		/*
		 * 
		 * 
		 * pvp events
		 * 
		 * 
		 */
		
		if(xEssentialsMemory.disablePvp) {
			setListener(new disablePvp());
		}
		if(xEssentialsMemory.createClientsideGraveyard) {
			setListener(new clientSideGraveyard());
		}
		if(xEssentialsMemory.killBountyEnabled) {
			setListener(new KillBounty());
		}
		
		/*
		 * 
		 * broadcast system
		 * 
		 * 
		 */
		if(xEssentialsMemory.broadcastEnabled) {
			broadcast.activateBroadcast();
		}
	}

	public void setListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, xEssentials.getPlugin());
	}
	
	public static void restartListeners() {
		HandlerList.unregisterAll(xEssentials.getPlugin());
		xEssentialsMemory.startMemoryInput();
		xEssentials.handle.getListener();	
	}

}
