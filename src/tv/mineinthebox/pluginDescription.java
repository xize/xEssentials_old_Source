package tv.mineinthebox;

import org.bukkit.Bukkit;

public class pluginDescription {
	
	public String version() {
		xEssentials plugin = (xEssentials) Bukkit.getPluginManager().getPlugin("xEssentials");
		if(plugin.getDescription().getVersion() != null) {
			return " " + plugin.getDescription().getVersion();
		}
		return "";
	}
	
	public String pname() {
		xEssentials plugin = (xEssentials) Bukkit.getPluginManager().getPlugin("xEssentials");
		if(plugin.getDescription().getName() != null) {
			return "[" + plugin.getDescription().getName() + "]";
		}
		return "[xEssentials]";
	}

}
