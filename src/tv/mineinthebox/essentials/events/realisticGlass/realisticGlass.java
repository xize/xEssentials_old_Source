package tv.mineinthebox.essentials.events.realisticGlass;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
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
					System.out.print("is true");
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
		int x = block.getX();
		int y = block.getY();
		int z = block.getZ();
		for(int X = 0; X < 9;X++) {
			for(int Y = 0; Y < 9; Y++) {
				for(int Z  = 0; Z < 9; Z++) {
					//x+
					Block newBlockxPlus = block.getWorld().getBlockAt((x+X), (y+Y), (z+Z));
					if(newBlockxPlus.getType() == Material.GLASS || newBlockxPlus.getType() == Material.THIN_GLASS) {
						glasses.put(newBlockxPlus.getLocation(), newBlockxPlus);
					}
					Block newBlockzMinusMinus = block.getWorld().getBlockAt((x-X), (y+Y), (z-Z));
					if(newBlockzMinusMinus.getType() == Material.GLASS || newBlockzMinusMinus.getType() == Material.THIN_GLASS) {
						glasses.put(newBlockzMinusMinus.getLocation(), newBlockzMinusMinus);
					}
					Block newBlockxMinus = block.getWorld().getBlockAt((x-X), (y+Y), (z+Z));
					if(newBlockxMinus.getType() == Material.GLASS || newBlockxMinus.getType() == Material.THIN_GLASS) {
						glasses.put(newBlockxMinus.getLocation(), newBlockxMinus);
					}
					Block newBlockzMinus = block.getWorld().getBlockAt((x+X), (y+Y), (z-Z));
					if(newBlockzMinus.getType() == Material.GLASS || newBlockzMinus.getType() == Material.THIN_GLASS) {
						glasses.put(newBlockzMinus.getLocation(), newBlockzMinus);
					}
				}
			}
		}
		System.out.print("current blocks: " + glasses.size());
	}
	
	public void destroyRandomGlasses() {
		int i = 0;
		while(i <= glasses.size()) {
			Location[] locs = glasses.keySet().toArray(new Location[glasses.size()]);
			Location loc = locs[i];
			Block block = glasses.get(loc);
			blocks.put(block.getLocation(), block.getState().getData());
			block.getWorld().playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
			glasses.remove(loc);
			block.breakNaturally();
			i++;
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
			
		}, 0, 500);
	}

}
