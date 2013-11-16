package tv.mineinthebox.essentials.hook;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import tv.mineinthebox.ManCo.ManCo;
import tv.mineinthebox.ManCo.exceptions.ChestHasNoOwnerException;

public class ManCoHook {
	
	public static boolean playerHasCrate(Player p) {
		ManCo manco = (ManCo) Bukkit.getPluginManager().getPlugin("ManCo");
		if(manco.getApi().hasCrate(p)) {
			return true;
		}
		return false;
	}
	
	public static Location getCrateLocation(Player p) {
		ManCo manco = (ManCo) Bukkit.getPluginManager().getPlugin("ManCo");
		if(manco.getApi().hasCrate(p)) {
			if(manco.getApi().isFalling(p)) {
				Location loc = manco.getApi().getFallingCrateLocation(p);
				return loc;
			} else {
				try {
					Chest chest = manco.getApi().getCrate(p);
					return chest.getLocation();
				} catch (ChestHasNoOwnerException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		return null;
	}

}
