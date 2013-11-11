package tv.mineinthebox.essentials.hook;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class groupmanager {
	
	public static String getSuffix(Player p) {
		GroupManager mg = (GroupManager) Bukkit.getPluginManager().getPlugin("GroupManager");
		String suffix = mg.getWorldsHolder().getWorldPermissions(p.getPlayer()).getUserSuffix(p.getPlayer().getName());
		return suffix;
	}

}
