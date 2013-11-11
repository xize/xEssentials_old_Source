package tv.mineinthebox.essentials.hook;

import org.bukkit.Bukkit;

public class hooks {
	
	public static boolean isWorldGuardEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			return true;
		}
		return false;
	}

}
