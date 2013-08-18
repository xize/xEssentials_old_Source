package tv.mineinthebox.resources.worldguard;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class worldguardApi {
	
	public static boolean isInRegion(Location loc) {
		if(Bukkit.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {	
			WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
			for(ProtectedRegion region : wg.getRegionManager(loc.getWorld()).getApplicableRegions(loc)) {
				if(region.getFlag(DefaultFlag.MOB_SPAWNING) == State.DENY || region.getFlag(DefaultFlag.PVP) == State.DENY) {
					//player has entered
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

}
