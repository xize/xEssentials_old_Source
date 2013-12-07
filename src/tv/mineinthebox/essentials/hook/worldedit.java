package tv.mineinthebox.essentials.hook;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalWorld;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.CylinderRegion;
import com.sk89q.worldedit.regions.CylinderRegionSelector;

public class worldedit {
	
	public static Location getFirstMatchingBiome(Player p, Biome biome) {
		
		ArrayList<Location> locations = new ArrayList<Location>();
		
		//highest could be set to 14000 but this is like a reload and very dangerous
		int range = 1800;
		WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		
		LocalWorld localworld = we.getSession(p).getSelectionWorld();
		
		Location loc = p.getLocation();
		
		try {
			CylinderRegion cyl = new CylinderRegionSelector(localworld, new Vector2D(loc.getBlockX(), loc.getBlockZ()), new Vector2D(0, range), loc.getBlockY(), loc.getBlockY()).getRegion();
			Iterable<Vector2D> vectors = cyl.asFlatRegion();
			Iterator<Vector2D> cylLocs = vectors.iterator();
			
			while(cylLocs.hasNext()) {
				Vector2D vector = cylLocs.next();
				Location locc = new Location(p.getWorld(), vector.getX(), loc.getY(), vector.getZ());
				if(locc.getBlock().getBiome() == biome) {
					locations.add(locc);
				}
			}
		} catch(IncompleteRegionException e) {
			p.sendMessage(ChatColor.RED + "an error ocuried has been the cause where our cylinder couldn't check for biomes!");
			return null;
		}
		
		if(!locations.isEmpty()) {
			Location arloc = locations.get(0);
			locations.clear();
			return arloc;
		} else {
			return null;
		}
	}

}
