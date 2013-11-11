package tv.mineinthebox.essentials.hook;

import org.bukkit.entity.Player;

import com.dthielke.herochat.Herochat;

public class herochat {
	
	public static String getSuffix(Player p) {
		String herochat = Herochat.getChatService().getPlayerSuffix(p.getPlayer().getWorld(), p.getPlayer().getName());
		return herochat;
	}

}
