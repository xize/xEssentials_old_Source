package tv.mineinthebox.essentials.hook;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class permissionsEx {
	
	public static String getSuffx(Player p) {
		String pex = PermissionsEx.getUser(p.getPlayer().getName()).getSuffix();
		return pex;
	}

}
