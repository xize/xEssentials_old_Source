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
	
	public static boolean isHerochatEnabeld() {
		if(Bukkit.getPluginManager().isPluginEnabled("HeroChat")) {
			return true;
		}
		return false;
	}
	
	public static boolean isbPermissionsEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("bPermissions")) {
			return true;
		}
		return false;
	}
	
	public static boolean isVaultEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("Vault")) {
			return true;
		}
		return false;
	}
	
	public static boolean isManCoEnabled() {
		if(Bukkit.getPluginManager().isPluginEnabled("ManCo")) {
			return true;
		}
		return false;
	}

}
