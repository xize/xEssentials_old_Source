package tv.mineinthebox.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import tv.mineinthebox.xEssentials;
import tv.mineinthebox.xEssentialsMemory;
import tv.mineinthebox.events.EntityEvent.creatureSpawnManager;
import tv.mineinthebox.events.EntityEvent.disableFireWork;
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
import tv.mineinthebox.events.playerMoveEvent.firefly;
import tv.mineinthebox.events.playerMoveEvent.torchEvent;
import tv.mineinthebox.events.playerMoveEvent.zoneEvent;
import tv.mineinthebox.events.pluginEnableEvent.TPS;
import tv.mineinthebox.events.weatherEvent.weatherEvent;

@SuppressWarnings("deprecation")
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
	}

	public void setListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, xEssentials.getPlugin());
	}
	
	public static void restartListeners() {
		PlayerJoinEvent.getHandlerList().unregister(xEssentials.getPlugin());
		PlayerQuitEvent.getHandlerList().unregister(xEssentials.getPlugin());
		PlayerKickEvent.getHandlerList().unregister(xEssentials.getPlugin());
		WeatherChangeEvent.getHandlerList().unregister(xEssentials.getPlugin());
		PluginEnableEvent.getHandlerList().unregister(xEssentials.getPlugin());
		PlayerMoveEvent.getHandlerList().unregister(xEssentials.getPlugin());
		PlayerChatEvent.getHandlerList().unregister(xEssentials.getPlugin());
		EntityTargetLivingEntityEvent.getHandlerList().unregister(xEssentials.getPlugin());
		BlockSpreadEvent.getHandlerList().unregister(xEssentials.getPlugin());
		BlockBurnEvent.getHandlerList().unregister(xEssentials.getPlugin());
		CreatureSpawnEvent.getHandlerList().unregister(xEssentials.getPlugin());
		ProjectileLaunchEvent.getHandlerList().unregister(xEssentials.getPlugin());
		xEssentialsMemory.closeMemoryInput();
		xEssentialsMemory.startMemoryInput();
		xEssentials.handle.getListener();
		
	}

}
