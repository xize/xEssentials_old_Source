package tv.mineinthebox.essentials.events.playerEvents;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitTask;

import tv.mineinthebox.essentials.xEssentials;

public class realisticGlass implements Listener {
	
	public HashMap<Location, MaterialData> blocks = new HashMap<Location, MaterialData>();
	public HashMap<Location, Block> glasses = new HashMap<Location, Block>();

	public BukkitTask task;
	
	@EventHandler
	public void projectiles(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getEntity();
			if(arrow.getShooter() instanceof Player) {
				if(hasAttachedGlass(arrow.getLocation().getBlock())) {
					storeGlasses(arrow.getLocation().getBlock());
					arrow.remove();
					destroyRandomGlasses();
					startRegen();
				}
			}
		}
	}
	
	public boolean hasAttachedGlass(Block block) {
		for(BlockFace face : BlockFace.values()) {
			if(face == BlockFace.SELF) {
				
			}else if(block.getRelative(face).getType() == Material.GLASS || block.getRelative(face).getType() == Material.THIN_GLASS) {
				return true;
			}
		}
		return false;
	}
	
	public void storeGlasses(Block block) {
		for(int X = 0; X < 12;X++) {
			for(int Y = 0; Y < 12; Y++) {
				for(int Z  = 0; Z < 12; Z++) {
					Block allplus = block.getWorld().getBlockAt((block.getX()+X), (block.getY()+Y),(block.getZ()+Z));
					if(allplus.getType() == Material.GLASS || allplus.getType() == Material.THIN_GLASS) {
						glasses.put(allplus.getLocation(), allplus);
					}
					Block allmin = block.getWorld().getBlockAt((block.getX()-X), (block.getY()+Y),(block.getZ()-Z));
					if(allmin.getType() == Material.GLASS || allmin.getType() == Material.THIN_GLASS) {
						glasses.put(allmin.getLocation(), allmin);
					}
					Block xmin = block.getWorld().getBlockAt((block.getX()-X), (block.getY()+Y), (block.getZ()+Z));
					if(xmin.getType() == Material.GLASS || xmin.getType() == Material.THIN_GLASS) {
						glasses.put(xmin.getLocation(), xmin);
					}
					Block zmin = block.getWorld().getBlockAt((block.getX()+X), (block.getY()+Y), (block.getZ()-Z));
					if(zmin.getType() == Material.GLASS || zmin.getType() == Material.THIN_GLASS) {
						glasses.put(zmin.getLocation(), zmin);
					}
				}
			}
		}
	}
	
	public void destroyRandomGlasses() {
		for(int i = 0; i < glasses.size();i++) {
			Location[] locs = glasses.keySet().toArray(new Location[glasses.size()]);
			Location loc = locs[i];
			Block block = glasses.get(loc);
			blocks.put(loc, block.getState().getData());
			block.getWorld().playEffect(loc, Effect.STEP_SOUND, block.getType());
			glasses.remove(loc);
			block.breakNaturally();
		}
		glasses.clear();
	}
	
	@EventHandler
	public void entityFall(EntityDamageEvent e) {
		if(e.getCause() == DamageCause.FALL) {
			if(e.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GLASS) {
				storeGlasses(e.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN));
				destroyRandomGlasses();
			}
		}
	}
	
	public void startRegen() {
		task = Bukkit.getScheduler().runTaskTimer(xEssentials.getPlugin(), new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if(blocks.isEmpty() || blocks.size() == 0) {
					task.cancel();
				} else {
					Iterator<Entry<Location, MaterialData>> it = blocks.entrySet().iterator();
					it.hasNext();
					Map.Entry<Location, MaterialData> its = (Map.Entry<Location, MaterialData>) it.next();
					Location loca = (Location) its.getKey();
					MaterialData blockType = (MaterialData) its.getValue();
					loca.getBlock().setTypeId(blockType.getItemTypeId());
					loca.getBlock().setData(blockType.getData());
					loca.getBlock().getWorld().playEffect(loca.getBlock().getLocation(), Effect.STEP_SOUND, loca.getBlock().getType());
					it.remove();
					blocks.remove(loca);
				}
			}
			
		}, 10, 100);
	}

}
