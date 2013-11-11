package tv.mineinthebox.essentials.hook;

import org.bukkit.entity.Player;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.CalculableType;

public class bPermissions {
	
	public static String getSuffix(Player p) {
		String bPermissions = ApiLayer.getValue(p.getPlayer().getWorld().getName(), CalculableType.USER, p.getPlayer().getName(), "suffix");
		return bPermissions;
	}

}
