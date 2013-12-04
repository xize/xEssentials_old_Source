package tv.mineinthebox.essentials.events.playerEvents;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import tv.mineinthebox.essentials.fileManager;

public class spawnDeathEvent implements Listener {
	
	@EventHandler
	public void onDeathSpawn(PlayerRespawnEvent e) {
		if(fileManager.file_exists(e.getPlayer().getName().toLowerCase()+".yml", fileManager.getDir() + File.separator + "homes")) {
			Player p = e.getPlayer();
			Location loc = p.getLocation();
			loc.setX(fileManager.getDoubleValue(p.getName().toLowerCase() + ".yml", "x", fileManager.getDir() + File.separator + "homes"));
			loc.setY(fileManager.getDoubleValue(p.getName().toLowerCase() + ".yml", "y", fileManager.getDir() + File.separator + "homes"));
			loc.setZ(fileManager.getDoubleValue(p.getName().toLowerCase() + ".yml", "z", fileManager.getDir() + File.separator + "homes"));
			loc.setYaw(fileManager.getIntegerValue(p.getName().toLowerCase() + ".yml", "yaw", fileManager.getDir() + File.separator + "homes"));
			if(Bukkit.getWorld(fileManager.getStringValue(p.getName().toLowerCase() + ".yml", "World", fileManager.getDir() + File.separator + "homes")) instanceof World) {
				World w = Bukkit.getWorld(fileManager.getStringValue(p.getName().toLowerCase() + ".yml", "World", fileManager.getDir() + File.separator + "homes"));
				loc.setWorld(w);
				loc.getWorld().refreshChunk(loc.getChunk().getX(), loc.getChunk().getZ());
				if(p.isInsideVehicle()) {
					p.getVehicle().eject();
					p.teleport(loc);
				} else {
					e.setRespawnLocation(loc);
				}
			} else {
				loc.setX(fileManager.getDoubleValue("spawn.yml", "x", fileManager.getDir()));
				loc.setY(fileManager.getDoubleValue("spawn.yml", "y", fileManager.getDir()));
				loc.setZ(fileManager.getDoubleValue("spawn.yml", "z", fileManager.getDir()));
				loc.setYaw(fileManager.getIntegerValue("spawn.yml", "yaw", fileManager.getDir()));
				String wname = fileManager.getStringValue("spawn.yml", "world", fileManager.getDir());
				if(Bukkit.getWorld(wname) instanceof World) {
					loc.setWorld(Bukkit.getWorld(wname));
					if(p.isInsideVehicle()) {
						p.getVehicle().eject();
						e.setRespawnLocation(loc);
					} else {
						e.setRespawnLocation(loc);
					}
				}
			}
		} else if(fileManager.file_exists("spawn.yml", fileManager.getDir())) {
			Player p = e.getPlayer();
			Location loc = e.getPlayer().getLocation();
			loc.setX(fileManager.getDoubleValue("spawn.yml", "x", fileManager.getDir()));
			loc.setY(fileManager.getDoubleValue("spawn.yml", "y", fileManager.getDir()));
			loc.setZ(fileManager.getDoubleValue("spawn.yml", "z", fileManager.getDir()));
			loc.setYaw(fileManager.getIntegerValue("spawn.yml", "yaw", fileManager.getDir()));
			String wname = fileManager.getStringValue("spawn.yml", "world", fileManager.getDir());
			if(Bukkit.getWorld(wname) instanceof World) {
				loc.setWorld(Bukkit.getWorld(wname));
				if(p.isInsideVehicle()) {
					p.getVehicle().eject();
					e.setRespawnLocation(loc);
				} else {
					e.setRespawnLocation(loc);
				}
			}
		}
	}

}
