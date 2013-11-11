package tv.mineinthebox.essentials.events.playerDeathEvent;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class deathHeadEvent implements Listener {
	
	public static HashMap<String, Entity> entityList = new HashMap<String, Entity>();
	
	@EventHandler
	public void getMobTypeEvent(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getDamager() instanceof Zombie) {
				entityList.put(p.getName(), e.getDamager());
			} else if(e.getDamager() instanceof Arrow) {
				Arrow arrow = (Arrow) e.getDamager();
				if(arrow.getShooter() instanceof Skeleton) {
					entityList.put(p.getName(), arrow.getShooter());
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeathMobUseHead(PlayerDeathEvent e) {
		if(entityList.containsKey(e.getEntity().getName())) {
			Entity entity = entityList.get(e.getEntity().getName());
			if(!entity.isDead()) {
				if(entity instanceof Zombie) {
					ItemStack item = new ItemStack(Material.SKULL_ITEM);
					item.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) item.getItemMeta();
					meta.setOwner(e.getEntity().getName());
					item.setItemMeta(meta);
					Zombie zombie = (Zombie) entity;
					zombie.getEquipment().setHelmet(item);
					zombie.getEquipment().setHelmetDropChance(100);
					entityList.remove(e.getEntity().getName());
				} else if(entity instanceof Skeleton) {
					ItemStack item = new ItemStack(Material.SKULL_ITEM);
					item.setDurability((short) 3);
					SkullMeta meta = (SkullMeta) item.getItemMeta();
					meta.setOwner(e.getEntity().getName());
					item.setItemMeta(meta);
					Skeleton skeleton = (Skeleton) entity;
					skeleton.getEquipment().setHelmet(item);
					skeleton.getEquipment().setHelmetDropChance(100);
					entityList.remove(e.getEntity().getName());
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeathMobUseHeadClearMemory(PlayerQuitEvent e) {
		if(entityList.containsKey(e.getPlayer().getName())) {
			entityList.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void onPlayerDeathMobUseHeadClearMemory(PlayerKickEvent e) {
		if(entityList.containsKey(e.getPlayer().getName())) {
			entityList.remove(e.getPlayer().getName());
		}
	}
}
