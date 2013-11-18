package tv.mineinthebox.essentials.events.playerEvents;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tv.mineinthebox.essentials.xEssentialsMemory;
import tv.mineinthebox.essentials.hook.Vault;
import tv.mineinthebox.essentials.hook.hooks;

public class KillBounty implements Listener {

	public static HashMap<String, Entity> entityList = new HashMap<String, Entity>();

	@EventHandler
	public void storeKillBounty(EntityDamageByEntityEvent e) {

		if(e.getDamager() instanceof Player) {
			if(e.getEntity() instanceof Player || e.getEntity() instanceof LivingEntity) {
				if(e.getEntity() instanceof Slime) {
					Slime slime = (Slime) e.getEntity();
					if(slime.getSize() == 2) {
						Player p = (Player) e.getDamager();
						entityList.put(p.getName(), e.getEntity());
						return;
					}
				} else if(e.getEntity() instanceof MagmaCube) {
					MagmaCube cube = (MagmaCube) e.getEntity();
					if(cube.getSize() == 2) {
						Player p = (Player) e.getDamager();
						entityList.put(p.getName(), e.getEntity());
						return;
					}
				} else {
					Player p = (Player) e.getDamager();
					entityList.put(p.getName(), e.getEntity());	
				}
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
						if(hooks.isVaultEnabled()) {
							Vault.desposit(p, xEssentialsMemory.killBountyPrice);
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
						if(hooks.isVaultEnabled()) {
							Vault.desposit(p, xEssentialsMemory.killBountyPrice);
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
