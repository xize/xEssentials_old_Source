package tv.mineinthebox.essentials.events.EntityEvent;

import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class criticalEvent implements Listener {

	public HashSet<Entity> entitys = new HashSet<Entity>();

	@EventHandler
	public void onCritical(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			if(e.getEntity() instanceof LivingEntity) {
				LivingEntity attackedEntity = (LivingEntity) e.getEntity();
				final Slime entity = (Slime) e.getDamager().getWorld().spawnEntity(attackedEntity.getEyeLocation(), EntityType.SLIME);
				entity.setSize(1);
				entity.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e"+attackedEntity.getHealth() + "!"));
				entity.getWorld().playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 100);
				entitys.add(entity);
				entity.damage(1);	
			}
		}
	}

	@EventHandler
	public void preventDrop(EntityDeathEvent e) {
		if(entitys.contains(e.getEntity())) {
			e.getDrops().clear();
			entitys.remove(e.getEntity());
		}
	}

}
