package tv.mineinthebox.events.pvp;

import java.util.HashMap;
import java.util.Iterator;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import tv.mineinthebox.xEssentialsMemory;

public class KillBounty implements Listener {

	public static HashMap<String, Entity> entityList = new HashMap<String, Entity>();

	@EventHandler
	public void storeKillBounty(EntityDamageByEntityEvent e) {

		if(e.getDamager() instanceof Player) {
			if(e.getEntity() instanceof Player || e.getEntity() instanceof LivingEntity) {
				Player p = (Player) e.getDamager();
				entityList.put(p.getName(), e.getEntity());
			}
		}
	}

	@EventHandler
	public void getKillBounty(EntityDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player killed = (Player) e.getEntity();
			if(entityList.containsValue(e.getEntity())) {
				Iterator<String> it = entityList.keySet().iterator();
				if(it.hasNext()) {
					String playerName = it.next();
					Player p = Bukkit.getPlayer(playerName);
					if(p instanceof Player) {
						if(Bukkit.getPluginManager().isPluginEnabled("iConomy") && Bukkit.getPluginManager().isPluginEnabled("Vault")) {
							RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
							Economy econ = economyProvider.getProvider();
							econ.depositPlayer(p.getName(), xEssentialsMemory.killBountyPrice);
							p.sendMessage(ChatColor.GOLD + "you earned " + xEssentialsMemory.killBountyPrice + "$! by killing " + ChatColor.GREEN + killed.getName());
							entityList.remove(p.getName());
						}
					} else {
						entityList.remove(playerName);
					}
				}
			}	
		} else {
			if(entityList.containsValue(e.getEntity())) {
				Iterator<String> it = entityList.keySet().iterator();
				if(it.hasNext()) {
					String playerName = it.next();
					Player p = Bukkit.getPlayer(playerName);
					if(p instanceof Player) {
						if(Bukkit.getPluginManager().isPluginEnabled("iConomy") && Bukkit.getPluginManager().isPluginEnabled("Vault")) {
							RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
							Economy econ = economyProvider.getProvider();
							econ.depositPlayer(p.getName(), xEssentialsMemory.killBountyPrice);
							p.sendMessage(ChatColor.GOLD + "you earned " + xEssentialsMemory.killBountyPrice + "$! by killing a " + ChatColor.GREEN + e.getEntityType().name().toLowerCase().replace("_", " "));
							entityList.remove(p.getName());
						}
					} else {
						entityList.remove(playerName);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void removeKillBountyLeave(PlayerQuitEvent e) {
		if(entityList.containsKey(e.getPlayer().getName())) {
			entityList.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void removeKillBountyKick(PlayerKickEvent e) {
		if(entityList.containsKey(e.getPlayer().getName())) {
			entityList.remove(e.getPlayer().getName());
		}
	}
}
