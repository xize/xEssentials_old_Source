package tv.mineinthebox.essentials.hook;

import org.bukkit.entity.Player;

public class permissionsEx {
	
	public static String getSuffx(Player p) {
		String pex = PermissionsEx.getUser(p.getPlayer().getName()).getSuffix();
		return pex;
	}

}
