package tv.mineinthebox.essentials.events;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import tv.mineinthebox.essentials.xEssentials;
import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.events.EntityEvents.creatureSpawnManager;
import tv.mineinthebox.essentials.events.EntityEvents.criticalEvent;
import tv.mineinthebox.essentials.events.EntityEvents.disableFireWork;
import tv.mineinthebox.essentials.events.EntityEvents.disableSpawnEggs;
import tv.mineinthebox.essentials.events.EntityEvents.egglogger;
import tv.mineinthebox.essentials.events.EntityEvents.enderDragonCheck;
import tv.mineinthebox.essentials.events.EntityEvents.endermanCheck;
import tv.mineinthebox.essentials.events.EntityEvents.firespread;
import tv.mineinthebox.essentials.events.EntityEvents.weatherEvent;
import tv.mineinthebox.essentials.events.EntityEvents.witherGrief;
import tv.mineinthebox.essentials.events.EntityEvents.zombieTarget;
import tv.mineinthebox.essentials.events.banEvents.AntiFloodSpam;
import tv.mineinthebox.essentials.events.banEvents.alternateAccountEvent;
import tv.mineinthebox.essentials.events.banEvents.banKickEvent;
import tv.mineinthebox.essentials.events.banEvents.bancheck;
import tv.mineinthebox.essentials.events.banEvents.muteManager;
import tv.mineinthebox.essentials.events.banEvents.saveKickAlt;
import tv.mineinthebox.essentials.events.banEvents.saveLeaveAlt;
import tv.mineinthebox.essentials.events.broadcast.broadcast;
import tv.mineinthebox.essentials.events.memory.delKickMemory;
import tv.mineinthebox.essentials.events.memory.delLeaveMemory;
import tv.mineinthebox.essentials.events.memory.loadMemory;
import tv.mineinthebox.essentials.events.motd.motd;
import tv.mineinthebox.essentials.events.playerEvents.KillBounty;
import tv.mineinthebox.essentials.events.playerEvents.PlayerTaskEvent;
import tv.mineinthebox.essentials.events.playerEvents.TPS;
import tv.mineinthebox.essentials.events.playerEvents.afkChat;
import tv.mineinthebox.essentials.events.playerEvents.afkCheck;
import tv.mineinthebox.essentials.events.playerEvents.afkScheduler;
import tv.mineinthebox.essentials.events.playerEvents.chatEvent;
import tv.mineinthebox.essentials.events.playerEvents.clientSideGraveyard;
import tv.mineinthebox.essentials.events.playerEvents.deathHeadEvent;
import tv.mineinthebox.essentials.events.playerEvents.disablePvp;
import tv.mineinthebox.essentials.events.playerEvents.firefly;
import tv.mineinthebox.essentials.events.playerEvents.flyEvent;
import tv.mineinthebox.essentials.events.playerEvents.ghostJoin;
import tv.mineinthebox.essentials.events.playerEvents.ghostLeave;
import tv.mineinthebox.essentials.events.playerEvents.ghostkickLeave;
import tv.mineinthebox.essentials.events.playerEvents.joinMessageEvent;
import tv.mineinthebox.essentials.events.playerEvents.leaveMessageEvent;
import tv.mineinthebox.essentials.events.playerEvents.locationSave;
import tv.mineinthebox.essentials.events.playerEvents.modreqEvent;
import tv.mineinthebox.essentials.events.playerEvents.newPlayer_spawn;
import tv.mineinthebox.essentials.events.playerEvents.realisticGlass;
import tv.mineinthebox.essentials.events.playerEvents.saveInventory;
import tv.mineinthebox.essentials.events.playerEvents.saveLocationEvent;
import tv.mineinthebox.essentials.events.playerEvents.spawnDeathEvent;
import tv.mineinthebox.essentials.events.playerEvents.steveHurtSound;
import tv.mineinthebox.essentials.events.playerEvents.taskEvent;
import tv.mineinthebox.essentials.events.playerEvents.torchEvent;
import tv.mineinthebox.essentials.events.playerEvents.torchEventLeave;
import tv.mineinthebox.essentials.events.playerEvents.wg_spawnzone;
import tv.mineinthebox.essentials.events.signEvent.colorSigns;
import tv.mineinthebox.essentials.events.signEvent.fireworkSign;
import tv.mineinthebox.essentials.events.signEvent.freeSign;
import tv.mineinthebox.essentials.events.signEvent.signBoom;
import tv.mineinthebox.essentials.events.teleportEvents.deathBackEvent;
import tv.mineinthebox.essentials.events.teleportEvents.teleportBack;
import tv.mineinthebox.essentials.events.vanishEvents.vanishEntity;
import tv.mineinthebox.essentials.events.vanishEvents.vanishInteract;
import tv.mineinthebox.essentials.events.vanishEvents.vanishOnjoin;

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
		setListener(new wg_spawnzone());
		
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
		setListener(new newPlayer_spawn());
		
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
		setListener(new spawnDeathEvent());
		
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
		setListener(new colorSigns());
		
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
		
		/*
		 * 
		 * Vanish events
		 * 
		 * 
		 */
		setListener(new vanishEntity());
		setListener(new vanishInteract());
		setListener(new vanishOnjoin());
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
