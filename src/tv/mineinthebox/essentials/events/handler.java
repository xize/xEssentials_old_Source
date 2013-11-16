package tv.mineinthebox.essentials.events;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import tv.mineinthebox.essentials.xEssentials;
import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.events.EntityEvent.creatureSpawnManager;
import tv.mineinthebox.essentials.events.EntityEvent.criticalEvent;
import tv.mineinthebox.essentials.events.EntityEvent.disableFireWork;
import tv.mineinthebox.essentials.events.EntityEvent.disableSpawnEggs;
import tv.mineinthebox.essentials.events.EntityEvent.egglogger;
import tv.mineinthebox.essentials.events.EntityEvent.enderDragonCheck;
import tv.mineinthebox.essentials.events.EntityEvent.endermanCheck;
import tv.mineinthebox.essentials.events.EntityEvent.firespread;
import tv.mineinthebox.essentials.events.EntityEvent.witherGrief;
import tv.mineinthebox.essentials.events.EntityEvent.zombieTarget;
import tv.mineinthebox.essentials.events.broadcast.broadcast;
import tv.mineinthebox.essentials.events.chatEvent.AntiFloodSpam;
import tv.mineinthebox.essentials.events.chatEvent.afkChat;
import tv.mineinthebox.essentials.events.chatEvent.chatEvent;
import tv.mineinthebox.essentials.events.chatEvent.muteManager;
import tv.mineinthebox.essentials.events.joinEvent.PlayerTaskEvent;
import tv.mineinthebox.essentials.events.joinEvent.alternateAccountEvent;
import tv.mineinthebox.essentials.events.joinEvent.bancheck;
import tv.mineinthebox.essentials.events.joinEvent.flyEvent;
import tv.mineinthebox.essentials.events.joinEvent.ghostJoin;
import tv.mineinthebox.essentials.events.joinEvent.joinMessageEvent;
import tv.mineinthebox.essentials.events.joinEvent.loadMemory;
import tv.mineinthebox.essentials.events.joinEvent.modreqEvent;
import tv.mineinthebox.essentials.events.joinEvent.newPlayer;
import tv.mineinthebox.essentials.events.joinEvent.taskEvent;
import tv.mineinthebox.essentials.events.joinEvent.vanishEvent;
import tv.mineinthebox.essentials.events.kickEvent.banKickEvent;
import tv.mineinthebox.essentials.events.kickEvent.delKickMemory;
import tv.mineinthebox.essentials.events.kickEvent.ghostkickLeave;
import tv.mineinthebox.essentials.events.kickEvent.saveKickAlt;
import tv.mineinthebox.essentials.events.kickEvent.saveLocationEvent;
import tv.mineinthebox.essentials.events.leaveEvent.delLeaveMemory;
import tv.mineinthebox.essentials.events.leaveEvent.ghostLeave;
import tv.mineinthebox.essentials.events.leaveEvent.leaveMessageEvent;
import tv.mineinthebox.essentials.events.leaveEvent.locationSave;
import tv.mineinthebox.essentials.events.leaveEvent.saveInventory;
import tv.mineinthebox.essentials.events.leaveEvent.saveLeaveAlt;
import tv.mineinthebox.essentials.events.leaveEvent.torchEventLeave;
import tv.mineinthebox.essentials.events.motd.motd;
import tv.mineinthebox.essentials.events.playerDeathEvent.deathBackEvent;
import tv.mineinthebox.essentials.events.playerDeathEvent.deathHeadEvent;
import tv.mineinthebox.essentials.events.playerMoveEvent.afkCheck;
import tv.mineinthebox.essentials.events.playerMoveEvent.afkScheduler;
import tv.mineinthebox.essentials.events.playerMoveEvent.firefly;
import tv.mineinthebox.essentials.events.playerMoveEvent.torchEvent;
import tv.mineinthebox.essentials.events.playerMoveEvent.zoneEvent;
import tv.mineinthebox.essentials.events.pluginEnableEvent.TPS;
import tv.mineinthebox.essentials.events.pvp.KillBounty;
import tv.mineinthebox.essentials.events.pvp.clientSideGraveyard;
import tv.mineinthebox.essentials.events.pvp.disablePvp;
import tv.mineinthebox.essentials.events.pvp.steveHurtSound;
import tv.mineinthebox.essentials.events.realisticGlass.realisticGlass;
import tv.mineinthebox.essentials.events.signEvent.fireworkSign;
import tv.mineinthebox.essentials.events.signEvent.freeSign;
import tv.mineinthebox.essentials.events.signEvent.signBoom;
import tv.mineinthebox.essentials.events.teleportEvent.teleportBack;
import tv.mineinthebox.essentials.events.weatherEvent.weatherEvent;

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
		setListener(new PlayerTaskEvent());
		
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
		if(xEssentialsMemory.useCrits) {
			setListener(new criticalEvent());
		}
		
		/*
		 * 
		 * teleport event
		 * 
		 * 
		 */
		
		setListener(new teleportBack());
		setListener(new newPlayer());
		
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
		if(xEssentialsMemory.playerSteveHurtSound) {
			System.out.print("steves sounds enabled");
			setListener(new steveHurtSound());
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
		
		/*
		 * 
		 * afk system
		 * 
		 * 
		 */
		setListener(new afkCheck());
		afkScheduler.startAfkTimer();
		setListener(new afkScheduler());
		setListener(new afkChat());
		
		/*
		 * 
		 * realistic glass
		 * 
		 */
		
		if(xEssentialsMemory.realisticGlassEnabled) {setListener(new realisticGlass());}
		
		/*
		 * 
		 * 
		 * sign system
		 * 
		 * 
		 */
		setListener(new signBoom());
		setListener(new freeSign());
		setListener(new fireworkSign());
		
		/*
		 * 
		 * 
		 * ghost system
		 * 
		 * 
		 */
		setListener(new ghostLeave());
		setListener(new ghostkickLeave());
		setListener(new ghostJoin());
		
		/*
		 * 
		 * motd
		 * 
		 */
		setListener(new motd());
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
