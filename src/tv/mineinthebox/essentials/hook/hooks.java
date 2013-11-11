package tv.mineinthebox.essentials.hook;

import org.bukkit.Bukkit;

public class hooks {
	
	public static boolean isWorldGuardEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
			return true;
		}
		return false;
	}
	
	public static boolean isPexEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
			return true;
		}
		return false;
	}
	
	public static boolean isGroupManagerEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("GroupManager")) {
			return true;
		}
		return false;
	}

}
