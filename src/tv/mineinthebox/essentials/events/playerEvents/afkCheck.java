package tv.mineinthebox.essentials.events.playerEvents;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.resources.vanish.vanishApi;

public class afkCheck implements Listener {
	
	public static ArrayList<String> afkList = new ArrayList<String>();
	public static HashMap<String, String> afkMessage = new HashMap<String, String>();
	
	@EventHandler
	public void afkListener(PlayerMoveEvent e) {
		if(!vanishApi.isVanished(e.getPlayer())) {
			if(afkList.contains(e.getPlayer().getName())) {
				afkList.remove(e.getPlayer().getName());
				Bukkit.broadcastMessage(ChatColor.GREEN + e.getPlayer().getName() + " is no longer afk");
			}
		}
	}
	
	@EventHandler
	public void GodModeAfk(EntityDamageEvent e) {
		if(xEssentialsMemory.playerGodmodeAfk) {
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(afkList.contains(p.getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void GodModeAfk(EntityDamageByEntityEvent e) {
		if(xEssentialsMemory.playerGodmodeAfk) {
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(afkList.contains(p.getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void GodModeAfk(EntityTargetLivingEntityEvent e) {
		if(xEssentialsMemory.playerGodmodeAfk) {
			if(e.getTarget() instanceof Player) {
				Player p = (Player) e.getTarget();
				if(afkList.contains(p.getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void GodModeAfk(FoodLevelChangeEvent e) {
		if(xEssentialsMemory.playerGodmodeAfk) {
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(afkList.contains(p.getName())) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void afkQuit(PlayerQuitEvent e) {
		if(afkList.contains(e.getPlayer().getName())) {
			afkList.remove(e.getPlayer().getName());
		}
		if(afkMessage.containsKey(e.getPlayer().getName())) {
			afkMessage.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void afkQuit(PlayerKickEvent e) {
		if(afkList.contains(e.getPlayer().getName())) {
			afkList.remove(e.getPlayer().getName());
		}
		if(afkMessage.containsKey(e.getPlayer().getName())) {
			afkMessage.remove(e.getPlayer().getName());
		}
	}

}
